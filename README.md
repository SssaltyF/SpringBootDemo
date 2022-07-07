# SpringBootDemo
**SpringBootDemo using MongoDB and Redis**

src/main/java：主程序入口 Application，可以通过直接运行该类来 启动 Spring Boot应用

src/main/resources：配置目录，该目录用来存放应用的一些配置信息，比如应用名、服务端口、数据库配置等。

src/test：单元测试目录，生成的 ApplicationTests 通过 JUnit4实现，可以直接用运行 Spring Boot应用的测试。

application.properties/application.yml 用于存放程序的各种依赖模块的配置信息，比如 服务端口，数据库连接配置等。

## 1）代码层的结构

根目录：org.yuy.demo

1.工程启动类(SpringBootDemoApplication.java)置于 org.yuy.demo 包下

2.实体类(domain)置于 org.yuy.demo.domain

3.数据访问层(Dao)置于 org.yuy.demo.repository

4.数据服务层(Service)置于 org.yuy.demo.service,
4.1，数据服务的实现接口(serviceImpl)置于 org.yuy.demo.service.impl

5.前端控制器(Controller)置于 org.yuy.demo.controller

6.工具类(utils)置于 org.yuy.demo.utils

7.常量接口类(constant)置于 org.yuy.demo.constant

8.配置信息类(config)置于 org.yuy.demo.config

9.数据传输类(dto)置于 org.yuy.demo.domain.bo

10.视图对象类(vo)置于 org.yuy.demo.domain.vo

##  2）资源文件的结构

根目录:src/main/resources

1.配置文件(.properties/.json等)置于config文件夹下

2.spring.xml置于META-INF/spring文件夹下


命名包名目录的方式 :com.公司名的简写.项目的名字.业务模块名
