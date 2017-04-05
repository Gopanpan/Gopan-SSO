function login(){
    var username = $('#J-username').val();
    var password = $('#J-password').val();
    if (!username || !password) {
        $('#J-error').html('用户名密码不能为空！');
    } else {
        $.ajax({
            type:	"POST",
            url :  webConfig.webUrl+"/loginSysUser",
            dataType:"json",
            data: {
                userName: username,
                password: md5(password)
            },
            success: function(data){
                if(data.code == "20000") {
                    top.location.href = webConfig.webUrl+"/ssoMain";
                }else{
                    $('#J-error').html(data.message);
                }
            }
        });
    }
}


$('#J-submit').bind('click', function () {
    login();
});