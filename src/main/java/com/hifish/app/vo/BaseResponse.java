package com.hifish.app.vo;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 16/7/11
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class BaseResponse {

    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 信息
     */
    private String message = "FAILURE";
    /**
     * 详细内容
     */
    private String details = "";

    /**
     * 返回DTO数据
     */
    private Object data;

    /**
     * Instantiates a new Base response.
     */
    public BaseResponse() {
    }

    /**
     * Instantiates a new Base response.
     *
     * @param message the message
     */
    public BaseResponse(String message) {
        if (message.equals("FAILURE")) {
            this.setCode(0);
        } else {
            this.setCode(1);
        }
        this.message = message;
    }

    /**
     * Instantiates a new Base response.
     *
     * @param code    the code
     * @param message the message
     * @param details the details
     */
    public BaseResponse(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        if (message.equals("FAILURE")) {
            this.setCode(0);
        } else {
            this.setCode(1);
        }
        this.message = message;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }
}
