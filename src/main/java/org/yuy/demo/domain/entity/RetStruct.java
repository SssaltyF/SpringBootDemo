package org.yuy.demo.domain.entity;

import com.google.gson.annotations.Expose;
import org.yuy.demo.constant.Constant;

/**
 * 通用返回前端对象
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class RetStruct<T> {

  /**
   * 日志id，在记录异常日志的同时反馈前端，用于定位异常日志。
   */
  private String exceptionId;

  /**
   * 业务结构
   */
  private T data;

  /**
   * 返回编码 1：成功  -1：失败
   */
  private int retCode;

  /**
   * 附加信息
   */
  private String message;

  public RetStruct() {
  }

  /***
   * 返回成功的构造
   * @param data
   */
  public RetStruct(T data) {
    this.data = data;
    this.retCode = Constant.RETURN_CODE_SUCCESS;
    this.message = Constant.RETURN_MESSAGE_SUCCESS;
  }

  /**
   * 返回失败的静态方法（未知异常）
   *
   * @return QARetStruct
   */
  public static RetStruct abnormal(String errorMessage) {
    RetStruct ret = new RetStruct();
    ret.setMessage(Constant.RETURN_MESSAGE_ERROR);
    ret.setRetCode(Constant.RETURN_CODE_ERROR);
    return ret;
  }

  public String getExceptionId() {
    return exceptionId;
  }

  public void setExceptionId(String exceptionId) {
    this.exceptionId = exceptionId;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getRetCode() {
    return retCode;
  }

  public void setRetCode(int retCode) {
    this.retCode = retCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
