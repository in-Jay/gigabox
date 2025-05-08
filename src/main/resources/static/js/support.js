$(function(){
    $('.supportFaq .faq-all').addClass('show');
    $('.supportFaq .q-all').addClass('active');

    $('.supportFaq .q-all').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-all').addClass('show');
    });
    $('.supportFaq .q-res').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-res').addClass('show');
    });
    $('.supportFaq .q-pay').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-pay').addClass('show');
    });
    $('.supportFaq .q-theater').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-theater').addClass('show');
    });
    $('.supportFaq .q-store').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-store').addClass('show');
    });
    $('.supportFaq .q-page').on('click', function(){
        $('.supportFaq .q-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportFaq .faq-list').removeClass('show');
        $('.supportFaq .faq-page').addClass('show');
    });


    $('.supportFaq .faq-list ul li .q').on('click', function(){
        // 클릭한 .q의 형제 .a 요소
        var $answer = $(this).siblings('.a');
        var $arrow = $(this).find('img');
        var $question = $(this).find('.q-cont');

        // 다른 li의 모든 .a에서 'show' 클래스를 제거
        $('.supportFaq .faq-list ul li .a').not($answer).removeClass('show');
        $('.supportFaq .faq-list ul li .q img').not($arrow).removeClass('rotate');
        $('.supportFaq .faq-list ul li .q .q-cont').not($question).removeClass('font');

        // 클릭한 .a에 대해 'show' 클래스를 토글
        $answer.toggleClass('show');
        $arrow.toggleClass('rotate');
        $question.toggleClass('font')
    });




    $('.supportNotice .notice-all').addClass('show');
    $('.supportNotice .n-all').addClass('active');

    $('.supportNotice .n-all').on('click', function(){
        $('.supportNotice .notice-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportNotice .notice-list').removeClass('show');
        $('.supportNotice .notice-all').addClass('show');
    });
    $('.supportNotice .n-theater').on('click', function(){
        $('.supportNotice .notice-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportNotice .notice-list').removeClass('show');
        $('.supportNotice .notice-theater').addClass('show');
    });
    $('.supportNotice .n-branch').on('click', function(){
        $('.supportNotice .notice-cate').removeClass('active');
        $(this).addClass('active');
        $('.supportNotice .notice-list').removeClass('show');
        $('.supportNotice .notice-branch').addClass('show');
    });

});