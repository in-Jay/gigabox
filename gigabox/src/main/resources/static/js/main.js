$(function(){
    var swiper = new Swiper(".mySwiper", {
        pagination: {
          el: ".swiper-pagination",
          type: "progressbar",
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      });


      var swiper = new Swiper(".mySwiper2", {
        slidesPerView: 6,
        spaceBetween: 20,
        loop:true,
        navigation: {
          nextEl: ".swiper-button-next2",
          prevEl: ".swiper-button-prev2",
        }
      });

        $(document).ready(function(){
        $('.q-cont > div').hide();
        $('.q-list ul li').click(function(){
            //클릭한 li의 data-set의 값을 가져와서 target에 저장
            var target =$(this).data('set');
            $('.q-cont > div').hide();
            //.+ dataset의 값 = 선택된 li와 같은 이름의 class가 보이게함
            $('.'+target).fadeIn();
            $('.q-list ul li').removeClass('active');
            $(this).addClass('active');
            //자동으로 첫번째 li를 선택함
        }).filter(':eq(0)').click();
    });


// 수량 및 총 금액 계산
    function initQuantityHandlers() {
        const price = parseInt($('meta[name="item-price"]').attr('content'), 10);
        const $quantityInput = $('#quantity');
        const $totalPriceElement = $('#total-price');

        function updateTotalPrice() {
            const quantity = parseInt($quantityInput.val(), 10);
            $totalPriceElement.text((price * quantity).toLocaleString() + "원");
        }

        $('#increase').click(function () {
            let quantity = parseInt($quantityInput.val(), 10);
            if (quantity < 10) {
                $quantityInput.val(quantity + 1);
                updateTotalPrice();
            }
        });

        $('#decrease').click(function () {
            let quantity = parseInt($quantityInput.val(), 10);
            if (quantity > 1) {
                $quantityInput.val(quantity - 1);
                updateTotalPrice();
            }
        });

        updateTotalPrice(); // 초기 총 금액 계산
    }

    // 모든 초기화 함수 실행
    function initialize() {
        initQuantityHandlers();
    }

    initialize();



//burger메뉴 들어가고 나가게 하기
    $(document).ready(function() {
        // 메뉴 열기
        $('#burgeOpen').click(function() {
            $('.burger').fadeIn(); // .burger 메뉴 표시
        });

        // 메뉴 닫기
        $('#closeMenu').click(function() {
            $('.burger').fadeOut(); // .burger 메뉴 숨기기
        });
    });


    //이벤트 슬라이드
    var swiper = new Swiper(".eventSwiper", {
          slidesPerView: 2,
          spaceBetween: 30,
          loop: true,
          pagination: {
            el: ".eventSwiper-pagination",
            type: "fraction",
          },
          navigation: {
            nextEl: ".eventSwiper-button-next",
            prevEl: ".eventSwiper-button-prev",
          },
        });


})