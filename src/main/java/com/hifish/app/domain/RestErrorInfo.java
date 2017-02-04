package com.hifish.app.domain;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * The type Rest error info.
 */
/*
 * A sample class for adding error information in the response
 */
@XmlRootElement
public class RestErrorInfo {
    /**
     * The Detail.
     */
    public final String detail;
    /**
     * The Message.
     */
    public final String message;

    /**
     * Instantiates a new Rest error info.
     *
     * @param ex     the ex
     * @param detail the detail
     */
    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
