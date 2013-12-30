package org.opensource.webapp.framework.service;

/**
 * 服务层的异常
 * Created by panmingzhi on 13-12-28.
 */
public class WebAppServiceException extends RuntimeException {

    public WebAppServiceException(String message) {
        super(message);
    }

    public WebAppServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
