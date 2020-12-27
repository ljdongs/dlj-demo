## 后端项目搭建

### 1、IDEA创建Springboot项工程

### 使用工具及技术：IEDA+Springboot+Mybaits+pg+JDBC+Druid 

IDEA中FIle->new->project->Spring initializr,选择SDK的版本，点击下一步![1609057375326](C:\Users\ljdongs\AppData\Local\Temp\1609057375326.png)

填写项目的GroupID，ArtifactID和javaversion(GroupID是项目组织唯一的标识符，实际对应JAVA的包的结构，是main目录里java的目录结构。ArtifactID就是项目的唯一的标识符，实际对应项目的名称，就是项目根目录的名称)。点击next进入依赖Dependencies选择页面，根据自己需求进行选择。接下来填写工程名和路径，点击finish完成工程的建立。

![1609057405758](C:\Users\ljdongs\AppData\Local\Temp\1609057405758.png)![1609057434776](C:\Users\ljdongs\AppData\Local\Temp\1609057434776.png)接下来是漫长的一个等待过程~，等待idea自动下载完内容。生成的工程目录结构如下图所示（建工程对依赖的选择差异生成的工程目录大小有差异）：

![1609060131117](C:\Users\ljdongs\AppData\Local\Temp\1609060131117.png)

### 2、设置相关配置文件

首先，在IDEA中File的settings，搜索maven，设置maven路径，maven的版本和settings文件以及maven的仓库位置。setting.xml主要用于配置maven的运行环境等一系列通用的属性，是全局级别的配置文件 。本文配置settings.xml中maven依赖包的下载来源，一般通过阿里云淘宝镜像下载(http://maven.aliyun.com/nexus/content/groups/public/)，以及设置下载的依赖仓库路径。![1609058103644](C:\Users\ljdongs\AppData\Local\Temp\1609058103644.png)

```html
  <!-- 依赖仓库本地路径 -->
<localRepository>G:/02workSpace/06开放式缴费平台/01java学习-练习/m2/Repository</localRepository>
<!-- 阿里镜像仓库 -->
    <mirror>
        <id>alimaven</id>
        <name>aliyun maven</name>
        <url>
            http://maven.aliyun.com/nexus/content/groups/public/
        </url>
        <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
```

接着，设置pom.xml文件。pom.xml主要描述了项目的maven坐标，依赖关系，开发者需要遵循的规则，缺陷管理系统，组织和licenses，以及其他所有的项目相关因素，是项目级别的配置文件 。在此文件添加项目中所用的各种第三方插件即添加依赖。通过maven->reimport来导入和刷新依赖包。

```html
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>dlj-project01</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dlj-project01</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
            <!-- 加载jdbc连接数据库 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-jdbc</artifactId>
            </dependency>
            <!-- 加载postgresql驱动 -->
            <dependency>
                <groupId>org.postgresql</groupId>
                <artifactId>postgresql</artifactId>
            </dependency>
            <!-- 加载mybatis jar包 -->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.1.2</version>
            </dependency>
            <!-- 数据源 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.9</version>
            </dependency>
           <!-- 加载lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
            <!-- 加载test测试 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
         <!-- 加载commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.4</version>
        </dependency>
        <!--添加swagger-spring-boot-starter依赖-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

```

![1609060298730](C:\Users\ljdongs\AppData\Local\Temp\1609060298730.png)

接下来，设置application.properties文件。因本项目采用在本地建立postgresql数据库，所以在此文件中进行数据库的配置（mybatis，数据源），使得项目连接上数据库。还配置了本项目用到的swagger插件。所有的配置完成接下来可以启动DljProject01Application项目。![1609061263690](C:\Users\ljdongs\AppData\Local\Temp\1609061263690.png)

```java

server.port=8000     //本地服务的端口号
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres   通过JDBC连接postgresql数据库
spring.datasource.username=postgres     //数据库名称
spring.datasource.password=19940909dlj     //数据库密码
spring.datasource.driver-class-name=org.postgresql.Driver

spring.datasource.druid.initial-size=5
spring.datasource.druid.min-idle=5
spring.datasource.druid.max-active=20
spring.datasource.druid.max-wait=60000
spring.datasource.druid.time-between-eviction-runs-millis=60000
spring.datasource.druid.min-evictable-idle-time-millis=300000
#spring.datasource.druid.validation-query=SELECT 1 FROM DUAL
spring.datasource.druid.test-while-idle=true
spring.datasource.druid.test-on-borrow=false
spring.datasource.druid.test-on-return=false
spring.datasource.druid.pool-prepared-statements=true
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
#spring.datasource.druid.filters=stat,wall,log4j
#spring.datasource.druid.connection-properties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

mybatis.mapper-locations=classpath:/mapper/**/*Mapper.xml

swagger.enabled=true
swagger.title=spring-boot-starter-swagger
swagger.description=Starter for swagger 2.x
swagger.version=3.0.0.RELEASE
swagger.license=Apache License, Version 2.0
swagger.licenseUrl=https://www.apache.org/licenses/LICENSE-2.0.html
swagger.termsOfServiceUrl=https://github.com/dyc87112/spring-boot-starter-swagger
swagger.contact.name=ljdongs
swagger.contact.url=http://blog.didispace.com
swagger.contact.email=ljdongs@126.com
swagger.base-package=com.example.dljproject01
swagger.base-path=/**

```

然后，通过pgAdmin可视化工具建立用户表，并建立字段和属性值。接着在java的src----->com.example包中创建模块，即包含Controller层、DAO层(数据访问层 )、DTO层(实体层)以及Service层(服务层 )。个人理解controller层-----> service层(接口—>接口实现类) -----> dao层的.mapper文件  -----> 和mapper层里的.xml文件对应。本项目的各层如下图： 

![1609061413086](C:\Users\ljdongs\AppData\Local\Temp\1609061413086.png)![1609062195998](C:\Users\ljdongs\AppData\Local\Temp\1609062195998.png)![1609062081145](C:\Users\ljdongs\AppData\Local\Temp\1609062081145.png)![1609062134908](C:\Users\ljdongs\AppData\Local\Temp\1609062134908.png)

![1609062251982](C:\Users\ljdongs\AppData\Local\Temp\1609062251982.png)

![1609062635392](C:\Users\ljdongs\AppData\Local\Temp\1609062635392.png)

![1609062707768](C:\Users\ljdongs\AppData\Local\Temp\1609062707768.png)

到此，后端项目已经搭建完毕。可通过swagger查看后端的所有API，并测试API，验证后端项目的完整性和正确性。

![1609063013238](C:\Users\ljdongs\AppData\Local\Temp\1609063013238.png)

![1609063262462](C:\Users\ljdongs\AppData\Local\Temp\1609063262462.png)



## 前端项目

前端基于 [vue-element-admin](https://panjiachen.github.io/vue-element-admin)框架实现，技术：vue、scss、vuex、elementUI、mock模拟、vue-router、js-cookie等，具体请前往[vue-element-admin](https://panjiachen.github.io/vue-element-admin)学习。本项目对其框架进行拆解，对自己的个人主页进行了设计，以及用户管理的增删改查操作，删掉了多余不用的东西。项目的整体目录如图所示。在此只展示部分更改的代码地方。

![1609065116868](C:\Users\ljdongs\AppData\Local\Temp\1609065116868.png)

vue-router是vue.js官方路由管理器。vue的单页应用是基于路由和组件的，路由用于设定访问路径，并将路径和组件映射起来（传统页面切换是用超链接a标签进行切换） ，在index.js文件中注释了本项目不用的路由组件，添加了自己用的路由组件。下图展示其中一部分路由以及效果图如下所示。

![1609065989126](C:\Users\ljdongs\AppData\Local\Temp\1609065989126.png)

![1609070348955](C:\Users\ljdongs\AppData\Local\Temp\1609070383268.png)

vue-element-admin---->src---->layout---->components---->Navbar.vue组件中设计头部动态导航和其他功能，比如搜索、登录中心设置等等。部分代码和效果图如下所示。

![1609068320656](C:\Users\ljdongs\AppData\Local\Temp\1609068320656.png)![1609069730469](C:\Users\ljdongs\AppData\Local\Temp\1609069730469.png)

vue-element-admin---->src---->views---->dashboard---->editor---->index.vue为用户登录进的首页组件，右上角天猫可以外链到我的github网址。

![1609068841056](C:\Users\ljdongs\AppData\Local\Temp\1609068841056.png)

![](http://down.gif.55.la/uploads/gif/20201227/1609069251270.gif)

vue-element-admin---->src---->views---->documentation---->index.vue为本项目的用户管理组件。并通过axios跟后端的API拿数据实现交互。下图为部分代码，效果图如下所示：

![1609070115177](C:\Users\ljdongs\AppData\Local\Temp\1609070115177.png)

![1609070273004](C:\Users\ljdongs\AppData\Local\Temp\1609070273004.png)![1609071262349](C:\Users\ljdongs\AppData\Local\Temp\1609071262349.png)

由此，整个demo完成。





