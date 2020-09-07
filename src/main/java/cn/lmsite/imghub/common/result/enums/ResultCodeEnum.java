package cn.lmsite.imghub.common.result.enums;

/**
 * 结果代码枚举
 *
 * @author Jonny.Chang  ( https://jonnyhub.com )  @jonny6015
 */
public enum ResultCodeEnum {

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //               成功 与 系统异常                   //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    SUCCESS("000000", "成功"),

    SYSTEM_ERROR("999999", "系统异常"),

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          未知系统错误[000001-000099]            //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    IDEMPOTENT("000001", "幂等性控制"),

    ClONE_UNSUPPORT("000002", "不支持克隆"),

    QUERY_ERROR("000003", "查询异常"),

    CREATE_ERROR("000004", "新增异常"),

    UPDATE_ERROR("000005", "更新失败"),

    UNKNOWN_ERROR("000006", "未知错误"),

    ILLEGAL_REQUEST("000007", "非法请求"),

    UPLOAD_ERROR("000008", "上传失败"),

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          请求校检相关错误[000100-000199]         //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    PARAM_INVALID("000100", "请求参数非法"),

    DATE_INVALID("000101", "请求日期为空或非法"),

    UNKNOWN_ID("000103", "未知数据"),

    PARAM_IS_NULL("000104", "请求参数为空"),

    ACCESS_TOKEN_IS_NULL("000105", "AccessToken为空"),

    ACCESS_TOKEN_IS_INVALID("000106", "无效AccessToken"),

    UNKNOWN_USER("000107", "无效用户"),

    CHECK_FAIL("000108", "校验失败"),

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          内部处理相关[000200-000299]            //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    MODEL_CONVERT_ERROR("000200", "域模型转换错误"),

    JSON_PARSE_ERROR("000201", "JSON解析错误"),

    URI_CONVERT_ERROR("000202", "URI转换错误"),

    ALARM_FORMAT_ERROR("000203", "报警信息格式错误"),

    ALARM_SEND_ERROR("000204", "发送报警消息异常"),

    DATE_PARSE_ERROR("000205", "日期格式转化错误"),

    STATE_MACHINE_ERROR("000207", "状态机状态跳转异常"),

    FILE_PARSE_ERROR("000209", "文件解析错误"),

    RULE_PARSE_ERROR("000210", "规则解析错误"),

    ENV_PARSE_ERROR("000211", "环境解析异常"),

    DATA_PARSE_ERROR("000212", "数据解析异常"),

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          业务逻辑相关[000300-000399]            //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    UNKNOWN_ADMIN_NAME("000300", "未注册用户"),

    UNKNOWN_JOINSIGN_OR_DOMAIN("000301", "未知JoinSign或域名"),

    UNKNOWN_SERVICE_ID("000302", "未知服务id"),

    EMERGENCY_NOT_EXIST("000303", "应急事件不存在"),

    NO_PERMISSION("000304", "没有权限"),

    DB_QUERY_RESULT_NULL("000306", "数据库查询结果不存在"),

    STATUS_NOT_ALLOWED_TO_UPDATE("000307", "当前状态不允许数据变更"),

    DATA_ALREADY_EXISTS("000308", "数据库中已经存在数据"),

    OBJECT_CLONE_FAIL("000309", "克隆对象失败"),

    AUTHORIZATION_REFUSE("000310", "权限不存在"),

    NOT_IN_WHITELIST("000319", "不在白名单列表中"),

    IS_IN_BLACKLIST("000320", "命中黑名单规则"),

    REPETITION_OPERATION("000334", "重复操作"),

    OPERATION_FAILED("000335", "操作失败"),

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          调用外部接口相关[000400-000499]         //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    //          数据库操作相关[000500-000599]           //
    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\//
    QUERY_DB_FAILED("000501", "查询数据库数据失败"),
    QUERY_IS_NOT_EXIT("000502", "查询数据不存在"),
    INSERT_DB_FAILED("000503", "插入数据库数据失败"),
    UPDATE_DB_FAILED("000504", "更新数据库数据失败"),
    DELETE_DB_FAILED("000505", "删除数据库数据失败"),

    ;

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 描述说明
     */
    private final String msg;

    /**
     * 私有构造函数。
     *
     * @param code 枚举编码
     * @param msg  描述说明
     */
    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the msg.
     */
    public String getMsg() {
        return msg;
    }

}