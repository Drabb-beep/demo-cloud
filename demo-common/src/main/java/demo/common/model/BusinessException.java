package demo.common.model;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @author sed
 * @ClassName: BusinessException
 * @Description: 业务异常类, 使用场景:程序并未出现执行异常情况,人为抛出异常信息。
 * 例如：登录功能,账号不存在或密码错误时,可抛出一个业务异常,自定义异常信息
 * @date 2021-08-31
 */
@Data
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 异常对应的返回码
     */
    private Integer code;

    /**
     * 异常对应的描述信息
     */
    private String message;

    private Throwable throwable;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = String.format("%s %s", message, cause.getMessage());
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message);
        this.code = code;
        this.message = message;
        this.throwable = throwable;
    }

    public BusinessException(ResultCode ResultCode) {
        this(ResultCode.getCode(), ResultCode.getMsg(), null);
    }

    public BusinessException(ResultCode ResultCode, Throwable throwable) {
        this(ResultCode.getCode(), ResultCode.getMsg(), throwable);
    }

    public BusinessException(ResultCode ResultCode, Object... args) {
        super(ResultCode.getMsg());
        String message = ResultCode.getMsg();
        try {
            message =
                    String.format("%s %s", ResultCode.getMsg(), OBJECT_MAPPER.writeValueAsString(args));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.message = message;
        this.code = ResultCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}