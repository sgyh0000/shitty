# 简介
干嘛的自己看代码

# 使用
## Step 1:
fork项目到自己的repository中
## Step 2:
拉到本地
## Step 3:
在terminal/cmd中执行:
```shell script
mvn install
```
# Step 4:
导入pom(如导入mvc):
```xml
<!-- mvc模块,依赖beans和util模块,json序列化默认使用fastjson,不可配置 -->
<dependency>
    <groupId>club.itguys</groupId>
    <artifactId>shitty-mvc</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
## Step 5:
web.xml中配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 监听器 -->
    <listener>
        <listener-class>club.itguys.shitty.mvc.ContextLoaderListener</listener-class>
    </listener>

    <!-- 注解扫描包 -->
    <context-param>
        <param-name>basePackage</param-name>
        <param-value>cc.c.test</param-value>
    </context-param>

    <!-- 编码配置 -->
    <context-param>
        <param-name>charset</param-name>
        <param-value>utf-8</param-value>
    </context-param>

    <!-- requestMapping -->
    <servlet>
        <servlet-name>shitty</servlet-name>
        <servlet-class>club.itguys.shitty.mvc.RequestMappingServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!-- mapping域配到根目录下 -->
    <servlet-mapping>
        <servlet-name>shitty</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```
