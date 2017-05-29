
//加载页面
$(document).ready(function () {
    var id = $("#id").val();
    if(id){
        $.ajax({
            url: sysUser_findById_url,
            type: 'POST',
            dataType: 'json',
            data: {id: id}
        })
            .done(function (data) {
                serviceValidateErrorTipsMessage(data);

                if (data.code == "20000" && data.result) {
                    $("#id").val(data.result.id).attr("disabled",true);
                    $("#loginName").val(data.result.loginName).attr("disabled",true);
                    $("#realName").val(data.result.realName).attr("disabled",true);
                    $("#sex").val(data.result.sex).attr("disabled",true);
                    $("#phone").val(data.result.phone).attr("disabled",true);
                    $("#email").val(data.result.email).attr("disabled",true);
                    $("#birthday").val(data.result.birthday).attr("disabled",true);
                    $("#lastLogin").val(changeDateFormat(data.result.lastLogin)).attr("disabled",true);
                    $("#lastIp").val(data.result.lastIp).attr("disabled",true);
                    $("#createUserName").val(data.result.createUserName).attr("disabled",true);
                    $("#createTime").val(changeDateFormat(data.result.createTime)).attr("disabled",true);
                    $("#updateUserName").val(data.result.updateUserName).attr("disabled",true);
                    $("#updateTime").val(changeDateFormat(data.result.updateTime)).attr("disabled",true);
                    $("#available").val(availableFormat(data.result.available)).attr("disabled",true);
                }
            });
    }
});



//取消
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});
