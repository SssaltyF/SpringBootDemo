package org.yuy.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yuy.demo.domain.entity.RetStruct;
import org.yuy.demo.domain.vo.UserVO;
import org.yuy.demo.service.UserService;
import org.yuy.demo.utils.gson.GsonFactory;

/**
 * 用户controller
 * todo：用户登出、邮箱校验、验证码、小程序获取openID、修改密码、忘记密码、删除用户。。。
 *
 * @Author YuanYu
 * @Date 2022/7/7
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * web用户注册  小程序用户直接调用登录接口
   *
   * @param userVO 用户信息 包含loginName password email
   * @return userId
   */
  @PostMapping(value = "/register", produces = "application/json; charset=UTF-8")
  public @ResponseBody
  String userRegister(@RequestBody UserVO userVO) {
    RetStruct retStruct;
    try {
      String userId = userService.registerUser(userVO);
      retStruct = userId != null ? new RetStruct(userId) : RetStruct.abnormal("error");
    } catch (Exception e) {
      LOG.error(e.getMessage());
      retStruct = RetStruct.abnormal(e.getMessage());
    }
    return GsonFactory.getGsonClient().toJson(retStruct);
  }

  /**
   * 用户登录
   *
   * @param userVO 用户信息 web用户包含loginName password  小程序用户包含wechatUserId（openID）
   * @return userId
   */
  @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
  public @ResponseBody
  String userLogin(@RequestBody UserVO userVO) {
    RetStruct retStruct;
    try {
      String userId = userService.userLogin(userVO);
      retStruct = userId != null ? new RetStruct(userId) : RetStruct.abnormal("error");
    } catch (Exception e) {
      LOG.error(e.getMessage());
      retStruct = RetStruct.abnormal(e.getMessage());
    }
    return GsonFactory.getGsonClient().toJson(retStruct);
  }
}
