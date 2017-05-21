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

    SUCCESS("20000", "success!"),


    // ---------------------客户端请求异常-------------------
    CLIENT_PARAM_ERR("40000", "参数解析失败/参数缺少!"),

    CLIENT_PARAM_MISS("40001", "参数丢失!"),

    CLIENT_UNABLE_RESOLVE_ERR("40004", "坏请求!"),

    CLIENT_NO_SUPPORT_METHOD("40005","不支持该请求方法!"),

    CLIENT_NO_SUPPORT_TYPE("40015"," 不支持当前媒体类型!"),

    // ---------------------服务端处理异常-------------------
    SERVE_UNKNOWN_ERROR("50000", "服务器端异常!"),


    // ---------------------登陆-------------------
    LOGIN_USER_MISS("6001","用户不存在!"),
    LOGIN_USER_NOT_AVAILABLE("6002","账户已被禁用!"),
    LOGIN_PASSWORD_ERR("6002","密码不正确!"),
    LOGIN_TOME_OUT("6003","登陆过期!");



    private String code;

    private String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
