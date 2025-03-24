package com.example.gigabox.service;

import com.example.gigabox.dto.Movie;
import com.example.gigabox.message.DataNotFoundException;
import com.example.gigabox.repository.MovieRepository;
import com.example.gigabox.repository.UserRepository;
import com.example.gigabox.users.GigaUser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository    userRepository;

    private static final String megaBox = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

    @PostConstruct
    public void preloadCache() {
        // 애플리케이션 시작 시, 캐시에 미리 데이터를 로드
        try {
            getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 영화 목록 캐시 적용
    @Cacheable(value = "moviesCache", key = "'moviesList'")
    public List<Movie> getMovies() throws IOException {
        List<Movie> moviesList = new ArrayList<>();  // 영화들을 저장할 리스트 생성
        try {
            Document doc = Jsoup.connect(megaBox).get();
            Elements movieEle = doc.select(".sect-movie-chart ol li");

            int maxMovies = 19;
            int count = 0;

            for (Element element : movieEle) {
                if (count >= maxMovies) {
                    break;
                }

                String posterUrl = element.select("img").attr("src");
                String rankText = element.select(".rank").text();
                String ranking = rankText.replace("No.", "").trim();
                String title = element.select(".title").text();
                String rate = element.select(".percent span").text();
                String date = element.select(".txt-info strong").text();
                Element ddayElement = element.select(".txt-info .dday").first();
                Element releaseElement = element.select(".txt-info span").first();
                if (ddayElement != null) {
                    ddayElement.remove();
                }
                if (releaseElement != null) {
                    releaseElement.remove();
                }
                date = element.select(".txt-info strong").text().trim();
                String age = element.select("i.cgvIcon.etc").text();

                String detailUrl = "http://www.cgv.co.kr/movies/detail-view/?midx=" + element.select("a").attr("href").split("=")[1];
                String story = story(detailUrl);
                String dtitle = dTitle(detailUrl);
                String director = director(detailUrl);
                String actors = actors(detailUrl);
                String genre = genre(detailUrl);
                String dInfo = dInfo(detailUrl);
                double gpa = (gpa(detailUrl));
                String movieCode = extractMovieCode(detailUrl);

                Movie movie = new Movie(posterUrl, ranking, title, rate, date, age, story, detailUrl, dtitle, director, actors, genre, dInfo, gpa, movieCode);
                moviesList.add(movie);  // 리스트에 추가

                count++;
            }

            if (!moviesList.isEmpty()) {
                saveAllMovies(moviesList);  // 저장 메서드 호출
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    // 상세 페이지에서 스토리 정보를 추출하는 메서드
    private String story(String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element storyElement = detailDoc.select(".sect-story-movie").first();

        if (storyElement != null) {
            String storyContent = storyElement.html();  // HTML 내용 가져오기
            storyContent = storyContent.replace("<br>", "\n");  // <br>을 \n으로 변환
            storyContent = Jsoup.parse(storyContent).text().trim();  // 나머지 HTML 태그는 제거
            return storyContent;
        } else {
            return "null";  // 스토리가 없을 경우 기본 값 반환
        }
    }

    private String dTitle(String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element dTitleElement = detailDoc.select(".title p").first();
//        Elements roundElements = detailDoc.select(".title .round");
//        for (Element round : roundElements) {
//            round.remove();
//        }
        if (dTitleElement != null) {
            return dTitleElement.text().trim();
        } else {
            return "";
        }
    }

    private String director(String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element directorElement = detailDoc.select(".spec > dl > dt:contains(감독 :) + dd a").first();

        if (directorElement != null) {
            return directorElement.text();
        } else {
            return "";
        }
    }

    private String actors (String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element actorsElement = detailDoc.select(".spec > dl > dt:contains(배우 :) + dd a").first();


        if (actorsElement != null) {
            return actorsElement.text();
        } else {
            return "";
        }
    }

    private String genre (String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element genreElement = detailDoc.select(".spec > dl > dt:contains(장르 :)").first();

        if (genreElement != null) {
            // "장르 : "를 기준으로 텍스트를 나누고, 두 번째 부분이 있다면 반환
            String genreText = genreElement.text();
            String[] parts = genreText.split(" : ");

            // '장르 : ' 뒤에 있는 텍스트가 존재하는지 확인하고, 존재하면 반환
            if (parts.length > 1) {
                return parts[1].trim();  // 두 번째 요소인 장르명만 반환
            } else {
                return "";  // "장르 : " 뒤에 값이 없을 경우
            }
        } else {
            return "No genre available";  // 장르 정보를 찾을 수 없는 경우
        }
    }

    private String dInfo (String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element dInfoElement = detailDoc.select(".spec > dl > dt:contains(기본 정보 :) + dd").first();

        if (dInfoElement != null) {
            return dInfoElement.text();
        } else {
            return "";
        }
    }

    private double gpa(String detailUrl) throws IOException {
        Document detailDoc = Jsoup.connect(detailUrl).get();

        Element gpaElement = detailDoc.select(".box_golden .percent").first();

        if (gpaElement != null) {
            try {
                String gpaText = gpaElement.text().replace("%", "").replace("?", "").trim();

                if (gpaText.isEmpty()) {
                    // GPA가 빈 문자열일 경우 기본값을 반환 (예: -1)
                    return 0;
                }

                // GPA 문자열이 비어 있지 않으면 파싱
                return Double.parseDouble(gpaText);
            } catch (NumberFormatException e) {
                // 숫자 형식 오류 처리
                System.out.println("Error parsing GPA: " + e.getMessage());
                return -1;  // 오류 발생 시 기본값 반환
            }
        } else {
            // GPA 정보가 없을 경우
            return -1;  // GPA가 없을 경우 기본값으로 -1 반환
        }

    }

    // detailUrl에서 movieCode 추출하는 메서드
    private String extractMovieCode(String detailUrl) {
        String[] parts = detailUrl.split("midx=");
        if (parts.length > 1) {
            return parts[1];
        }
        return "";  // movieCode를 찾을 수 없으면 빈 문자열 반환
    }


    // 중복 체크 및 저장/업데이트 메서드
    private void saveAllMovies(List<Movie> moviesList) {
        for (Movie movie : moviesList) {
            Optional<Movie> existingMovie = movieRepository.findByMovieCode(movie.getMovieCode());

            if (existingMovie.isPresent()) {
                // 기존 영화가 있으면 업데이트
                Movie existing = existingMovie.get();
                existing.setPosterUrl(movie.getPosterUrl());
                existing.setRanking(movie.getRanking());
                existing.setTitle(movie.getTitle());
                existing.setRate(movie.getRate());
                existing.setDate(movie.getDate());
                existing.setAge(movie.getAge());
                existing.setStory(movie.getStory());
                existing.setDetailUrl(movie.getDetailUrl());
                existing.setDirector(movie.getDirector());
                existing.setActors(movie.getActors());
                existing.setGenre(movie.getGenre());
                existing.setGpa(movie.getGpa());


                movieRepository.save(existing);
            } else {
                movieRepository.save(movie);
            }
        }
    }

    public Movie getDetail(String movieCode) {
        return this.movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new DataNotFoundException("데이터가 없습니다."));
    }


    public List<Movie> getHighRank(int count) {
        // PageRequest.of(0, count) - 첫 번째 페이지부터 시작하고, count만큼의 영화 리스트를 가져옵니다.
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Order.asc("ranking")));  // ranking 내림차순 정렬
        return movieRepository.findTopMovies(pageable);  // 반환된 페이지 내용을 가져옴
    }


    // 제목에 단어가 포함된 영화를 찾는 메서드
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title, Sort.by(Sort.Order.asc("ranking")));
    }


    @CacheEvict(value = "moviesCache", allEntries = true)
    public void updateMovies() {
        // 영화 데이터 업데이트 작업
        // 이때 캐시된 모든 영화 데이터를 삭제
    }


}
