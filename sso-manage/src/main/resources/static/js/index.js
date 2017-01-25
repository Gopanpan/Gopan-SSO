$(function () {
    //菜单点击
    // J_iframe
    $(".J_menuItem").on('click', function () {
        var url = $(this).attr('href');
        $("#J_iframe").attr('src', url);
        return false;
    });
});

//登出
function logOut() {
    $.ajax({
        type: "POST",
        url: webConfig.webUrl + "/manager/loginOut",
        dataType: "json",
        data: {},
        success: function (data) {
            if (data.code == "10000") {
                top.location.href = webConfig.webUrl + "/login";
            } else {
                layer.alert(data.message, {icon: 2});
            }
        }
    });
}