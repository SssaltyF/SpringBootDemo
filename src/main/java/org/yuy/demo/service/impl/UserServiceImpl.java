package org.yuy.demo.service.impl;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.yuy.demo.constant.Constant;
import org.yuy.demo.constant.enumerate.UserSourceEnum;
import org.yuy.demo.domain.entity.User;
import org.yuy.demo.domain.entity.builder.UserBuilder;
import org.yuy.demo.domain.vo.UserVO;
import org.yuy.demo.repository.UserDao;
import org.yuy.demo.repository.storage.RedisStorage;
import org.yuy.demo.service.UserService;
import org.yuy.demo.utils.mail.MailUtil;

/**
 * @Author YuanYu
 * @Date 2022/7/6
 */
@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserDao userDao;

  @Autowired
  RedisStorage redisStorage;

  @Autowired
  private MailUtil mailUtil;

  /**
   * 通过Id查找用户
   *
   * @param id             用户Id
   * @param collectionName 数据库
   * @return User
   */
  @Override
  public User findById(String id, String collectionName) {
    return userDao.queryUserById(id, collectionName);
  }

  /**
   * 通过用户名查询用户
   *
   * @param userName       用户名
   * @param collectionName 数据库
   * @return User
   */
  @Override
  public User queryUserByLoginName(String userName, String collectionName) {
    return userDao.queryUserByLoginName(userName, collectionName);
  }

  /**
   * 根据邮箱查询用户
   *
   * @param email          用户邮箱
   * @param collectionName 数据库
   * @return User
   */
  @Override
  public User findUserByEmail(String email, String collectionName) {
    return userDao.findUserByEmail(email, collectionName);
  }

  /**
   * 用户登录，返回用户id
   *
   * @param userVO 新注册用户
   * @return 用户Id
   */
  @Override
  public String userLogin(UserVO userVO) {
    if (userVO == null || userVO.getUserSource() == null) {
      // TODO: 2022/7/6 throw exception
      return null;
    }
    //标识用户Id
    String loginUserId = null;
    if (UserSourceEnum.WEB_REGISTER.getId().equals(userVO.getUserSource())) {
      //注册用户登录
      loginUserId = this.webUserLogin(userVO);
    } else if (UserSourceEnum.WECHAT.getId().equals(userVO.getUserSource())) {
      //微信用户登录
      loginUserId = this.wechatUserLogin(userVO);
    }

    //用redis做时间校验，两小时后rediskey失效  用户操作前需要先判定rediskey是否存在
    redisStorage.setObject(Constant.USER_ID_REDIS, loginUserId, 2 * 60 * 60 * 1000);
    return loginUserId;
  }

  private String wechatUserLogin(UserVO userVO) {
    //只做了小程序用户校验 使用openID
    if (userVO == null || !StringUtils.hasText(userVO.getWechatUserId())) {
      // TODO: 2022/7/7 抛异常 用户信息不完整
      LOG.error("用户信息不完整");
      return null;
    }
    User user = userDao.queryUserByWechatUserId(userVO.getWechatUserId(), Constant.USER_COLLECTION_NAME);
    if (user == null) {
      //新用户注册
      return this.registerUser(userVO);
    }
    return user.getId();
  }

  /**
   * web注册用户登录校验
   */
  private String webUserLogin(UserVO userVO) {
    User user = userDao.queryUserByLoginName(userVO.getLoginName(), Constant.USER_COLLECTION_NAME);
    if (user == null) {
      // TODO: 2022/7/7 抛异常 用户未注册
      LOG.error("用户未注册");
      return null;
    }
    if (!user.getPassword().equals(userVO.getPassword())) {
      // TODO: 2022/7/7 抛异常 用户密码错误
      LOG.error("用户密码错误");
      return null;
    }
    return user.getId();
  }

  /**
   * 更新用户信息，根据传来的字段，更新该字段信息
   *
   * @param userVO         用户信息
   * @param collectionName 数据库
   * @return User
   */
  @Override
  public User updateUser(UserVO userVO, String collectionName) {
    UserBuilder userBuilder = new UserBuilder();
    userBuilder.setId(userVO.getId()).setLoginName(userVO.getLoginName()).setPassword(userBuilder.getPassword());
    return userDao.updateQAUser(userBuilder.build(), collectionName);
  }

  @Override
  public String registerUser(UserVO userVO) {
    if (userVO == null) {
      // TODO: 2022/7/7 抛异常 数据错误
      LOG.error("用户信息异常");
      return null;
    }
    User user = null;
    //校验用户信息
    if (UserSourceEnum.WEB_REGISTER.getId().equals(userVO.getUserSource())) {
      user = this.registerWebUser(userVO);
    }
    if (UserSourceEnum.WECHAT.getId().equals(userVO.getUserSource())) {
      user = this.registerWechatUser(userVO);
    }

    if (user != null) {
      //加入数据库中
      userDao.insertUser(user, Constant.USER_COLLECTION_NAME);
    } else {
      LOG.error("未知的用户来源");
      return null;
    }
    return user.getId();
  }

  /**
   * 微信用户注册
   *
   * @param userVO 用户信息 包含WechatUserId
   * @return User
   */
  private User registerWechatUser(UserVO userVO) {
    if (!StringUtils.hasText(userVO.getWechatUserId())) {
      // TODO: 2022/7/7 抛异常 数据错误
      LOG.error("用户信息不完整");
      return null;
    }
    User user = userDao.queryUserByWechatUserId(userVO.getWechatUserId(), Constant.USER_COLLECTION_NAME);
    //库中没有用户则生成新用户，有则返回原用户
    if (user == null) {
      return new UserBuilder().setUserSource(UserSourceEnum.WECHAT).setRegistrationTime(System.currentTimeMillis())
          .setLastModifyTime(System.currentTimeMillis()).setId(UUID.randomUUID().toString()).build();
    } else {
      return user;
    }
  }

  /**
   * web用户注册
   *
   * @param userVO 用户信息 包含loginName password email
   * @return user
   */
  private User registerWebUser(UserVO userVO) {
    if (!StringUtils.hasText(userVO.getLoginName()) || !StringUtils.hasText(userVO.getPassword()) || !StringUtils.hasText(userVO.getEmail())) {
      // TODO: 2022/7/7 抛异常 数据错误
      LOG.error("用户信息不完整");
      return null;
    }
    User user = userDao.queryUserByLoginName(userVO.getLoginName(), Constant.USER_COLLECTION_NAME);
    if (user != null) {
      // TODO: 2022/7/7 抛异常 用户名已存在
      LOG.error("用户名已存在");
      return null;
    }
    return new User(UUID.randomUUID().toString(), userVO.getLoginName(), userVO.getPassword(), userVO.getEmail(), UserSourceEnum.WEB_REGISTER,
        System.currentTimeMillis(), System.currentTimeMillis());

    //builder模式：
//    return new UserBuilder().setUserSource(UserSourceEnum.WEB_REGISTER).setLoginName(userVO.getLoginName()).setPassword(userVO.getPassword())
//        .setEmail(userVO.getEmail()).setRegistrationTime(System.currentTimeMillis()).setLastModifyTime(System.currentTimeMillis()).setId(
//            UUID.randomUUID().toString()).build();

  }
}
