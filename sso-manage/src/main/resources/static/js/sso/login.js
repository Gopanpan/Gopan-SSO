function login(){
    var loginName = $('#J-username').val();
    var password = $('#J-password').val();
    if (!loginName || !password) {
        $('#J-message').html('用户名密码不能为空!');
        return;
    }

    $.ajax({
        type: "POST",
        url : user_login_url,
        dataType:"json",
        data: {
            loginName: loginName,
            password: md5(password)
        },
        beforeSend: function(){
            $('#J-message').html('正在登陆,请稍后。。。');
        },
        success: function(result){
            if(result.code == 20000) {
                $('#J-message').html('登陆成功!');
                top.location.href = system_main_url;
            }else{
                $('#J-message').html(result.message);
            }
        }
    });


}


$('#J-submit').bind('click', function () {
    login();
});



//登出
function logOut() {
    $.ajax({
        type: "POST",
        url: base_serve_url_config.webUrl + "/manager/loginOut",
        dataType: "json",
        data: {},
        success: function (data) {
            if (data.code == "10000") {
                top.location.href = base_serve_url_config.webUrl + "/login";
            } else {
                layer.alert(data.message, {icon: 2});
            }
        }
    });
}



document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc
    }
    if(e && e.keyCode==113){ // 按 F2
    }
    if(e && e.keyCode==13){ // enter 键
        login();
    }
};