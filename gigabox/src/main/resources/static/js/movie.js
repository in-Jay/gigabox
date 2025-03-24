$(function(){
    $('.like-btn').click(function(e){
        e.preventDefault();
        $(this).find('.like-img').find('img').toggleClass('toggle')
    });
});