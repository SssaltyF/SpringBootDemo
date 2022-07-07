package org.yuy.demo.repository.storage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * MongoDB 抽象类 子类需要实现获取泛型的方法
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
public abstract class BaseMongoStorage<T> {

  /**
   * 所有子类必须实现该抽象方法，用于获取泛型的class
   *
   * @return class
   */
  protected abstract Class<T> getEntityClass();

  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   * 保存对象
   *
   * @param t 对象
   * @param collectionName collectionName
   */
  public void save(T t, String collectionName) {
    this.mongoTemplate.save(t, collectionName);
  }

  /**
   * 插入对象
   *
   * @param t 对象
   * @param collectionName collectionName
   */
  public void insert(T t, String collectionName) {
    this.mongoTemplate.insert(t, collectionName);
  }

  /**
   * 查询所有
   *
   * @param collectionName collectionName
   * @return 集合
   */
  public List<T> selectAll(String collectionName) {
    return this.mongoTemplate.findAll(this.getEntityClass(), collectionName);
  }

  /**
   * 根据id查询
   *
   * @param id id
   * @param collectionName collectionName
   * @return 对象
   */
  public T selectById(String id, String collectionName) {
    Query query = new Query(Criteria.where("_id").is(id));
    return this.mongoTemplate.findOne(query, this.getEntityClass(), collectionName);
  }

  /**
   * 根据条件查询
   *
   * @param query 查询条件
   * @param collectionName collectionName
   * @return 集合
   */
  public List<T> selectList(Query query, String collectionName) {
    return this.mongoTemplate.find(query, this.getEntityClass(), collectionName);
  }

  /**
   * 根据条件查询
   *
   * @param query 查询条件
   * @param collectionName collectionName
   * @return 对象
   */
  public T selectOne(Query query, String collectionName) {
    return this.mongoTemplate.findOne(query, this.getEntityClass(), collectionName);
  }

  /**
   * 根据条件分页查询
   *
   * @param query 查询条件
   * @param pageNo 页码
   * @param pageSize 分页大小
   * @param collectionName collectionName
   * @return 集合
   */
  public List<T> selectByPage(Query query, int pageNo, int pageSize, String collectionName) {
    query.skip((pageNo - 1) * pageSize);
    query.limit(pageSize);
    return this.mongoTemplate.find(query, this.getEntityClass(), collectionName);
  }

  /**
   * 聚合查询
   *
   * @param query 查询条件
   * @param field 字段
   * @param collectionName collectionName
   * @param clazz 返回类型
   * @return list
   */
  public List<?> distinctSelect(Query query, String field, String collectionName, Class clazz) {
    return this.mongoTemplate
        .findDistinct(query, field, collectionName, this.getEntityClass(), clazz);
  }

  /**
   * 根据条件查询数量
   *
   * @param query 查询条件
   * @param collectionName collectionName
   * @return 数量
   */
  public Long count(Query query, String collectionName) {
    return this.mongoTemplate.count(query, this.getEntityClass(), collectionName);
  }

  /**
   * 删除对象
   *
   * @param t 对象
   * @param collectionName collectionName
   * @return 数量
   */
  public int delete(T t, String collectionName) {
    return (int) this.mongoTemplate.remove(t, collectionName).getDeletedCount();
  }

  /**
   * 根据id删除对象
   *
   * @param id id
   * @param collectionName collectionName
   * @return 数量
   */
  public int deleteById(String id, String collectionName) {
    Query query = new Query(Criteria.where("_id").is(id));
    return (int) this.mongoTemplate.remove(query, this.getEntityClass(), collectionName)
        .getDeletedCount();
  }

  /**
   * 根据条件删除对象
   *
   * @param query 查询条件
   * @param collectionName collectionName
   * @return 数量
   */
  public int delete(Query query, String collectionName) {
    return (int) this.mongoTemplate.remove(query, this.getEntityClass(), collectionName)
        .getDeletedCount();
  }

  /**
   * 根据条件修改文档
   *
   * @param query 查询条件
   * @param update 修改内容
   * @param collectionName collectionName
   * @return 数量
   */
  public int updateOne(Query query, Update update, String collectionName) {
    return (int) this.mongoTemplate
        .updateFirst(query, update, this.getEntityClass(), collectionName).getModifiedCount();
  }

  /**
   * 根据条件修改所有文档
   *
   * @param query 查询条件
   * @param update 修改内容
   * @param collectionName collectionName
   * @return 数量
   */
  public int batchUpdate(Query query, Update update, String collectionName) {
    return (int) this.mongoTemplate
        .updateMulti(query, update, this.getEntityClass(), collectionName).getModifiedCount();
  }

  /**
   * 根据条件修改所有文档 没有则添加
   *
   * @param query 查询条件
   * @param update 修改内容
   * @param collectionName collectionName
   * @return 数量
   */
  public int updateInsert(Query query, Update update, String collectionName) {
    return (int) this.mongoTemplate.upsert(query, update, this.getEntityClass(), collectionName)
        .getModifiedCount();
  }

  /**
   * 根据条件修改文档并返回
   *
   * @param query 查询条件
   * @param update 修改内容
   * @param collectionName collectionName
   * @return 对象
   */
  public T findAndModify(Query query, Update update, String collectionName) {
    FindAndModifyOptions option = new FindAndModifyOptions();
    option.returnNew(true);
    return this.mongoTemplate
        .findAndModify(query, update, option, this.getEntityClass(), collectionName);
  }

  /**
   * 聚合查询
   *
   * @param aggregation 查询条件
   * @param collectionName collectionName
   * @return 对象
   */
  public List<T> aggregate(Aggregation aggregation, String collectionName) {
    AggregationResults<T> results = mongoTemplate
        .aggregate(aggregation, collectionName,this.getEntityClass());
    return results.getMappedResults();
  }

}
