package org.yuy.demo.repository;

import org.yuy.demo.domain.entity.User;

/**
 * @Author YuanYu
 * @Date 2022/7/6
 */
public interface UserDao {

  /**
   * 通过用户名查用户
   *
   * @param userName       用户名
   * @param collectionName 表名称
   * @return User
   */
  User queryUserByLoginName(String userName, String collectionName);

  /**
   * 通过用户id查询用户
   *
   * @param id             用户Id
   * @param collectionName 表名称
   * @return User
   */
  User queryUserById(String id, String collectionName);

  /**
   * 通过unionid查询用户
   *
   * @param unionId        unionId
   * @param collectionName 表名称
   * @return 用户信息
   */
  User queryUserByWechatUserId(String unionId, String collectionName);

  /**
   * 根据邮箱查询用户信息
   *
   * @param email          邮箱
   * @param collectionName 表名称
   * @return user
   */
  User findUserByEmail(String email, String collectionName);

  /**
   * 新增一个用户
   *
   * @param user         用户信息
   * @param collectionName 表名称
   */
  void insertUser(User user, String collectionName);

  /**
   * 修改用户信息
   *
   * @param user         用户信息
   * @param collectionName 表名称
   * @return 更新后的用户
   */
  User updateQAUser(User user, String collectionName);

}
