function login(){
    var loginName = $('#J-username').val();
    var password = $('#J-password').val();
    if (!loginName || !password) {
        $('#J-error').html('用户名密码不能为空！');
    } else {
        $.ajax({
            type:	"POST",
            url :  webConfig.webUrl+"/loginSysUser",
            dataType:"json",
            data: {
                loginName: loginName,
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



document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc
        //要做的事情
    }
    if(e && e.keyCode==113){ // 按 F2
        //要做的事情
    }
    if(e && e.keyCode==13){ // enter 键
        login();
    }
};