package com.gordon.springboot.exception;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.utils.BRUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GwExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(GwException.class)
    public BRUtils handleGwException(GwException e){
        logger.error(e.getMessage(), e);
//        BRUtils brUtils = new BRUtils();
//        brUtils.put(BRUtils.KEY_CODE,e.getCode());
//        brUtils.put(BRUtils.KEY_MSG,e.getMsg());
        return BRUtils.error(e.getErrorCode());
    }

    @ExceptionHandler(AuthorizationException.class)
    public BRUtils handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return BRUtils.error(ErrorContants.ERROR_403);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BRUtils handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return BRUtils.error(ErrorContants.ERROR_9000);
    }

    @ExceptionHandler(Exception.class)
    public BRUtils handleException(Exception e){
        logger.error(e.getMessage(), e);
        return BRUtils.error();
    }

}
