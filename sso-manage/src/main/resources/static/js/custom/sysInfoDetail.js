//常量
var oldSysInfoName;
var oldsubStationStatus;

//加载页面
$(document).ready(function () {
    var sysInfoId = $("#sysInfoId").val();
    $.ajax({
               url: webConfig.webUrl + '/getSysInfoById',
               type: 'POST',
               dataType: 'json',
               data: {sysInfoId: sysInfoId}
           })
        .done(function (data) {
            if (data) {
                if (data.code == "10000" && data.result) {
                    $("#sysInfoName").val(data.result.name);  //系统名称

                    var subStationStatus= document.getElementsByName("subStationStatus");
                    //var subStationStatus=  $(".subStationStatus");

                    for(var i=0; i<subStationStatus.length; i ++){
                        if(subStationStatus[i].value==data.result.subStationStatus){
                            subStationStatus[i].checked="checked";
                        }
                    }

                    oldSysInfoName = data.result.name;
                    oldsubStationStatus = data.result.subStationStatus;

                }
            }
        });
});

//提交
$('#btnUpdate').click(function () {
    var sysInfo = {},
        id = $("#sysInfoId").val();
    url = webConfig.webUrl + '/addSysInfo';

    if (null != id && "" != id && id != "undefined") {
        sysInfo.id = $("#sysInfoId").val(); //系统id
        url = webConfig.webUrl + '/updateSysInfo';
    }

    sysInfo.name = $("#sysInfoName").val();  //系统名称
    sysInfo.subStationStatus = $(':input[name="subStationStatus"]:checked ').val();

    if (sysInfo.name == null || sysInfo.name == "" || sysInfo.name == '') {
        layer.alert("系统名称为空", {icon: 2});
        return false;
    }

    if (oldSysInfoName == sysInfo.name && oldsubStationStatus == sysInfo.subStationStatus) {
        layer.alert("参数未变化", {icon: 2});
        return false;
    }

    $.ajax({
               url: url,
               type: 'POST',
               dataType: 'json',
               contentType: "application/json; charset=utf-8",
               data: JSON.stringify(sysInfo)
           })
        .done(function (data) {
            if (data) {
                if (data.code == "10000") {
                    layer.alert(data.message, {icon: 1}, function () {
                        parent.layer.closeAll('iframe');
                        return;
                    });

                } else {
                    layer.alert(data.message, {icon: 2});
                }
            }
        });
});

//取消
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});


