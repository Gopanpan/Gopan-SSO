//常量
var oldSysRoleName;


//加载页面
$(document).ready(function () {
    var sysRoleId=$("#sysRoleId").val();
    $.ajax({
            url: webConfig.webUrl + '/getSysRoleById',
            type: 'POST',
            dataType: 'json',
            data: {sysRoleId:sysRoleId}
        })
        .done(function(data) {
            if(data){
                if(data.code == "10000" && data.result){
                    $("#sysInfoId").val(data.result.sysId);  //系统名称
                    $("#sysRoleId").val(data.result.id);  //系统名称
                    $("#sysRoleName").val(data.result.roleName);  //系统名称
                    oldSysRoleName=data.result.roleName;
                }
            }
        });
});



//提交
$('#btnUpdate').click(function () {
    var sysRole = {},
        id = $("#sysRoleId").val();
    url = webConfig.webUrl + '/addSysRole';

    if (null != id && "" != id && id != "undefined") {
        sysRole.id = $("#sysRoleId").val(); //系统id
        url = webConfig.webUrl + '/updateSysRole';
    }

    sysRole.roleName = $("#sysRoleName").val();  //系统名称
    sysRole.sysId = $("#sysInfoId").val();  //系统名称

    if(oldSysRoleName==sysRole.roleName){
        layer.alert("角色名称未变化", {icon: 2});
        return false;
    }



    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sysRole)
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
