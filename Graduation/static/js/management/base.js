$(function () {
    // hover event for nav bar
    let a = $('#nav-left ul .deactivate a');
    a.each(function () {
        $(this).hover(function () {
            $(this).css('background', 'none');
            $(this).css('color', '#727cf5');
            $(this).css('transition', 'color 0.5s');
            $(this).children('i').css('color', '#727cf5');
            $(this).children('i').css('transition', 'color 0.5s');
        }, function () {
            $(this).css('color', '#6c757d');
            $(this).children('i').css('color', '#6c757d');
        })
    });
    $('#logout').click(function () {
        $(location).attr('href', '/logout');
    });
    $('#setting').click(function () {
        $(location).attr('href', '/management/setting');
    });
});