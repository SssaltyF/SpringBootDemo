package org.yuy.demo.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.yuy.demo.domain.entity.User;
import org.yuy.demo.repository.UserDao;
import org.yuy.demo.repository.storage.BaseMongoStorage;

/**
 * @Author YuanYu
 * @Date 2022/7/6
 */
@Repository
public class UserDaoImpl extends BaseMongoStorage<User> implements UserDao {

  private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

  /**
   * 通过用户名查用户
   *
   * @param userName       用户名
   * @param collectionName 表名称
   * @return User
   */
  @Override
  public User queryUserByLoginName(String userName, String collectionName) {
    Query query = new Query();
    query.addCriteria(Criteria.where("loginName").is(userName).and("delFlag").is(false));
    User user = super.selectOne(query, collectionName);
    return user;
  }

  /**
   * 通过用户id查询用户
   *
   * @param id             用户Id
   * @param collectionName 表名称
   * @return User
   */
  @Override
  public User queryUserById(String id, String collectionName) {
    Query query = new Query().addCriteria(Criteria.where("_id").is(id).and("delFlag").is(false));
    return super.selectOne(query, collectionName);
  }

  /**
   * 通过unionid查询用户
   *
   * @param unionId        unionId
   * @param collectionName 表名称
   * @return 用户信息
   */
  @Override
  public User queryUserByWechatUserId(String unionId, String collectionName) {
    Query query = new Query().addCriteria(Criteria.where("unionid").is(unionId).and("delFlag").is(false));
    return super.selectOne(query, collectionName);
  }

  /**
   * 根据邮箱查询用户信息
   *
   * @param email          邮箱
   * @param collectionName 表名称
   * @return user
   */
  @Override
  public User findUserByEmail(String email, String collectionName) {
    Query query = new Query().addCriteria(Criteria.where("email").is(email).and("delFlag").is(false));
    return super.selectOne(query, collectionName);
  }

  /**
   * 新增一个用户
   *
   * @param user           用户信息
   * @param collectionName 表名称
   */
  @Override
  public void insertUser(User user, String collectionName) {
    super.save(user, collectionName);
  }

  /**
   * 修改用户信息
   *
   * @param user           用户信息
   * @param collectionName 表名称
   * @return 更新后的用户
   */
  @Override
  public User updateQAUser(User user, String collectionName) {
    Query query = new Query().addCriteria(
        Criteria.where("_id").is(user.getId()).and("delFlag").is(false));
    if (user.getLastModifyTime() != 0) {
      query.addCriteria(Criteria.where("lastModTime").is(user.getLastModifyTime()));
    }
    Update update = new Update();
    if (user.getLoginName() != null) {
      update.set("loginName", user.getLoginName());
    }

    if(user.getPassword() != null){
      update.set("password", user.getPassword());
    }

    update.set("lastModTime", System.currentTimeMillis());
    FindAndModifyOptions option = new FindAndModifyOptions();
    option.returnNew(true);
    return super.findAndModify(query, update, collectionName);
  }

  /**
   * 所有子类必须实现该抽象方法，用于获取泛型的class
   *
   * @return class
   */
  @Override
  protected Class<User> getEntityClass() {
    return User.class;
  }
}
