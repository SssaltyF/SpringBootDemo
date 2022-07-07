package org.yuy.demo.repository.storage;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author YuanYu
 * @Date 2022/7/6
 */
@Repository
public class RedisStorageImpl implements RedisStorage {

  private static final Logger LOG = LoggerFactory.getLogger(RedisStorageImpl.class);

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 判断key是否存在
   *
   * @param key 键
   * @return true 存在 false 不存在
   */
  @Override
  public boolean hasKey(String key) {
    try {
      return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 删除缓存
   *
   * @param key 键
   */
  @Override
  public void delete(String key) {
    if (key != null) {
      redisTemplate.delete(key);
    }
  }

  /**
   * 获取缓存
   *
   * @param key 键
   * @return 值
   */
  @Override
  public Object getObject(String key) {
    return key == null ? null : redisTemplate.opsForValue().get(key);
  }

  /**
   * 添加缓存
   *
   * @param key   键
   * @param value 值
   * @return true 成功 false 失败
   */
  @Override
  public boolean setObject(String key, Object value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean setObject(String key, Object value, long time) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
      } else {
        this.setObject(key, value);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 根据item获取hash的指定value
   *
   * @param key  键 不能为null
   * @param item 项 不能为null
   * @return 值
   */
  @Override
  public Object hashGet(String key, String item) {
    return redisTemplate.opsForHash().get(key, item);
  }

  /**
   * 向hash添加数据
   *
   * @param key   键
   * @param item  项
   * @param value 值
   * @return true 成功 false失败
   */
  @Override
  public boolean hashSet(String key, String item, Object value) {
    try {
      redisTemplate.opsForHash().put(key, item, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 根据item删除hash中的值
   *
   * @param key  键 不能为null
   * @param item 项 可以使多个 不能为null
   */
  @Override
  public void hashDelete(String key, Object... item) {
    redisTemplate.opsForHash().delete(key, item);
  }

  /**
   * 判断hash中是否有该项的值
   *
   * @param key  键 不能为null
   * @param item 项 不能为null
   * @return true 存在 false 不存在
   */
  @Override
  public boolean hashHasKey(String key, String item) {
    return redisTemplate.opsForHash().hasKey(key, item);
  }
}
