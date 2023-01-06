package top.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.api.utils.Code;
import top.api.utils.Msg;

/**
 * springMVC异常处理器
 */
@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {

    /**
     * 未知异常
     * @param ex
     * @return
     */
   @ExceptionHandler(Exception.class)
    public Msg doException(Exception ex){
        log.error(ex.getMessage());
        return new Msg(Code.CODE_ERR,"服务器繁忙,请稍后重试!",null);
    }

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ControllerException.class)
    public Msg doControllerException(ControllerException ex){
        log.error(ex.getMessage());
        return new Msg(ex.getCode(), ex.getMessage(), null);
    }

    /**
     * 系统异常
     * @param ex
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public Msg doSystemException(SystemException ex){
        log.error(ex.getMessage());
        return new Msg(ex.getCode(),ex.getMessage(),null);
    }

}
