package bing.Pan.sso.common.enums;

/**
 * @crea :Created by intelliJ IDEA 16.1.1 .
 * @auth :bing.Pan 15923508369@163.com .
 * @date :2017/1/18 11:02
 * @desc :统一返回状态码 10000 - 19999 消息提示
 *                     20000 - 29999 正常返回
 *                     30000 - 39999
 *                     40000 - 40000 客户端错误
 *                     50000 - 59999 服务端错误
 */
public enum ResponseCode {

    SUCCESS(20000, "success!",""),


    // ---------------------客户端请求异常-------------------
    CLIENT_PARAM_PARSE_ERROR    (40000, "参数解析失败/参数缺少!",""),
    CLIENT_PARAM_MISS           (40010, "参数丢失!",""),
    CLIENT_PARAM_ERROR          (4002, "参数错误",""),
    CLIENT_UNABLE_RESOLVE_ERR   (40030, "坏请求!",""),
    CLIENT_NO_SUPPORT_METHOD    (40040, "不支持该请求方法!",""),
    CLIENT_NO_SUPPORT_TYPE      (40050, "不支持当前媒体类型!",""),


    // ---------------------服务端处理异常-------------------
    SERVE_UNKNOWN_ERROR         (50000, "服务器端异常!",""),
    SERVE_LOGIC_PARAM_MISS      (51000, "必要逻辑参数为空!",""),




    // ---------------------登陆-------------------
    LOGIN_USER_MISS            (60010, "用户不存在!",""),
    LOGIN_USER_NOT_AVAILABLE   (60020, "账户已被禁用!",""),
    LOGIN_PASSWORD_ERR         (60030, "密码不正确!",""),
    LOGIN_TOME_OUT             (60040, "登陆过期!",""),
    LOGIN_NOT_LOGIN            (60050, "用户未登陆!","");



    private int code;

    private String description;

    private String detailMessage;

    ResponseCode(int code, String description, String detailMessage) {
        this.code = code;
        this.description = description;
        this.detailMessage = detailMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
