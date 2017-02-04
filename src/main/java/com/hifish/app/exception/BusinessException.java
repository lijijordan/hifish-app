/**
 * 定义业务异常
 */
package com.hifish.app.exception;

/**
 * The type Business exception.
 *
 * @author yinhong
 */
public class BusinessException extends Exception {

  private static final long serialVersionUID = -5050745931951542954L;

  // 错误代码
  private Integer errorCode;

  /**
   * Instantiates a new Business exception.
   *
   * @param errorCode    the error code
   * @param errorMessage the error message
   */
  public BusinessException(Integer errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
  }

  /**
   * Instantiates a new Business exception.
   *
   * @param errorCode the error code
   * @param cause     the cause
   */
  public BusinessException(Integer errorCode, Throwable cause) {
    this(errorCode, null, cause);
  }

  /**
   * Instantiates a new Business exception.
   *
   * @param errorCode    the error code
   * @param errorMessage the error message
   * @param cause        the cause
   */
  public BusinessException(Integer errorCode, String errorMessage, Throwable cause) {
    super(errorMessage, cause);
    this.errorCode = errorCode;
  }

  /**
   * Gets error code.
   *
   * @return the error code
   */
  public Integer getErrorCode() {
    return errorCode;
  }

  public String toString() {
    String errorCode = getErrorCode().toString();
    String message = getLocalizedMessage();
    return (message != null) ? (errorCode + ": " + message) : errorCode;
  }

}
