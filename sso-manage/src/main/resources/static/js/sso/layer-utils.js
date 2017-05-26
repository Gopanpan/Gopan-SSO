

/**
 * 校验服务器端是否返回异常,异常弹出tips,页面不做任何跳转刷新,无异常则放行
 * @param data
 */
function serviceValidateErrorTipsMessage(data){
    if(data.code != 20000 || data.code != '20000'){
        var message = "异常代码   "+data.code+"<br/>异常摘要   "+data.message+"<br/>详细信息   "+ data.detailMessage;
        layer.msg(message,{icon:5,title:'服务器返回异常',time:4000});
    }
}




/**
 * 校验服务器端返回数据，成功/异常都提示tips,关闭/不处理当前页面
 * @param data             服务器端返回的处理结果数据
 * @param successOperate   服务器端处理结果为成功后的页面操作 为true是执行回调函数，false不执行
 * @param errorOperate     服务器端处理结果为失败后的页面操作 为true是执行回调函数，false不执行
 */
function serviceValidateHandleCurrent (data, successOperate, errorOperate) {

    if (data) {
        if (data.code == "20000") {
            layer.msg(data.message, {icon: 1,title:'服务器返回成功',time:2000}, function () {
                if(successOperate ==true){
                    parent.layer.closeAll('iframe');
                }
            });
        } else {
            var message = "异常代码   "+data.code+"<br/>异常摘要   "+data.message+"<br/>详细信息   "+ data.detailMessage;
            layer.msg(message, {icon: 5,title:'服务器返回异常',time:4000}, function () {
                if(errorOperate ==true){
                    parent.layer.closeAll('iframe');
                }
            });

        }
    }

}






/**
 * 客户端校验
 * @param data
 */
function frontErrorValidate(data){
    layer.msg("页面校验失败<br/>异常内容："+data,{icon:5,time:3000});
    return false;
}

