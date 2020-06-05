$(function () {
    if (re_entry) {
        $('#commit-alert .sy-content').html("登录失效，请重新登录！");
        syalert.syopen('commit-alert');
        $('#commit-alert .sy-btn button').click(function () {
            syalert.syhide('commit-alert');
        });
    }

    let check_name = false;
    let check_password = false;

    $('main #to-reg #reg').click(function () {
        $(location).attr('href', '/sign-up');
    });

    $('#login-name').on("input", function () {
        let help = $('#input-name .help-block');
        help.html("");
        if ($(this).val() === "") {
            help.html("用户名不应为空");
            check_name = false;
        } else if (!/^\d{8}$/.test($(this).val())) {
            help.html("请输入正确的用户编号");
            check_name = false;
        } else {
            check_name = true;
        }
    });

    $('#login-password').on("input", function () {
        let help = $('#input-password .help-block');
        help.html("");
        if ($(this).val() === "") {
            help.html("密码不应为空");
            check_password = false;
        } else {
            check_password = true
        }
    });

    $('#commit').click(function () {
        if (/^\d{8}$/.test($('#login-name').val())) {
            check_name = true;
        }
        if ($('#login-password').val() !== "") {
            check_password = true;
        }
        if (check_name && check_password) {
            $.ajax({
                type: 'POST',
                url: '/login',
                data: {
                    name: $('main form #login-name').val(),
                    password: $('main form #login-password').val()
                },
                dataType: 'json',
                success: function (result) {
                    if (result["result"] === "success") {
                        $('#commit-alert .sy-content').html("登录成功！");
                        syalert.syopen('commit-alert');
                        $('#commit-alert .sy-btn button').click(function () {
                            $(location).attr('href', '/management/index');
                        });
                        setTimeout(function () {
                            $(location).attr('href', '/management/index');
                        }, 3000);
                    } else if (result["result"] === "fail" && result["des"] === "No such user.") {
                        $('#commit-alert .sy-content').html("该用户未注册或信息填写错误！");
                        syalert.syopen('commit-alert');
                        $('#commit-alert .sy-btn button').click(function () {
                            syalert.syhide('commit-alert');
                        });
                    } else {
                        $('#commit-alert .sy-content').html("未知错误，请稍候再试！");
                        syalert.syopen('commit-alert');
                        $('#commit-alert .sy-btn button').click(function () {
                            syalert.syhide('commit-alert');
                        });
                    }
                },
                error: function () {
                    $('#commit-alert .sy-content').html("不能正常请求，请检查你的网络配置！");
                    syalert.syopen('commit-alert');
                    $('#commit-alert .sy-btn button').click(function () {
                        syalert.syhide('commit-alert');
                    });
                }
            });
        } else {
            $('#commit-alert .sy-content').html("请正确填写信息！");
            syalert.syopen('commit-alert');
            $('#commit-alert .sy-btn button').click(function () {
                syalert.syhide('commit-alert');
            });
        }
    })
});