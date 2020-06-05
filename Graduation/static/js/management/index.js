$(function () {
    // let overview = $('#overview-chart');
    // overview.css('min-width', $(window).width() - 360);
    // overview.css('max-width', $(window).width() - 360);

    // let last_number = $('main #overview-data .data-item #data-item-number:last');
    // last_number.css('color', '#fa5c7c');
    // last_number.css('color', '#0acf97');

    // overview chart
    // let overview_chart = echarts.init(document.getElementById('overview-chart'));
    // overview_chart.setOption({
    //     backgroundColor: '#fff',
    //     color: ['#727cf5', '#0acf97'],
    //     title: {
    //         text: '综述',
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
    //             type: 'value'
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
    // });

    $('#nav-left-monitor').click(function () {
        $(location).attr('href', '/management/monitor');
    });
    $('#nav-left-write_list').click(function () {
        $(location).attr('href', '/management/write_list');
    });
    $('#nav-left-video').click(function () {
        $(location).attr('href', '/management/videotape');
    });

    // $(window).resize(function () {
    //     $('#analyze').remove();
    //     $("<scri" + "pt >" + "</scr" + "ipt>").attr({
    //         id: 'analyze',
    //         src: '../../static/js/management/index.js',
    //         type: 'text/javascript'
    //     }).appendTo($('body'));
    //     overview_chart.resize();
    // });
});

