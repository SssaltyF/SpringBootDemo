package org.yuy.demo.domain.entity.builder;

import org.springframework.data.annotation.Id;
import org.yuy.demo.constant.enumerate.UserSourceEnum;
import org.yuy.demo.domain.entity.User;

/**
 * User 构造者模式  此处仅做样例，可以直接使用lombok注解实现相同功能
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class UserBuilder {

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

  public User build(){
    return new User(this);
  }

  public UserBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public UserBuilder setLoginName(String loginName) {
    this.loginName = loginName;
    return this;
  }

  public UserBuilder setPassword(String password) {
    this.password = password;
    return this;
  }

  public UserBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder setWechatUserId(String wechatUserId) {
    this.wechatUserId = wechatUserId;
    return this;
  }

  public UserBuilder setUserSource(UserSourceEnum userSource) {
    this.userSource = userSource;
    return this;
  }

  public UserBuilder setRegistrationTime(long registrationTime) {
    this.registrationTime = registrationTime;
    return this;
  }

  public UserBuilder setLastModifyTime(long lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getLoginName() {
    return loginName;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getWechatUserId() {
    return wechatUserId;
  }

  public UserSourceEnum getUserSource() {
    return userSource;
  }

  public long getRegistrationTime() {
    return registrationTime;
  }

  public long getLastModifyTime() {
    return lastModifyTime;
  }
}
