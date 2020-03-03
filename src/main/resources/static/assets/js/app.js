$(function () {

    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function () {
        $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function () {
        $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
    });


    var dataType = $('body').attr('data-type');
    for (key in pageData) {
        if (key == dataType) {
            pageData[key]();
        }
    }

    $('.tpl-switch').find('.tpl-switch-btn-view').on('click', function () {
        $(this).prev('.tpl-switch-btn').prop("checked", function () {
            if ($(this).is(':checked')) {
                return false
            } else {
                return true
            }
        })
        // console.log('123123123')

    })
})
// ==========================
// 侧边导航下拉列表
// ==========================

$('.tpl-left-nav-link-list').on('click', function () {
    $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
        .end()
        .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
})
// ==========================
// 头部导航隐藏菜单
// ==========================

$('.tpl-header-nav-hover-ico').on('click', function () {
    $('.tpl-left-nav').toggle();
    $('.tpl-content-wrapper').toggleClass('tpl-content-wrapper-hover');
});
// 页面数据
var pageData = {
    // ===============================================
    // 首页
    // ===============================================
    'index': function indexData() {


        var myScroll = new IScroll('#wrapper', {
            scrollbars: true,
            mouseWheel: true,
            interactiveScrollbars: true,
            shrinkScrollbars: 'scale',
            preventDefault: false,
            fadeScrollbars: true
        });

        var myScrollA = new IScroll('#wrapperA', {
            scrollbars: true,
            mouseWheel: true,
            interactiveScrollbars: true,
            shrinkScrollbars: 'scale',
            preventDefault: false,
            fadeScrollbars: true
        });

        var myScrollB = new IScroll('#wrapperB', {
            scrollbars: true,
            mouseWheel: true,
            interactiveScrollbars: true,
            shrinkScrollbars: 'scale',
            preventDefault: false,
            fadeScrollbars: true
        });



        // document.addEventListener('touchmove', function(e) { e.preventDefault(); }, false);

        // ==========================
        // 百度图表A http://echarts.baidu.com/
        // ==========================

        var echartsA = echarts.init(document.getElementById('tpl-echarts-A'));
        option = {

            tooltip: {
                trigger: 'axis',
            },
            legend: {
                data: ['邮件', '媒体', '资源']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: true,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }],

            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '邮件',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [120, 132, 101, 134, 90, 230, 210],
                itemStyle: {
                    normal: {
                        color: '#59aea2'
                    },
                    emphasis: {

                    }
                }
            },
            {
                name: '媒体',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [220, 182, 191, 234, 290, 330, 310],
                itemStyle: {
                    normal: {
                        color: '#e7505a'
                    }
                }
            },
            {
                name: '资源',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [150, 232, 201, 154, 190, 330, 410],
                itemStyle: {
                    normal: {
                        color: '#32c5d2'
                    }
                }
            }
            ]
        };
        echartsA.setOption(option);
    },
    // ===============================================
    // 图表页
    // ===============================================
    'chart': function chartData() {
        // ==========================
        // 百度图表A http://echarts.baidu.com/
        // ==========================

        var echartsC = echarts.init(document.getElementById('tpl-echarts-C'));


        optionC = {
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                top: '0',
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            legend: {
                data: ['蒸发量', '降水量', '平均温度']
            },
            xAxis: [{
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }],
            yAxis: [{
                type: 'value',
                name: '水量',
                min: 0,
                max: 250,
                interval: 50,
                axisLabel: {
                    formatter: '{value} ml'
                }
            },
            {
                type: 'value',
                name: '温度',
                min: 0,
                max: 25,
                interval: 5,
                axisLabel: {
                    formatter: '{value} °C'
                }
            }
            ],
            series: [{
                name: '蒸发量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name: '降水量',
                type: 'bar',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name: '平均温度',
                type: 'line',
                yAxisIndex: 1,
                data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
            }
            ]
        };

        echartsC.setOption(optionC);

        var echartsB = echarts.init(document.getElementById('tpl-echarts-B'));
        optionB = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                x: 'center',
                data: ['某软件', '某主食手机', '某水果手机', '降水量', '蒸发量']
            },
            radar: [{
                indicator: [
                    { text: '品牌', max: 100 },
                    { text: '内容', max: 100 },
                    { text: '可用性', max: 100 },
                    { text: '功能', max: 100 }
                ],
                center: ['25%', '40%'],
                radius: 80
            },
            {
                indicator: [
                    { text: '外观', max: 100 },
                    { text: '拍照', max: 100 },
                    { text: '系统', max: 100 },
                    { text: '性能', max: 100 },
                    { text: '屏幕', max: 100 }
                ],
                radius: 80,
                center: ['50%', '60%'],
            },
            {
                indicator: (function () {
                    var res = [];
                    for (var i = 1; i <= 12; i++) {
                        res.push({ text: i + '月', max: 100 });
                    }
                    return res;
                })(),
                center: ['75%', '40%'],
                radius: 80
            }
            ],
            series: [{
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: { normal: { areaStyle: { type: 'default' } } },
                data: [{
                    value: [60, 73, 85, 40],
                    name: '某软件'
                }]
            },
            {
                type: 'radar',
                radarIndex: 1,
                data: [{
                    value: [85, 90, 90, 95, 95],
                    name: '某主食手机'
                },
                {
                    value: [95, 80, 95, 90, 93],
                    name: '某水果手机'
                }
                ]
            },
            {
                type: 'radar',
                radarIndex: 2,
                itemStyle: { normal: { areaStyle: { type: 'default' } } },
                data: [{
                    name: '降水量',
                    value: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 75.6, 82.2, 48.7, 18.8, 6.0, 2.3],
                },
                {
                    name: '蒸发量',
                    value: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 35.6, 62.2, 32.6, 20.0, 6.4, 3.3]
                }
                ]
            }
            ]
        };
        echartsB.setOption(optionB);
        var echartsA = echarts.init(document.getElementById('tpl-echarts-A'));
        option = {

            tooltip: {
                trigger: 'axis',
            },
            legend: {
                data: ['邮件', '媒体', '资源']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: true,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }],

            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '邮件',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [120, 132, 101, 134, 90, 230, 210],
                itemStyle: {
                    normal: {
                        color: '#59aea2'
                    },
                    emphasis: {

                    }
                }
            },
            {
                name: '媒体',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [220, 182, 191, 234, 290, 330, 310],
                itemStyle: {
                    normal: {
                        color: '#e7505a'
                    }
                }
            },
            {
                name: '资源',
                type: 'line',
                stack: '总量',
                areaStyle: { normal: {} },
                data: [150, 232, 201, 154, 190, 330, 410],
                itemStyle: {
                    normal: {
                        color: '#32c5d2'
                    }
                }
            }
            ]
        };
        echartsA.setOption(option);
    }
}
$(function(){
    /*全选按钮状态显示判断*/
$("#checklist").find("input[type='checkbox']").click(function(){
     /*初始化选择为TURE*/
     $("#all")[0].checked = true;
     /*获取未选中的*/
     var nocheckedList = new Array();
     $("#checklist").find('input:not(:checked)').each(function() {
         nocheckedList.push($(this).val());
     });

     /*状态显示*/
     if (nocheckedList.length == $("#checklist").find('input').length) {
         $("#all")[0].checked = false;
     }else if(nocheckedList.length ==0){
         $("#all")[0].checked = true;
     }else if(nocheckedList.length){
         $("#all")[0].checked = false;
     }
});
// 全选/取消
$("#all").click(function(){
 // alert(this.checked);
  if ($(this).is(':checked')) {
         $("#checklist").find('input').each(function(){
              $(this).prop("checked",true);
         });

   } else {
        $("#checklist").find('input').each(function(){
              $(this).removeAttr("checked",false);
              // 根据官方的建议：具有 true 和 false 两个属性的属性，
              // 如 checked, selected 或者 disabled 使用prop()，其他的使用 attr()
              $(this).prop("checked",false); 
        });
   }
});
});
//注册提交
$('#signin').on("click", function () {
    var formObject = {};
    var formArray = $("#peopleInfo").serializeArray();
    $.each(formArray, function (i, item) {
        formObject[item.name] = item.value;
    });
    $.ajax({
        url: "/people/signin.action",
        type: "post",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(formObject),
        dataType: "text",
        success: function (data) {
            ajaxobj = eval("(" + data + ")");
            if (ajaxobj.res == "1") {
                window.location.href = "/people/login";
                alert("注册成功!");
            } else
                alert("请勿重复注册");
        },
        error: function (e) {
            alert("错误！！");
        }
    });
}
)
//登录验证
$('#login_sub').on("click", function () {
    var formObject = {};
    var formArray = $("#login").serializeArray();
    $.each(formArray, function (i, item) {
        formObject[item.name] = item.value;
    });
    $.ajax({
        url: "/people/login.action",
        type: "post",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(formObject),
        dataType: "text",
        success: function (data) {
            ajaxobj = eval("(" + data + ")");
            if (ajaxobj.res == "1") {
                var people = $.session.get('people');
                console.log(people);
                window.location.href = "/index";
            } else {
                $("#alter_mess").html("密码或者用户名错误！")
            }
        }
    });
})
//实验室注册
$('#label_sub').on("click", function () {
    var formObject = {};
    var formArray = $("#labelInfo").serializeArray();
    $.each(formArray, function (i, item) {
        formObject[item.name] = item.value;
    });
    $.ajax(
        {
            url: "/label/signLabel.action",
            type: "post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(formObject),
            dataType: "text",
            success: function (data) {
                ajaxobj = eval("(" + data + ")");
                if (ajaxobj.res == "1") {
                    alert("实验室注册成功！");
                } else {
                    $("#alter_mess").html("实验室已被注册请勿重复注册！")
                }
            }
        }
    )
})
//学校注册
$('#school_sub').on('click', function () {
    var formObject = {};
    var formArray = $("#schoolInfo").serializeArray();
    $.each(formArray, function (i, item) {
        formObject[item.name] = item.value;
    });
    $.ajax(
        {
            url: "/school/signSchool.action",
            type:"post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(formObject),
            dataType: "text",
            success: function (data) {
                ajaxobj = eval("(" + data + ")");
                if (ajaxobj.res == "1") {
                    alert("学校注册成功！");
                } else {
                    $("#alter_mess").html("学校已被注册请勿重复注册！")
                }
            }
        }
    )
})
//项目注册
$('#project_sub').on('click', function () {
    var projectMap = new Map();
    projectMap.set("pname",$("#project-name").val());
    projectMap.set("pnum",$("#pnum").val());
    projectMap.set("teanum",$("#teacher option:selected").val());
    projectMap.set("label",$("#label option:selected").val());
    let obj= Object.create(null);
    for (let[k,v] of projectMap) {
        obj[k] = v;
    }
    console.log(projectMap);
    $.ajax(
        {
            url: "/project/signProject.action",
            type:"post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(obj),
            dataType: "text",
            success: function (data) {
                ajaxobj = eval("(" + data + ")");
                if (ajaxobj.res == "1") {
                    alert("项目注册成功，等待指导老师审核！");
                } else {
                    $("#alter_mess").html("项目已被注册请勿重复注册！")
                }
            }
        }
    )
})
function people() {
    var people = $.session.get('people');
    if (people.plimit == 1) {
        alert("权限不足");
    }
}
//教师审核项目
$("#checkPro_sub").click(function(){
    let $select = [];
    var $chkBoxes = $('#checklist').find('input:checked');   //找到被选中的checkbox集
    if ($chkBoxes.length == 0) {         //如果不勾选弹出警告框
        alert('请至少选择一个项目');
        return false;
      }
      console.log($chkBoxes)
      //遍历被选中的checkbox集
      $($chkBoxes).each(function () { 
        $select.push($(this).data('pnum') );   //找到对应checkbox中data-id属性值，然后push给空数组$ids
      });
    console.log($select);
    $.ajax(
        {
            url: "/projectinfo/updateProjectsta.action",
            type:"post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify($select),
            dataType: "text",
            success: function (data) {
                ajaxobj = eval("(" + data + ")");
                if (ajaxobj.res == "1") {
                    alert("更新完毕");
                } else {
                    $("#alter_mess").html("更新失败")
                }
            }
        }
    )
})
//学生审核项目
$("#joinPro_sub").click(function(){
    let $select = [];
    let $chkBoxes = $('#checklist').find('input:checked');   //找到被选中的checkbox集
    if ($chkBoxes.length == 0) {         //如果不勾选弹出警告框
        alert('请至少选择一个项目');
        return false;
      }
      console.log($chkBoxes)
      //遍历被选中的checkbox集
      $($chkBoxes).each(function () { 
        $select.push($(this).data('pnum') );   //找到对应checkbox中data-id属性值，然后push给空数组$ids
      });
    console.log($select);
    $.ajax(
        {
            url: "/ppl/agreePpl.action",
            type:"post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify($select),
            dataType: "text",
            success: function (data) {
                ajaxobj = eval("(" + data + ")");
                if (ajaxobj.res == true) {
                    alert("已经成功加入");
                } else if(ajaxobj.res == false) {
                    alert("未能成功审核");
                }else
                {
                    console.log(ajaxobj.res.length);
                    let n  = [] 
                    n.push(ajaxobj.res-1);
                    alert("已经通过" + n +"个项目，请勿重复审核！");
                }
            }
        }
    )
})
$("#check").click(function(){
    let formObject = {};
    let formArray = $("#check_self").serializeArray();
    $.each(formArray, function (i, item) {
        formObject[item.name] = item.value;
    });
    $.ajax(
        {
            url: "/recordtime/record.action",
            type:"post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(formObject),
            dataType: "text",
            success: function (data) {

            }
        }
    )
})