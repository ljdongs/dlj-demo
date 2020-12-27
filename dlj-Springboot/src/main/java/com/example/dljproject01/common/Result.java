package com.example.dljproject01.common;

import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.naming.NoPermissionException;
import java.io.Serializable;


@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int CHECK_FAIL = 1;
    private static final int SUCCESS = 0;
    private static final int UNKNOWN_EXCEPTION = -99;

    /**
     * 状态码：
     * 0  - 成功
     * >0 - 已知异常
     * <0 - 未知异常
     */
    private int code = SUCCESS;
    private T data;
    private String message = "请求成功";

    public Result(T data){
        super();
        this.data = data;
    }

    /**
     * 用于需要手动指定SUCCESS的返回消息
     * @param message
     * @return
     */
    public Result(String message){
        super();
        this.data = (T)message;
        this.message = message;
    }

    /**
     * 用于ControllerAdvice + ExceptionHandler统一捕获异常后调用
     * @param e
     */
    public Result(Throwable e){
        super();
        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 没有权限
        if (e instanceof NoPermissionException) {
            this.code = CHECK_FAIL;
            this.message = e.getMessage();
        }
        // @Validated实体类参数校验异常
        else if(e instanceof MethodArgumentNotValidException){
            StringBuilder sb = new StringBuilder();
            for(FieldError fieldError : ((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors()){
                sb.append(String.format("%s.%s %s，当前值：%s ；  ",fieldError.getObjectName(),fieldError.getField(),fieldError.getDefaultMessage(),fieldError.getRejectedValue()));
            }
            this.code = CHECK_FAIL;
            this.message = "验证失败，请检查！" + sb;
        }

        else {
            // 对于未知的异常，此处可以考虑发邮件
            this.code = UNKNOWN_EXCEPTION;
            this.message = e.getMessage();
        }
    }

}

