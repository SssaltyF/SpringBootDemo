package org.yuy.demo.utils.gson;

/**
 * 使用Gson对枚举类进行序列化和反序列化
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public interface GsonEnum<E> {

  Integer serialize(E e);

  E deserialize(String jsonEnum);

}
