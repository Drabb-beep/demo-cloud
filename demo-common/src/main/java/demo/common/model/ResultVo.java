package demo.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = -3960261604608758516L;
    private Integer code;
    private String msg;
    private T data;
    public static <T> ResultVo<T> success() {
        return new ResultVo<>();
    }

    /**
     * 成功,默认状态码,返回消息,自定义返回数据
     * @param data 自定义返回数据
     * @param <T>  返回类泛型,不能为String
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(data);
    }

    /**
     * 成功,默认状态码,自定义返回消息,返回数据
     * @param msg  自定义返回消息
     * @param data 自定义返回数据
     * @param <T>  返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> success(String msg, T data) {
        return new ResultVo<>(msg, data);
    }
    /**
     * 成功,默认状态码,自定义返回消息,无返回数据
     *
     * @param msg 自定义返回消息
     * @param <T> 返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> success(String msg) {
        return new ResultVo<>(msg);
    }
    /**
     * 失败,默认状态码,返回消息,无返回数据
     * @param <T> 返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> error() {
        return new ResultVo<>(ResultCode.ERROR);
    }
    /**
     * 失败,默认状态码,自定义返回消息,无返回数据
     * @param <T> 返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> error(String msg) {
        return new ResultVo<>(ResultCode.ERROR.getCode(), msg);
    }
    /**
     * 失败,自定义状态码,返回消息,无返回数据
     * @param code 自定义状态码
     * @param msg  自定义返回消息
     * @param <T>  返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> error(Integer code, String msg) {
        return new ResultVo<>(code, msg);
    }
    /**
     * 失败,使用CodeMsg状态码,返回消息,无返回数据
     * @param ResultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> msg  返回消息
     * @param <T>        返回类泛型
     * @return 通用返回ResultVo
     */
    public static <T> ResultVo<T> error(ResultCode ResultCode) {
        return new ResultVo<>(ResultCode);
    }
    /**
     * 成功构造器,无返回数据
     */
    private ResultVo() {
        this(ResultCode.SUCCESS);
    }
    /**
     * 成功构造器,自定义返回数据
     * @param data 返回数据
     */
    private ResultVo(T data) {
        this(ResultCode.SUCCESS, data);
    }
    /**
     * 成功构造器,自定义返回消息,无返回数据
     * @param msg 返回消息
     */
    private ResultVo(String msg) {
        this(ResultCode.SUCCESS.getCode(), msg);
    }
    /**
     * 构造器,自定义状态码,返回消息
     * @param code 状态码
     * @param msg  返回消息
     */
    private ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**
     * 成功构造器,自定义返回信息,返回数据
     * @param msg  返回信息
     * @param data 返回数据
     */
    private ResultVo(String msg, T data) {
        this(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 构造器,自定义状态码,返回消息,返回数据
     * @param code 状态码
     * @param msg  返回消息
     * @param data 返回数据
     */
    private ResultVo(Integer code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }
    /**
     * 构造器,使用CodeMsg状态码与返回信息,自定义返回数据
     * @param ResultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> msg  返回消息
     * @param data       返回数据
     */
    private ResultVo(ResultCode ResultCode, T data) {
        this(ResultCode);
        this.data = data;
    }

    /**
     * 构造器,使用CodeMsg状态码与返回信息
     * @param ResultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> msg  返回消息
     */
    private ResultVo(ResultCode ResultCode) {
        this(ResultCode.getCode(), ResultCode.getMsg());
    }

}
