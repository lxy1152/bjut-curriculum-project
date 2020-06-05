function request() {
    $.ajax({
        url: "/write_list",
        type: "get",
        dataType: 'json',
        processData: false,
        contentType: false,
        success: function (result) {
            if (Object.keys(result).length === 0) {
                $('main #tip').show();
                $('main #write-list').hide();
            } else {
                $('#tip').hide();
                $('main #write-list').empty();
                let id = 1;
                $('main #write-list').append('<tr>\n' +
                    '                    <th>序号</th>\n' +
                    '                    <th>标题</th>\n' +
                    '                    <th style="width: 35%">内容</th>\n' +
                    '                    <th>时间</th>\n' +
                    '                </tr>');
                for (let key in result) {
                    let item = result[key];
                    $('main #write-list').append('<tr id="show"><td>' + id + '</td><td>' + item["title"] + '</td><td>' + item["content"] + '</td><td>' + item["time"] + '</td></tr>');
                    id++;
                }
                $('main #write-list td').click(function () {
                    let trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);
                    $('main #write-list').hide();
                    $('main').append('<div id="show-text" style="display: none"></div>');
                    let s = $('#show-text');
                    s.show();
                    s.empty();
                    s.append('<h2>' + result[Object.keys(result)[trSeq - 1]]["title"] + '</h2>');
                    s.append(result[Object.keys(result)[trSeq - 1]]["content"]);
                    $('#new-task').text("返回");
                });
            }
        },
        error: function (e) {
            console.log(e)
        }
    });
}

request();
var quill;
$('#new-task').click(function () {
    if ($(this).html() === "返回") {
        $('#show-text').remove();
        $('main #write-list').show();
        $(this).text("新建计划");
    } else if ($(this).html() === "新建计划") {
        $('main #tip').hide();
        $('main #write-list').hide();
        $('main').append('<div id="editor"><p>开始编写你的计划吧！</p></div>');
        quill = new Quill('#editor', {
            theme: 'snow'
        });
        $(this).text('保存并返回');
    } else {
        let content = quill.container.firstChild.innerHTML;
        $.ajax({
            url: "/save_write",
            type: "post",
            data: {
                content
            },
            dataType: 'json',
            success: function (result) {
            },
            error: function (e) {
                console.log(e)
            }
        });
        $('main #tip').show();
        $('main #write-list').show();
        $('#editor').remove();
        $('.ql-toolbar').remove();
        $(this).text('新建计划');
        request();
    }
});

$('#nav-left-index').click(function () {
    $(location).attr('href', '/management/index');
});
$('#nav-left-video').click(function () {
    $(location).attr('href', '/management/videotape');
});
$('#nav-left-monitor').click(function () {
    $(location).attr('href', '/management/monitor');
});
