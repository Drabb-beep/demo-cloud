package com.demo.vite.config;
import demo.common.model.BusinessException;
import demo.common.model.ResultCode;
import demo.common.model.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Mr peng
 * 2020-6-11 09:20:54
 * 描述：全局的异常都会走向这里统一处理！
 */
@RestControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public Object errorHandle(Exception e){
        String errMsg = e.getMessage();
        if(e instanceof BusinessException || (e instanceof RuntimeException && (errMsg!=null && errMsg.contains("BusinessException")))){
            logger.error(errMsg);
            return ResultVo.error(ResultCode.BIZ_ERROR);
        }else {
            logger.error("", e);
            return ResultVo.error(ResultCode.SYS_ERROR);
        }

    }
}
