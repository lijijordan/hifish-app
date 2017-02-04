package com.hifish.app.api.rest;

import com.hifish.app.domain.RestErrorInfo;
import com.hifish.app.exception.DataFormatException;
import com.hifish.app.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 */
//@ControllerAdvice?
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {

    /**
     * The Log.
     */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * The Event publisher.
     */
    protected ApplicationEventPublisher eventPublisher;

    /**
     * The constant DEFAULT_PAGE_SIZE.
     */
    protected static final String  DEFAULT_PAGE_SIZE = "100";
    /**
     * The constant DEFAULT_PAGE_NUM.
     */
    protected static final String DEFAULT_PAGE_NUM = "0";

    /**
     * 请求返回成功
     */
    protected static final String RESPONSE_SUCCESS = "SUCCESS";
    /**
     * 请求返回失败
     */
    protected static final String RESPONSE_FAILURE = "FAILURE";

    /**
     * Handle data store exception rest error info.
     *
     * @param ex       the ex
     * @param request  the request
     * @param response the response
     * @return the rest error info
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());

        return new RestErrorInfo(ex, "You messed up.");
    }

    /**
     * Handle resource not found exception rest error info.
     *
     * @param ex       the ex
     * @param request  the request
     * @param response the response
     * @return the rest error info
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());

        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * Check resource found t.
     *
     * @param <T>      the type parameter
     * @param resource the resource
     * @return the t
     */
//todo: replace with exception mapping
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }


}