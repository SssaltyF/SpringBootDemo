package org.yuy.demo.constant.enumerate;

import org.yuy.demo.utils.gson.GsonEnum;

/**
 * 用户来源枚举类
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public enum UserSourceEnum implements GsonEnum<UserSourceEnum> {

  UNKNOWN(0,"unknown"),
  WECHAT(1,"WeChat"),
  WEB_REGISTER(2,"register"),;

  private Integer id;
  private String value;

  UserSourceEnum(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer serialize(UserSourceEnum object) {
    return object.getId();
  }

  @Override
  public UserSourceEnum deserialize(String jsonEnum) {
    UserSourceEnum[] values = UserSourceEnum.values();
    for (UserSourceEnum value : values) {
      if (value.getValue().equals(jsonEnum)) {
        return value;
      }
    }
    return null;
  }
}
