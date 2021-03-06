package org.yuy.demo.utils.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 * Gson序列化枚举类采样器
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public class GsonEnumTypeAdapter<E> implements JsonSerializer<E>, JsonDeserializer<E> {

  private final GsonEnum<E> gsonEnum;

  public GsonEnumTypeAdapter(GsonEnum<E> gsonEnum) {
    this.gsonEnum = gsonEnum;
  }

  @Override
  public JsonElement serialize(E src, Type typeOfSrc, JsonSerializationContext context) {
    if (null != src && src instanceof GsonEnum) {
      return new JsonPrimitive(((GsonEnum) src).serialize(src));
    }
    return null;
  }

  @Override
  public E deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (null != json) {
      return gsonEnum.deserialize(json.getAsString());
    }
    return null;
  }

}
