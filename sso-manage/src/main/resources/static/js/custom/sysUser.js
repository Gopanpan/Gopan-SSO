//常量
var oldSysUserName;
var oldSysUserPassword;


//加载页面
$(document).ready(function () {
    var sysUserId = $("#sysUserId").val();
    $.ajax({
            url: webConfig.webUrl + '/getSysUserById',
            type: 'POST',
            dataType: 'json',
            data: {sysUserId: sysUserId}
        })
        .done(function (data) {
            if (data) {
                if (data.code == "10000" && data.result) {
                    $("#sysUserName").val(data.result.userName);
                    $("#sysUserPassword").val(data.result.password);
                    oldSysUserName = data.result.userName;
                    oldSysUserPassword = data.result.password;
                }
            }
        });
});


//提交
$('#btnUpdate').click(function () {

    var sysUserInfo = {},
        id = $("#sysUserId").val();
    url = webConfig.webUrl + '/addSysUser';


    if (null != id && "" != id && id != "undefined") {
        sysUserInfo.id = $("#sysUserId").val(); //用户id
        url = webConfig.webUrl + '/updateSysUser';
    }

    sysUserInfo.userName = $("#sysUserName").val();  //用户名称
    sysUserInfo.password = $("#sysUserPassword").val();  //用户密码


    if (sysUserInfo.userName == null || sysUserInfo.userName == "") {
        layer.alert("请输入用户名", {icon: 2});
        return false;
    }


    if (sysUserInfo.password == null || sysUserInfo.password == "") {
        layer.alert("请输入密码", {icon: 2});
        return false;
    }

    if (oldSysUserName == sysUserInfo.userName && oldSysUserPassword == sysUserInfo.password) {
        layer.alert("参数未变化", {icon: 2});
        return false;
    }


    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sysUserInfo)
        })
        .done(function (data) {
            if (data) {
                if (data.code == "10000") {
                    layer.alert(data.message, {icon: 1}, function () {
                        parent.layer.closeAll('iframe');
                    });
                    return;
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
