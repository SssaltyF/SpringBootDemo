package org.yuy.demo.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.yuy.demo.constant.enumerate.UserSourceEnum;

/**
 * gson解析器工厂类
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class GsonFactory {

  private static Gson gson;

  static {
    gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .serializeNulls()
        .registerTypeAdapter(UserSourceEnum.class,
            new GsonEnumTypeAdapter<>(UserSourceEnum.UNKNOWN))
        .create();

  }

  /**
   * 可能有性能问题，共用一个gson
   */
  public static Gson getGsonClient() {
    return gson;
  }

}
