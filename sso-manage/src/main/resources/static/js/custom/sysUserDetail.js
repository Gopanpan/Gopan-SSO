//常量
var oldphone;
var oldaddress;
var oldsex;


//加载页面
$(document).ready(function () {
    var sysUserId=$("#sysUserId").val();
    $.ajax({
            url: webConfig.webUrl + '/getSysUserDetailById',
            type: 'POST',
            dataType: 'json',
            data: {sysUserId:sysUserId}
        })
        .done(function(data) {
            if(data){
                if(data.code == "10000" && data.result){
                    $("#sysUserId").val(data.result.id);
                    $("#sysUserUid").val(data.result.uid);
                    $("#phone").val(data.result.phone);
                    $("#address").val(data.result.address);
                    $("#sex").val(data.result.sex);
                    oldphone=data.result.phone;
                    oldaddress=data.result.address;
                    oldsex=data.result.sex;
                }
            }
        });
});



//提交
$('#btnUpdate').click(function () {
    var sysUserInfo = {},
        id = $("#sysUserId").val();

    if(id==null || id=="" || id =="undefined"){
        layer.alert("没有此用户", {icon: 2});
        return false;
    }


    sysUserInfo.id = $("#sysUserId").val();
    url = webConfig.webUrl + '/updatesysUserDetail';


    sysUserInfo.uid = $("#sysUserUid").val();
    sysUserInfo.phone = $("#phone").val();
    sysUserInfo.address = $("#address").val();
    sysUserInfo.sex = $("#sex").val();

    if(oldphone==sysUserInfo.phone && oldaddress==sysUserInfo.address && oldsex==sysUserInfo.sex){
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
