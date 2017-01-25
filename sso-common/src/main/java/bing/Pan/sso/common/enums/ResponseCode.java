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

    USER_ROLE_NOT_EXIST("50700", "用户角色不存在"),

    NO_SYS_INFO("50701", "系统为空!"),

    ROOT_PID_CANNOT_DEL("50702", "根节点不能删除!"),

    PID_CANNOT_DELETE("50703", "父节点不能删除!"),

    RESOURCE_NOT_FUND("50704", "未获取到选中资源!"),

    ROLE_EXIST("50705", "角色名称已经存在!"),

    PARAM_NOT_PASS("50706", "参数校验未通过!"),

    USER_REGISTER_FAIL("50707", "用户注册失败!"),

    RESOURCE_NAME_CANNOT_NULL("50708", "资源名称不能为空!"),

    INCLUDE_ERROR_STR("50709", "列信息不能有非法字符!"),

    USERNAME_NOTNULL("60001", "用户名不能为空"),

    PWD_NOTNULL("60002", "密码不能为空"),

    USERNAME_REPEAT("60003", "用户名不能重复"),

    USER_NOT_EXIST("60004", "用户不存在"),

    PWD_WRONG("60005", "用户密码错误"),

    PWD_REPEAT("60006", "旧密码与新密码不能重复!"),

    OLD_PWD_WRONG("60007", "旧密码不正确!"),

    TOKEN_WRONG("60008", "无效的Token!"),

    TICKET_WRONG("60009", "无效的Ticket!"),

    SYS_INFO_EXIST_RESOURCE("60010", "该系统对应资源非空!"),

    SYS_ROLE_USERD("60011", "该角色已被引用,无法删除!"),

    NO_SYS_USER("60012", "用户不存在!"),

    NO_SYS_ROLE("60013", "角色不存在!");


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
