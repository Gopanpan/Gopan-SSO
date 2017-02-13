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

    SUCCESS("20000", "success"),

    CLIENT_PARAM_ERR("40001", "参数错误"),

    CLIENT_UNABLE_RESOLVE_ERR("40004", "坏请求"),

    SERVE_UNKNOWN_ERROR("50000", "服务器端异常!");


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
