$(function () {
    $(window).scrollTop(0);

    let video_list;

    $.ajax({
        type: "GET",
        url: "/videotape_list",
        dataType: "json",
        success: function (res) {
            video_list = res;
        }
    });

    let width = $(window).width() - 780;
    // set width of overview chart
    // let ochart = $('#overview-chart');
    // ochart.css('min-width', $(window).width() - width - 350);
    // ochart.css('max-width', $(window).width() - width - 350);
    // // init overview chart
    // let overview_chart = echarts.init(document.getElementById('overview-chart'));
    // let overview_option = {
    //     backgroundColor: '#fff',
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
    echart.css('min-width', $(window).width() - width - 350);
    echart.css('max-width', $(window).width() - width - 350);
    // init expression chart
    let expression_chart = echarts.init(document.getElementById('expression-chart'));
    let expression_option = {
        backgroundColor: '#fff',
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
    eyechart.css('min-width', $(window).width() - 360);
    eyechart.css('max-width', $(window).width() - 360);
    // init expression chart
    let eye_chart = echarts.init(document.getElementById('eye-chart'));
    let eye_option = {
        backgroundColor: '#fff',
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
                data: [],
                hoverable: false
            }
        ]
    };
    eye_chart.setOption(eye_option);

    let wavechart = $('#wave-chart');
    wavechart.css('min-width', $(window).width() - 360);
    wavechart.css('max-width', $(window).width() - 360);
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
                data: [0,]
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

    let dp = $('.J-datepicker-day');
    let tip_container = $('main #no-info-tip');
    let choose = $('main #video-choose');
    let monitor = $('#monitor');
    if (width > 473) {
        width = 473;
    }
    monitor.css('min-width', width);
    monitor.css('max-width', width);
    tip_container.css('min-width', $(window).width() - 360);
    tip_container.css('max-width', $(window).width() - 360);
    choose.css('min-width', $(window).width() - 360);
    choose.css('max-width', $(window).width() - 360);
    dp.css('min-width', $(window).width() - 360);
    dp.css('max-width', $(window).width() - 360);
    dp.datePicker({
        hide: function () {
            let date = this.$input.eq(0).val().split(" ")[0];
            let tip = $('main #no-info-tip span');
            let ul = $('main #video-choose ul');
            ul.empty();
            if (date in video_list) {
                if (video_list[date].length === 0) {
                    tip_container.show();
                    choose.hide();
                    monitor.hide();
                    // ochart.hide();
                    echart.hide();
                    eyechart.hide();
                    wavechart.hide();
                    tip.html("暂无数据");
                } else {
                    tip_container.hide();
                    choose.show();
                    for (let video of video_list[date]) {
                        ul.append("<li><img src='/get_source/video+" + date + "+" + video + ".jpg" + "'><span>" + video.replace("-", "点") + "分的视频" + "</span></li>");
                    }
                }
            } else {
                tip_container.show();
                choose.hide();
                monitor.hide();
                // ochart.hide();
                echart.hide();
                eyechart.hide();
                wavechart.hide();
                tip.html("暂无数据");
            }
            $('main #video-choose ul li').each(function () {
                $(this).click(function () {
                    choose.hide();
                    let title = $(this).children("span").get(0).innerHTML;
                    title = title.replace("点", "-");
                    title = title.replace("分的视频", "");
                    let expression_data_list;
                    let eye_data_list;
                    let wave_data_list;
                    $.ajax({
                        type: "GET",
                        url: "/get_source/data+camera_eye+" + date + "+" + title + ".csv",
                        dataType: "json",
                        success: function (result) {
                            expression_data_list = result["expression"];
                            eye_data_list = result["p_cor"];
                        }
                    });
                    $.ajax({
                        type: "GET",
                        url: "/get_source/data+wave+" + date + "+" + title + ".csv",
                        dataType: "json",
                        success: function (result) {
                            wave_data_list = result["wave"];
                        }
                    });
                    let mv = $('#monitor video');
                    mv.empty();
                    mv.append("<source src='/get_source/video+" + date + "+" + title + ".mp4" + "' type='video/mp4'>");
                    monitor.show();
                    expression_option.series[0].data[0].value = [];
                    eye_option.series[0].data = [];
                    // ochart.show();
                    // ochart.css("display", "inline-block");
                    // overview_chart.resize();
                    echart.show();
                    echart.css("display", "inline-block");
                    expression_chart.resize();
                    eyechart.show();
                    eye_chart.resize();
                    wavechart.show();
                    wave_chart.resize();

                    let i = 0;
                    let expression_eye_interval = setInterval(function () {
                        if (i > expression_data_list.length - 1 || i > eye_data_list.length - 1) {
                            clearInterval(expression_eye_interval);
                        }
                        expression_option.series[0].data[0].value = [
                            expression_data_list[i]["anger"],
                            expression_data_list[i]["disgust"],
                            expression_data_list[i]["fear"],
                            expression_data_list[i]["happiness"],
                            expression_data_list[i]["neutral"],
                            expression_data_list[i]["sadness"],
                            expression_data_list[i]["surprise"],
                        ];
                        expression_chart.setOption(expression_option);
                        eye_option.series[0].data.push([
                            eye_data_list[i]["p_cor_x"] + eyechart.width() / 2,
                            eye_data_list[i]["p_cor_x"] + eyechart.height() / 2,
                            Math.random()
                        ]);
                        if (eye_option.series[0].data.length > 100) {
                            eye_option.series[0].data.shift();
                        }
                        eye_chart.setOption(eye_option);
                        i++;
                    }, 1000);

                    let date_arr = wave_option.xAxis[0].data;
                    let med_data_arr = wave_option.series[0];
                    let att_data_arr = wave_option.series[1];
                    let j = 0;
                    let time = 1;
                    let wave_interval = setInterval(function () {
                        if (j > wave_data_list.length - 1) {
                            clearInterval(wave_interval);
                        }
                        date_arr.push(time);
                        if (date_arr.length > 7) {
                            date_arr.shift();
                        }
                        att_data_arr.data.push(wave_data_list[j]["Attention"]);
                        med_data_arr.data.push(wave_data_list[j + 1]["Meditation"]);
                        if (med_data_arr.data.length > 7) {
                            med_data_arr.data.shift();
                        }
                        if (att_data_arr.data.length > 7) {
                            att_data_arr.data.shift();
                        }
                        wave_chart.setOption(wave_option);
                        j = j + 2;
                        time++;
                    }, 900);
                })
            });
        }
    });

    $('#nav-left-index').click(function () {
        $(location).attr('href', '/management/index');
    });
    $('#nav-left-write_list').click(function () {
        $(location).attr('href', '/management/write_list');
    });
    $('#nav-left-monitor').click(function () {
        $(location).attr('href', '/management/monitor');
    });
});