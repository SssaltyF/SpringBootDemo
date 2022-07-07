package org.yuy.demo.utils.mail;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 邮箱工具类
 *
 * @Author YuanYu
 * @Date 2022/7/6
 */
@Component
public class MailUtil {

  /**
   * 邮箱初始化方法
   *  ‘@PostConstruct 注解为启动时运行该方法
   */
  @PostConstruct
  private void initEMailbox() {
    // TODO: 2022/7/6 邮箱初始化
  }

  /**
   * @param to 收件人
   * @param subject 主题
   * @param html 内容
   */
  public void sendMail(String to, String subject, String html) {
    // TODO: 2022/7/6 发送邮件
  }

}
