$(function () {
    let check_number = false;
    let check_email = false;
    let check_name = false;
    let check_password = false;

    $('main #to-reg #reg').click(function () {
        $(location).attr('href', '/login');
    });

    $('#sp-number').on("input", function () {
        let help = $('#input-number .help-block');
        help.html("");
        if ($(this).val() === "") {
            help.html("用户编号不应为空");
            check_number = false;
        } else if (!/^\d{8}$/.test($(this).val())) {
            this.value = this.value.replace(/[^0-9]/g, '');
            help.html("用户编号应为8位数字");
            check_number = false;
        } else {
            check_number = true
        }
    });

    $('#sp-email').on("input", function () {
        let help = $('#input-email .help-block');
        help.html("");
        if ($(this).val() === "") {
            help.html("电子邮箱不应为空");
            check_email = false;
        } else if (!/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/.test($(this).val())) {
            help.html("请输入正确的电子邮箱地址");
            check_email = false;
        } else {
            check_email = true
        }
    });

    $('#sp-name').on("input", function () {
        let help = $('#input-name .help-block');
        help.html("");
        if ($(this).val() === "") {
            help.html("姓名不应为空");
            check_name = false;
        } else {
            check_name = true
        }
    });

    $('#sp-password').on("input", function () {
        let help = $('#input-password .help-block');
        help.html("以字母开头，只能包含字符、数字和下划线(6-18位)");
        if ($(this).val() === "") {
            help.html("密码不应为空");
            check_password = false;
        } else if (!/^[a-zA-Z]\w{5,17}$/.test($(this).val())) {
            check_password = false;
        } else {
            help.html("");
            check_password = true
        }
    });

    $('#commit').click(function () {
        if (/^\d{8}$/.test($('#sp-number').val())) {
            check_number = true;
        }
        if (/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/.test($('#sp-email').val())) {
            check_email = true;
        }
        if ($('#sp-name').val() !== "") {
            check_name = true;
        }
        if (/^[a-zA-Z]\w{5,17}$/.test($('#sp-password').val())) {
            check_password = true;
        }
        if (check_number && check_email && check_name && check_password) {
            $.ajax({
                type: 'POST',
                url: '/sign-up',
                data: {
                    number: $('main form #sp-number').val(),
                    email: $('main form #sp-email').val(),
                    name: $('main form #sp-name').val(),
                    password: $('main form #sp-password').val()
                },
                dataType: 'json',
                success: function (result) {
                    if (result["result"] === "success") {
                        $('#commit-alert .sy-content').html("注册成功，即将跳转到登录页面！");
                        syalert.syopen('commit-alert');
                        $('#commit-alert .sy-btn button').click(function () {
                            $(location).attr('href', '/login');
                        });
                        setTimeout(function () {
                            $(location).attr('href', '/login');
                        }, 3000);
                    } else if (result["result"] === "fail" && result["des"] === "Already have.") {
                        $('#commit-alert .sy-content').html("该用户已注册！");
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
    });
});