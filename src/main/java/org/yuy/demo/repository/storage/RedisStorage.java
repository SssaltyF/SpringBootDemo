package org.yuy.demo.repository.storage;

/**
 * redis interface
 * key对应数据库表名称 item对应数据的id
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public interface RedisStorage {

  /**
   * 判断key是否存在
   *
   * @param key 键
   * @return true 存在 false 不存在
   */
  boolean hasKey(String key);

  /**
   * 删除缓存
   *
   * @param key 键
   */
  void delete(String key);

  /**
   * 获取缓存
   *
   * @param key 键
   * @return 值
   */
  Object getObject(String key);

  /**
   * 添加缓存
   *
   * @param key 键
   * @param value 值
   * @return true 成功 false 失败
   */
  boolean setObject(String key, Object value);

  /**
   * 添加缓存并设置失效时间
   *
   * @param key 键
   * @param value 值
   * @param time 时间（毫秒） time要大于0 如果time小于等于0 将设置无限期
   * @return true 成功 false 失败
   */
  boolean setObject(String key, Object value, long time);

  /**
   * 根据item获取hash的指定value
   *
   * @param key 键 不能为null
   * @param item 项 不能为null
   * @return 值
   */
  Object hashGet(String key, String item);

  /**
   * 向hash添加数据
   *
   * @param key 键
   * @param item 项
   * @param value 值
   * @return true 成功 false失败
   */
  boolean hashSet(String key, String item, Object value);


  /**
   * 根据item删除hash中的值
   *
   * @param key 键 不能为null
   * @param item 项 可以使多个 不能为null
   */
  void hashDelete(String key, Object... item);

  /**
   * 判断hash中是否有该项的值
   *
   * @param key 键 不能为null
   * @param item 项 不能为null
   * @return true 存在 false 不存在
   */
  boolean hashHasKey(String key, String item);

}
