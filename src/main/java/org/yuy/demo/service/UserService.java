package org.yuy.demo.service;

import org.yuy.demo.domain.entity.User;
import org.yuy.demo.domain.vo.UserVO;

/**
 * @Author YuanYu
 * @Date 2022/7/6
 */
public interface UserService {

  /**
   * 通过Id查找用户
   *
   * @param id             用户Id
   * @param collectionName 数据库
   * @return User
   */
  User findById(String id, String collectionName);

  /**
   * 通过用户名查询用户
   *
   * @param userName       用户名
   * @param collectionName 数据库
   * @return User
   */
  User queryUserByLoginName(String userName, String collectionName);

  /**
   * 根据邮箱查询用户
   *
   * @param email          用户邮箱
   * @param collectionName 数据库
   * @return User
   */
  User findUserByEmail(String email, String collectionName);

  /**
   * 用户登录，网页用户校验是否注册，微信用户自动注册      返回用户id
   *
   * @param user         登录用户
   * @return 用户Id
   */
  String userLogin(UserVO user);

  /**
   * 更新用户信息，根据传来的字段，更新该字段信息
   *
   * @param collectionName 数据库
   * @param user 用户信息
   * @return User
   */
  User updateUser(UserVO user, String collectionName);

  /**
   * 平台用户注册
   *
   * @param userVO 用户信息 包含loginName password email
   * @return userID
   */
  String registerUser(UserVO userVO);
}
