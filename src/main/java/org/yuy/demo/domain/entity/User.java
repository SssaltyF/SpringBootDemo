package org.yuy.demo.domain.entity;

import org.springframework.data.annotation.Id;
import org.yuy.demo.constant.enumerate.UserSourceEnum;
import org.yuy.demo.domain.entity.builder.UserBuilder;

/**
 * 用户信息entity
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class User {

  /**
   * id
   */
  @Id
  private String id;

  /**
   * 登录名
   */
  private String loginName;

  private String password;

  private String email;

  /**
   * 用户微信ID  公众号接入为unionID  小程序接入为openID
   */
  private String wechatUserId;

  /**
   * 用户来源 0：unknown 1：微信 2：邮箱注册
   */
  private UserSourceEnum userSource;

  /**
   * 注册时间
   */
  private long registrationTime;

  /**
   * 最后修改时间
   */
  private long lastModifyTime;

  /**
   * 用户是否被删除
   */
  private boolean delFlag;

  public User() {
  }

  /**
   * 使用构造器模式建立User对象  一般用于参数较多的类  此处仅做样例，可以直接使用lombok注解实现相同功能
   *
   * @param builder User 构造器
   */
  public User(UserBuilder builder) {
    this.id = builder.getId();
    this.loginName = builder.getLoginName();
    this.password = builder.getPassword();
    this.email = builder.getEmail();
    this.wechatUserId = builder.getWechatUserId();
    this.userSource = builder.getUserSource();
    this.registrationTime = builder.getRegistrationTime();
    this.lastModifyTime = builder.getLastModifyTime();
    this.delFlag = false;
  }

  /**
   * 用于注册用户
   */
  public User(String id, String loginName, String password, String email, UserSourceEnum userSource, long registrationTime, long lastModifyTime) {
    this.id = id;
    this.loginName = loginName;
    this.password = password;
    this.email = email;
    this.userSource = userSource;
    this.registrationTime = registrationTime;
    this.lastModifyTime = lastModifyTime;
    this.delFlag = false;
  }

  /**
   * 用于微信用户
   */
  public User(String id, String wechatUserId, UserSourceEnum userSource, long registrationTime, long lastModifyTime) {
    this.id = id;
    this.wechatUserId = wechatUserId;
    this.userSource = userSource;
    this.registrationTime = registrationTime;
    this.lastModifyTime = lastModifyTime;
    this.delFlag = false;
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

  public UserSourceEnum getUserSource() {
    return userSource;
  }

  public void setUserSource(UserSourceEnum userSource) {
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

  public boolean isDelFlag() {
    return delFlag;
  }

  public void setDelFlag(boolean delFlag) {
    this.delFlag = delFlag;
  }
}
