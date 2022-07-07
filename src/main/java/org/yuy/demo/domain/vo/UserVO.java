package org.yuy.demo.domain.vo;

import org.yuy.demo.constant.enumerate.UserSourceEnum;
import org.yuy.demo.domain.entity.User;

/**
 * userVO类  用于与前端交互的用户数据结构
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class UserVO {

  /**
   * id
   */
  private String id;

  /**
   * 登录名
   */
  private String loginName;

  /**
   * 密码 VO类中仅做接收 不允许返回前端
   */
  private String password;

  private String email;

  /**
   * 用户微信ID  公众号接入为unionID  小程序接入为openID
   */
  private String wechatUserId;

  /**
   * 用户来源 0：unknown 1：微信 2：邮箱注册
   */
  private Integer userSource;

  /**
   * 注册时间
   */
  private long registrationTime;

  /**
   * 最后修改时间
   */
  private long lastModifyTime;

  /**
   * 用户微信code
   */
  private String code;

  public UserVO() {
  }

  /**
   * 将用户实体类转为VO类 用于返回前端 仅传输可供前端展示的字段
   *
   * @param user User entity
   */
  public UserVO(User user) {
    if (user == null) {
      return;
    }
    if (user.getLoginName() != null) {
      this.loginName = user.getLoginName();
    }
    if (user.getId() != null) {
      this.id = user.getId();
    }
    this.lastModifyTime = user.getLastModifyTime();
  }




  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWechatUserId() {
    return wechatUserId;
  }

  public void setWechatUserId(String wechatUserId) {
    this.wechatUserId = wechatUserId;
  }

  public Integer getUserSource() {
    return userSource;
  }

  public void setUserSource(int userSource) {
    this.userSource = userSource;
  }

  public long getRegistrationTime() {
    return registrationTime;
  }

  public void setRegistrationTime(long registrationTime) {
    this.registrationTime = registrationTime;
  }

  public long getLastModifyTime() {
    return lastModifyTime;
  }

  public void setLastModifyTime(long lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
