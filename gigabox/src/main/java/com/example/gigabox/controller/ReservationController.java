package com.example.gigabox.controller;

import com.example.gigabox.dto.*;
import com.example.gigabox.repository.ReservationRepository;
import com.example.gigabox.repository.ReservationSeatRepository;
import com.example.gigabox.repository.ShowtimeRepository;
import com.example.gigabox.service.*;
import com.example.gigabox.users.GigaUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    private final ShowtimeService showtimeService;

    private final RestheaterService restheaterService;

    private final ReservationRepository reservationRepository;

    private final ReservationSeatRepository reservationSeatRepository;

    private final UserService userService;

    // 영화예매 페이지로 보내기
    @GetMapping("/reservation")
    public String movieList(Model model) {
        try {
            List<Resmovie> resmovies = reservationService.getMovies();
            int resmovieCount;

            resmovieCount = resmovies.size();
            model.addAttribute("resmovies", resmovies);
            model.addAttribute("resmovieCount", resmovieCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "reservation/movie"; // 예약 영화 목록 페이지로 이동
    }

    // 출발날짜에 해당하는 영화목록을 반환하는 메서드
    @GetMapping("/reservation/moviesByDate")
    public ResponseEntity<Map<String, Object>> moviesByDate(@RequestParam("date") String date) {
        LocalDate selectedDate = LocalDate.parse(date);

        // 날짜에 맞는 영화 목록을 가져옴
        List<Resmovie> resmovies = reservationService.getResmoviesByDateRange(selectedDate);

        // JSON 응답을 위한 Map 생성
        Map<String, Object> response = new HashMap<>();
        response.put("resmovies", resmovies);

        // 영화 목록을 JSON 형식으로 반환
        return ResponseEntity.ok(response);
    }


    // 영화 선택 후 해당 영화에 맞는 극장 목록을 반환하는 메서드
    @GetMapping("/reservation/theatersByMovie")
    public ResponseEntity<Map<String, Object>> restheatersByResmovie(@RequestParam("resmovieId") Long resmovieId) {
        List<Restheater> restheaters = reservationService.getRestheatersByResmovie(resmovieId);

        //지역별 극장 분류
        Map<String, List<String>> areaToTheaters = new HashMap<>();

        Map<String, Object> response = new HashMap<>();
        response.put("restheaters", restheaters);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/reservation/showtimesByRestheater")
    public ResponseEntity<List<Showtime>> showtimesByRestheater(@RequestParam("resmovieId") Long resmovieId,
                                                                @RequestParam("restheaterId") Long restheaterId,
                                                                @RequestParam("startDate") String startDate,
                                                                @RequestParam("endDate") String endDate) {

        // 로그로 출력
        System.out.println("선택된 시작 날짜: " + startDate);
        System.out.println("선택된 종료 날짜: " + endDate);

        // theaterId를 이용해서 DB에서 극장 정보를 조회
        Restheater restheater = restheaterService.getOneById(restheaterId);
        if (restheater == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList());  // 빈 리스트로 응답
        }

        // 1. 영화와 극장을 기반으로 상영 시간을 생성 (createShowTimesFormovieAndTheater 호출)
        showtimeService.createShowTimesFormovieAndRestheater(resmovieId, restheaterId);  // 상영 시간 생성 메서드 호출

        // 선택된 영화와 극장에 대한 상영시간을 가져옴
        List<Showtime> showTimes = reservationService.getShowTimesByResmovieAndRestheater(resmovieId, restheaterId);

        if (showTimes.isEmpty()) {
            System.out.println("No showtimes found for the selected movie and theater.");
        } else {
            System.out.println("Found showtimes: " + showTimes.size());
        }

        // 날짜 범위 변환
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);  // 선택된 날짜의 시작 시간
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);      // 선택된 날짜의 종료 시간

        // 타임존을 고려하여 날짜를 변환
        ZoneId zoneId = ZoneId.of("Asia/Seoul");  // 서울 타임존
        ZonedDateTime startSeoulTime = start.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId);
        ZonedDateTime endSeoulTime = end.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId);

        System.out.println("Start Date in Seoul time: " + startSeoulTime);
        System.out.println("End Date in Seoul time: " + endSeoulTime);

        // 고정된 상영 시간 리스트 (7시, 10시 20분, 13시 40분, 17시, 20시 20분)
        List<Integer> fixedShowtimes = Arrays.asList(7, 10, 13, 17, 20);  // 시(hour)만 기준

        // 현재 시간을 서울 타임존 기준으로 가져옴
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("Current Time: " + currentTime);

        // 선택된 날짜에 대해 유효한 상영 시간을 필터링
        List<Showtime> filteredShowtimes = fixedShowtimes.stream()
                .map(hour -> {
                    // 현재 시간보다 이전 시간은 비활성화 처리
                    ZonedDateTime showtimeSeoul = currentTime.withHour(hour).withMinute(0).withSecond(0).withNano(0);
                    // ZonedDateTime을 LocalDateTime으로 변환
                    LocalDateTime showtimeLocal = showtimeSeoul.toLocalDateTime();
                    if (showtimeSeoul.isBefore(currentTime)) {
                        return null; // 현재 시간보다 이전이면 비활성화
                    }

                    // 해당 상영시간을 보여줄 Showtime 객체 생성
                    Showtime showtime = new Showtime();
                    showtime.setShowTime(showtimeLocal);
                    return showtime;
                })
                .filter(Objects::nonNull)  // null은 필터링
                .collect(Collectors.toList());

        // 응답을 ArrayList로 명시적으로 설정 (클라이언트가 배열 형태로 받기 위함)
        return ResponseEntity.ok(filteredShowtimes);
    }


    @GetMapping("/showtimes/{resmovieId}/{restheaterId}")
    public List<ShowtimeDTO> getShowtimes(@PathVariable("resmovieId") Long resmovieId, @PathVariable("restheaterId") Long restheaterId) {
        return showtimeService.getShowTimesByResmovieAndRestheater(resmovieId, restheaterId);
    }



    // 예매 정보 저장
    @PostMapping("/reservation/saveSelection")
    public String saveSelection(@RequestParam("date") String date,
                                @RequestParam("resmovie") String resmovie,
                                @RequestParam("area") String area,
                                @RequestParam("restheater") String restheater,
                                @RequestParam("time") String time,
                                @RequestParam("resmovieImage") String resmovieImage,
                                HttpSession session) {

        // ISO 형식으로 전달된 date를 ZonedDateTime으로 변환하여 서울 시간으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        // 서울 타임존으로 변환
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime seoulDateTime = localDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId);

        // 선택된 예매 정보를 세션에 저장
        session.setAttribute("selectedDate", date);
        session.setAttribute("selectedResmovie", resmovie);
        session.setAttribute("selectedArea", area);
        session.setAttribute("selectedRestheater", restheater);
        session.setAttribute("selectedTime", time);
        session.setAttribute("selectedResmovieImage", resmovieImage);

        // 리디렉션
        return "redirect:/reservation/details";
    }


    // 예약 상세 내역 페이지로 이동
    @GetMapping("/reservation/confirmation")
    public String showConfirmationPage(Model model, HttpSession session) {
        // 세션에서 예매 정보를 가져와서 모델에 추가
        Long reservationId = (Long) session.getAttribute("reservationId");
        String selectedDate = (String) session.getAttribute("selectedDate");
        String selectedResmovie = (String) session.getAttribute("selectedResmovie");
        String selectedRestheater = (String) session.getAttribute("selectedRestheater");
        String selectedTime = (String) session.getAttribute("selectedTime");
        String selectedResmovieImage = (String) session.getAttribute("selectedResmovieImage");

        // 예약 정보를 모델에 추가
        model.addAttribute("reservationId", reservationId != null ? reservationId : "정보 없음");
        model.addAttribute("selectedDate", selectedDate != null ? selectedDate : "정보 없음");
        model.addAttribute("selectedResmovie", selectedResmovie != null ? selectedResmovie : "정보 없음");
        model.addAttribute("selectedRestheater", selectedRestheater != null ? selectedRestheater : "정보 없음");
        model.addAttribute("selectedTime", selectedTime != null ? selectedTime : "정보 없음");
        model.addAttribute("selectedResmovieImage", selectedResmovieImage != null ? selectedResmovieImage : "정보 없음");

        // 선택된 좌석 정보를 모델에 추가 (세션에서 저장된 좌석 정보를 사용할 수 있도록)
        List<String> selectedSeats = (List<String>) session.getAttribute("selectedSeats");
        Integer adultnum = (Integer) session.getAttribute("adultnum");
        Integer youthnum = (Integer) session.getAttribute("youthnum");
        System.out.println("선택된 좌석들: " + selectedSeats);
            System.out.println("선택된 어른: " + adultnum);
        System.out.println("선택된 청소년: " + youthnum);
        if (selectedSeats == null) {
            selectedSeats = new ArrayList<>(); // 좌석이 없으면 빈 리스트로 설정
        }
        model.addAttribute("selectedSeats", selectedSeats);
        model.addAttribute("adultnum", adultnum);
        model.addAttribute("youthnum", youthnum);

        return "reservation/confirmation"; // 예약 확인 페이지로 이동
    }



    // 예약하기 API
    @PostMapping("/reservation/book1")
    public ResponseEntity<Map<String, Object>> bookReservation(@RequestBody Reservation reservation, HttpSession session, Principal principal) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Principal을 사용하여 로그인된 사용자 정보 가져오기
            if (principal == null) {
                response.put("success", false);
                response.put("message", "로그인 후 예매를 진행해 주세요.");
                return ResponseEntity.ok(response);
            }

            String username = principal.getName(); // 로그인된 사용자 username 가져오기
            GigaUser loggedInUser = userService.getUsername(username); // username을 이용해 GigaUser 객체 가져오기

            // 클라이언트로부터 받은 날짜는 String 형태로 받음
            String dateString = reservation.getDate(); // 예: "2024-12-20"

            // 문자열을 LocalDate로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate selectedDate = LocalDate.parse(dateString, formatter);  // "2024-12-20" -> LocalDate

            // 하루를 더함
            LocalDate adjustedDate = selectedDate.plusDays(1);  // 하루를 더함 -> "2024-12-21"

            // LocalDate를 다시 "yyyy-MM-dd" 형식의 문자열로 변환
            String adjustedDateString = adjustedDate.format(formatter);  // "2024-12-21" -> String

            // 예약 객체에 adjustedDateString을 설정
            reservation.setDate(adjustedDateString);

            // 예약 객체에 로그인한 사용자 설정
            reservation.setUser(loggedInUser);

            // 예약 정보를 세션에 저장
            session.setAttribute("selectedDate", reservation.getDate());
            session.setAttribute("selectedResmovie", reservation.getResmovie());
            session.setAttribute("selectedRestheater", reservation.getRestheater());
            session.setAttribute("selectedTime", reservation.getTime());
            session.setAttribute("selectedResmovieImage", reservation.getResmovieImage());
            session.setAttribute("selectedSeats", reservation.getSeats());

            Reservation savedReservation = reservationService.saveReservation(reservation);  // 예약 저장


            // 성공적으로 예약이 저장되면 예약 ID 반환
            response.put("success", true);
            response.put("reservationId", savedReservation.getId());  // 예약 ID 반환
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reservation/book1")
    public ResponseEntity<Map<String, Object>> bookReservation(
            @RequestParam(name = "date") String date,
            @RequestParam(name = "movie") String movie,
            @RequestParam(name = "time") String time) {
        // 메소드 구현
        Map<String, Object> response = new HashMap<>();
        response.put("date", date);
        response.put("movie", movie);
        response.put("time", time);
        return ResponseEntity.ok(response);
    }

    // 추가된 예시: 예약 상세 조회하기
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<Map<String, Object>> getReservationDetails(@PathVariable("reservationId") String reservationId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // reservationId는 String 형태로 전달받으므로 Long으로 변환해야 함
            Long reservationIdLong = Long.parseLong(reservationId);

            // reservationId를 Long으로 변환하여 DB에서 해당 예약을 찾기
            Reservation reservation = reservationRepository.findById(reservationIdLong)
                    .orElseThrow(() -> new RuntimeException("Reservation not found"));

            // 예약 정보 반환
            response.put("success", true);
            response.put("reservation", reservation);
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Invalid reservation ID format");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }


    // 좌석 저장
    @Transactional
    @PostMapping("/reservation/saveSeats")
    public ResponseEntity<Map<String, Object>> saveSeats(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // 예약 ID 추출 (String 타입으로 받을 수 있지만, Long 타입으로 변환하여 사용)
        String reservationIdStr = (String) requestData.get("reservationId");
        System.out.println("잡아온 id" + reservationIdStr);
        Long reservationId = null;

        try {
            reservationId = Long.parseLong(reservationIdStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Invalid reservationId"));
        }

        Integer adultnum = null;
        Integer youthnum = null;

        // 성인 수와 청소년 수를 Integer로 변환 (값이 없거나 잘못된 값일 경우 기본값 0 사용)
        if (requestData.get("adultnum") instanceof Integer) {
            adultnum = (Integer) requestData.get("adultnum");
        } else if (requestData.get("adultnum") instanceof String) {
            try {
                adultnum = Integer.parseInt((String) requestData.get("adultnum"));
            } catch (NumberFormatException e) {
                // 오류 처리: 숫자가 아닌 값이 전달된 경우
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "Invalid adultnum"));
            }
        }
        if (requestData.get("youthnum") instanceof Integer) {
            youthnum = (Integer) requestData.get("youthnum");
        } else if (requestData.get("youthnum") instanceof String) {
            try {
                youthnum = Integer.parseInt((String) requestData.get("youthnum"));
            } catch (NumberFormatException e) {
                // 오류 처리: 숫자가 아닌 값이 전달된 경우
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "Invalid youthnum"));
            }
        }

        System.out.println("찾아진 reservationId: " + reservationId);

        // "seats"가 List<String> 형태로 오면 바로 사용, 아니면 분리하여 변환
        Object seatsObj = requestData.get("seats");

        List<String> seats = new ArrayList<>();

        // 좌석 데이터가 List<String>이면 그대로 사용
        if (seatsObj instanceof List) {
            seats = (List<String>) seatsObj;
        }
        // 좌석 데이터가 String이면 쉼표로 구분하여 List로 변환
        else if (seatsObj instanceof String) {
            String seatsStr = (String) seatsObj;

            // 문자열을 쉼표로 나누고 각 항목에서 공백을 제거
            seats = Arrays.stream(seatsStr.split(","))
                    .map(String::trim)  // 각 좌석에 대해 앞뒤 공백을 제거
                    .collect(Collectors.toList());
        }

        // 예약 ID로 Reservation 객체 찾기
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Reservation not found"));
        }

        Reservation reservation = optionalReservation.get();

        // 금액 계산
        int adultPricePerTicket = 15000;
        int youthPricePerTicket = 13000;
        int adultTotalPrice = adultnum * adultPricePerTicket;
        int youthTotalPrice = youthnum * youthPricePerTicket;
        int totalPrice = adultTotalPrice + youthTotalPrice;

        // DecimalFormat을 사용하여 천 단위 구분 기호 포함한 포맷 설정
        DecimalFormat formatter = new DecimalFormat("#,###");

        // 포맷된 가격 세션에 저장
        String formattedAdultPrice = formatter.format(adultTotalPrice);
        String formattedYouthPrice = formatter.format(youthTotalPrice);
        String formattedTotalPrice = formatter.format(totalPrice);

        // 세션에 포맷된 가격 저장
        session.setAttribute("formattedAdultPrice", formattedAdultPrice);
        session.setAttribute("formattedYouthPrice", formattedYouthPrice);
        session.setAttribute("formattedTotalPrice", formattedTotalPrice);

        reservation.setTotalPrice(totalPrice);

        // 좌석을 ReservationSeat 객체로 변환하여 저장
        for (String seat : seats) {
            ReservationSeat reservationSeat = new ReservationSeat(reservation, seat, adultnum, youthnum);
            reservation.getSeats().add(reservationSeat);
        }



        // 좌석 정보 저장
        reservationRepository.save(reservation);  // Reservation에 포함된 ReservationSeat들도 함께 저장됩니다.

        // 세션에 선택된 좌석을 저장
        session.setAttribute("selectedSeats", seats);  // 세션에 좌석 리스트를 저장
        session.setAttribute("adultnum", adultnum);
        session.setAttribute("youthnum", youthnum);

        // 성공 응답 반환
        return ResponseEntity.ok(Map.of("success", true));  // 성공 시 JSON 응답
    }





    // 좌석 예매 페이지로 이동 (showSeatPage)
    @GetMapping("/reservation/seat")
    public String showSeatPage(Model model, @RequestParam(name = "reservationId") Long reservationId) {
        // 예시로 예약 ID로 예매된 좌석들을 가져온다고 가정
        List<String> reservedSeats = reservationService.getReservedSeats(reservationId);

        // 모델에 예약된 좌석을 저장
        model.addAttribute("reservedSeats", reservedSeats);
        return "reservation/seat"; // 좌석 예매 페이지로 이동
    }

    //이전에 예약된 좌석을 조회하여 좌석페이지에 예약 중복을 막음
    // 예약된 좌석 정보를 반환하는 API
    @GetMapping("/reservation/seatData")
    public ResponseEntity<Map<String, Object>> getReservedSeats(@RequestParam("date") String date,
                                                                @RequestParam("movie") String resmovie,
                                                                @RequestParam("theater") String restheater,
                                                                @RequestParam("time") String time) {
        System.out.println("Received Params: " +
                "date=" + date +
                ", movie=" + resmovie +
                ", theater=" + restheater +
                ", time=" + time

        );


        // 해당 조건에 맞는 예약된 좌석을 조회
        List<ReservationSeat> reservedSeats = reservationSeatRepository.findReservedSeats(date, resmovie, restheater, time);

        System.out.println("예약된 좌석 : " +reservedSeats);
        // 예약된 좌석 번호만 추출
        List<String> reservedSeatIds = reservedSeats.stream()
                .map(ReservationSeat::getSeat)
                .collect(Collectors.toList());

        // JSON 응답 반환
        Map<String, Object> response = new HashMap<>();
        response.put("reservedSeats", reservedSeatIds);

        return ResponseEntity.ok(response);
    }



    @GetMapping("/reservation/final")
    public String getReservationsByUser(Model model) {
        // 현재 로그인된 사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("로그인한 사용자:" + principal);

        // principal이 CustomUserDetails 타입인지 확인 후 캐스팅
        GigaUser loggedInUser = null;
        //instanceof는
        // if (principal instanceof CustomUserDetails)일때, principal이 CustomUserDetails 타입(자료형)인지 확인하고 맞으면~~ true라는 것
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            System.out.println("사용자 ID: " + userDetails.getId()); // 사용자 ID 출력
            loggedInUser = userDetails.getGigaUser(); // GigaUser 객체를 가져옴
        } else {
            System.out.println("로그인된 사용자 정보가 CustomUserDetails가 아닙니다.");
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }

        // 로그인된 사용자의 id를 통해 예약 내역을 조회합니다.
        Long userId = loggedInUser.getId();  // GigaUser의 id로 사용자 정보 조회

        // userService에서 userId를 이용하여 GigaUser 객체를 조회
        GigaUser user = userService.getUser(userId); // userId로 사용자 정보 조회
        if (user == null) {
            // 사용자 정보가 없다면 예외 처리
            return "redirect:/login";  // 로그인 페이지로 리다이렉트
        }
        System.out.println("사용자id:" + userId);

        // 해당 사용자의 예약 내역을 가져옵니다.
        List<Reservation> reservations = reservationService.getReservationsByUser(user);

        System.out.println("예약내역: " + reservations);

        // 예약 내역을 모델에 추가하여 뷰로 전달
        model.addAttribute("reservations", reservations);

        // 예약 내역을 보여주는 view로 포워딩
        return "reservation/final"; // 예약 내역을 보여주는 뷰로 이동
    }

    @PostMapping("/reservation/delete/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable String reservationId) {
        try {
            Long id = Long.parseLong(reservationId);  // reservationId를 Long으로 변환
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);

            if (!optionalReservation.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("예약을 찾을 수 없습니다.");
            }

            Reservation reservation = optionalReservation.get();
            reservationRepository.delete(reservation);  // 예약 삭제

            return ResponseEntity.ok("예약이 삭제되었습니다.");  // 성공 메시지
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 예약 ID입니다.");
        }
    }



}

