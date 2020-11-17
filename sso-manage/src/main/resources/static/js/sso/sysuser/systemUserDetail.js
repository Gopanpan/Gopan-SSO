
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
            .done(function (result) {
                serviceValidateErrorTipsMessage(result);

                if (result.code == "20000" && result.data) {
                    $("#id").val(result.data.id).attr("disabled",true);
                    $("#loginName").val(result.data.loginName).attr("disabled",true);
                    $("#realName").val(result.data.realName).attr("disabled",true);
                    $("#sex").val(result.data.sex).attr("disabled",true);
                    $("#phone").val(result.data.phone).attr("disabled",true);
                    $("#email").val(result.data.email).attr("disabled",true);
                    $("#birthday").val(result.data.birthday).attr("disabled",true);
                    $("#lastLogin").val(changeDateFormat(result.data.lastLogin)).attr("disabled",true);
                    $("#lastIp").val(result.data.lastIp).attr("disabled",true);
                    $("#createUserName").val(result.data.createUserName).attr("disabled",true);
                    $("#createTime").val(changeDateFormat(result.data.createTime)).attr("disabled",true);
                    $("#updateUserName").val(result.data.updateUserName).attr("disabled",true);
                    $("#updateTime").val(changeDateFormat(result.data.updateTime)).attr("disabled",true);
                    $("#available").val(availableFormat(result.data.available)).attr("disabled",true);
                }
            });
    }
});



//确认
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});
