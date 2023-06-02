package demo.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum ResultCodeEnum {
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    BIZ_ERROR(2,"业务异常"),
    FILE_OUT_MAX(3,"文件超出最大限制"),
    FILE_FORMAT_ERROR(5,"文件格式不正确"),
    PARAM_ERROR(6,"参数错误"),
    JSON_FORMAT_ERROR(7,"Json解析异常"),
    SQL_ERROR(8,"Sql解析异常"),
    NETWORK_TIMEOUT(9,"网络超时"),
    UNKNOWN_INTERFACE(10,"未知的接口"),
    REQ_MODE_NOT_SUPPORTED(11,"请求方式不支持"),
    SYS_ERROR(500,"系统异常");
    /** 状态码*/
    private Integer code;
    /**状态信息*/
    private String msg;

    private ResultCodeEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

}
