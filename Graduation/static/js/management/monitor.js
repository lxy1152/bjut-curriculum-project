$(function () {
    $(window).scrollTop(0);

    document.onkeydown = function (e) {//解禁F5刷新
        e = window.event || e;
        let keycode = e.keyCode || e.which;
        if (keycode === 116) {
            $(location).attr('href', '/');
        }
    };

    // set width of monitor
    let monitor = $('#monitor');
    let width = $(window).width() - 790;
    if (width > 470) {
        width = 470;
    }
    monitor.css('min-width', width);
    monitor.css('max-width', width);

    $('#nav-left-index').click(function () {
        $(location).attr('href', '/management/index');
    });
    $('#nav-left-write_list').click(function () {
        $(location).attr('href', '/management/write_list');
    });
    $('#nav-left-video').click(function () {
        $(location).attr('href', '/management/videotape');
    });

    // set width of overview chart
    // let ochart = $('#overview-chart');
    // ochart.css('min-width', $(window).width() - width - 370);
    // ochart.css('max-width', $(window).width() - width - 370);
    // // init overview chart
    // let overview_chart = echarts.init(document.getElementById('overview-chart'));
    // let overview_option = {
    //     color: ['#727cf5', '#0acf97'],
    //     title: {
    //         text: '综合分析',
    //         textStyle: {
    //             color: '#6c757d'
    //         },
    //         padding: 20
    //     },
    //     tooltip: {
    //         trigger: 'axis'
    //     },
    //     legend: {
    //         data: ['冥想度', '专注度'],
    //         y: 'bottom',
    //         itemGap: 20,
    //         textStyle: {
    //             fontSize: 14
    //         }
    //     },
    //     calculable: true,
    //     xAxis: [
    //         {
    //             type: 'category',
    //             boundaryGap: false,
    //             data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    //         }
    //     ],
    //     yAxis: [
    //         {
    //             show: true
    //         }
    //     ],
    //     series: [
    //         {
    //             name: '冥想度',
    //             type: 'line',
    //             smooth: true,
    //             // itemStyle: {normal: {areaStyle: {type: 'default'}}},
    //             itemStyle: {
    //                 normal: {
    //                     lineStyle: {
    //                         color: '#727cf5',
    //                         width: 4
    //                     }
    //                 },
    //             },
    //             data: [47, 40, 50, 90, 60, 70, 65]
    //         },
    //         {
    //             name: '专注度',
    //             type: 'line',
    //             smooth: true,
    //             itemStyle: {
    //                 normal: {
    //                     lineStyle: {
    //                         color: '#0acf97',
    //                         width: 4
    //                     }
    //                 }
    //             },
    //             data: [60, 65, 55, 30, 30, 20, 25]
    //         }
    //     ]
    // };
    // overview_chart.setOption(overview_option);

    // set width of expression chart
    let echart = $('#expression-chart');
    echart.css('min-width', $(window).width() - width - 370);
    echart.css('max-width', $(window).width() - width - 370);
    // init expression chart
    let expression_chart = echarts.init(document.getElementById('expression-chart'));
    let expression_option = {
        backgroundColor: '#fff',
        color: ['#727cf5', '#0acf97'],
        title: {
            text: '表情数据分析',
            textStyle: {
                color: '#6c757d'
            },
            padding: 20
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            x: 'center',
            y: 'bottom',
            data: ['表情占比']
        },
        polar: [
            {
                indicator: [
                    {text: '生气', max: 100},
                    {text: '厌恶', max: 100},
                    {text: '害怕', max: 100},
                    {text: '高兴', max: 100},
                    {text: '中性', max: 100},
                    {text: '悲伤', max: 100},
                    {text: '惊讶', max: 100}
                ]
            }
        ],
        calculable: true,
        series: [
            {
                name: '表情数据分析',
                type: 'radar',
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                data: [
                    {
                        value: [],
                        name: "表情占比"
                    }
                ]
            }
        ]
    };
    expression_chart.setOption(expression_option);

    // set width of expression chart
    let eyechart = $('#eye-chart');
    eyechart.css('min-width', $(window).width() - 350);
    eyechart.css('max-width', $(window).width() - 350);
    // init expression chart
    let eye_chart = echarts.init(document.getElementById('eye-chart'));
    let eye_option = {
        backgroundColor: '#fff',
        color: ['#727cf5', '#0acf97'],
        title: {
            text: '眼睛数据分析',
            textStyle: {
                color: '#6c757d'
            },
            padding: 20
        },
        series: [
            {
                type: 'heatmap',
                data: [[0, 0, 0]],
                hoverable: false
            }
        ]
    };
    eye_chart.setOption(eye_option);

    let wavechart = $('#wave-chart');
    wavechart.css('min-width', $(window).width() - 350);
    wavechart.css('max-width', $(window).width() - 350);
    let wave_chart = echarts.init(document.getElementById('wave-chart'));
    let wave_option = {
        backgroundColor: '#fff',
        color: ['#727cf5', '#0acf97'],
        title: {
            text: '脑波数据分析',
            textStyle: {
                color: '#6c757d'
            },
            padding: 20
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['冥想度', '专注度'],
            y: 'bottom',
            itemGap: 20,
            textStyle: {
                fontSize: 14
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: [new Date().toLocaleTimeString().replace(/^\D*/, '')]
            }
        ],
        yAxis: [
            {
                show: true
            }
        ],
        series: [
            {
                name: '冥想度',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        },
                        lineStyle: {
                            color: '#727cf5',
                            width: 4
                        }
                    },
                },
                data: [0,]
            },
            {
                name: '专注度',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        },
                        lineStyle: {
                            color: '#0acf97',
                            width: 4
                        }
                    }
                },
                data: [0,]
            }
        ]
    };
    wave_chart.setOption(wave_option);

    // resize event
    $(window).resize(function () {
        width = $(window).width() - 680;
        if (width > 480) {
            width = 480;
        }
        monitor.css('min-width', width);
        monitor.css('max-width', width);
        // ochart.css('min-width', $(window).width() - width - 370);
        // ochart.css('max-width', $(window).width() - width - 370);
        $('#monitorjs').remove();
        $("<scri" + "pt >" + "</scr" + "ipt>").attr({
            id: 'analyze',
            src: '../../static/js/management/monitor.js',
            type: 'text/javascript'
        }).appendTo($('body'));
        // overview_chart.resize();
        expression_chart.resize();
        eye_chart.resize();
        wave_chart.resize();
    });

    // display the small window
    $(document).scroll(function () {
        let img = $('#monitor img');
        let img_height = img.offset().top + img.height() - 3;
        let scroll_height = $(document).scrollTop();
        let small_window = $('#small-window');
        if (scroll_height >= img_height) {
            small_window.show();
        } else {
            small_window.hide();
        }
    });

    // ajax to /detect/wave_res
    setInterval(function () {
        $.ajax({
            url: "/detect/wave",
            type: "get",
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result) {
                let timestamp = result["timestamp"];
                let data = result["data"];
                let date_arr = wave_option.xAxis[0].data;
                let med_data_arr = wave_option.series[0];
                let att_data_arr = wave_option.series[1];

                date_arr.push(new Date().toLocaleTimeString().replace(/^\D*/, ''));
                if (date_arr.length > 7) {
                    date_arr.shift();
                }
                med_data_arr.data.push(data["Meditation"]);
                att_data_arr.data.push(data["Attention"]);
                if (med_data_arr.data.length > 7) {
                    med_data_arr.data.shift();
                }
                if (att_data_arr.data.length > 7) {
                    att_data_arr.data.shift();
                }
                wave_chart.setOption(wave_option);
            },
            error: function (e) {
                console.log(e);
            }
        })
    }, 1000);

    // ajax to /detect/camera
    setInterval(function () {
        $.ajax({
            url: "/detect/camera",
            type: "get",
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (result) {
                let timestamp = result["timestamp"];
                if (result["expression"]["emotion"]) {
                    expression_option.series[0].data[0].value = [
                        result["expression"]["emotion"]["anger"],
                        result["expression"]["emotion"]["disgust"],
                        result["expression"]["emotion"]["fear"],
                        result["expression"]["emotion"]["happiness"],
                        result["expression"]["emotion"]["neutral"],
                        result["expression"]["emotion"]["sadness"],
                        result["expression"]["emotion"]["surprise"],
                    ];
                    expression_chart.setOption(expression_option);
                } else {
                    expression_option.series[0].data[0].value = [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0];
                    expression_chart.setOption(expression_option);
                }
                if (result["eye"]) {
                    eye_option.series[0].data.push([
                        result["eye"]["p_cor_x"] + eyechart.width() / 2,
                        result["eye"]["p_cor_x"] + eyechart.height() / 2,
                        Math.random()
                    ]);
                    if (eye_option.series[0].data.length > 100) {
                        eye_option.series[0].data.shift();
                    }
                    eye_chart.setOption(eye_option);
                }
            },
            error: function (e) {
                console.log(e)
            }
        })
    }, 500);
});