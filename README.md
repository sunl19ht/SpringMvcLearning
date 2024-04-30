# 尚硅谷新版SSM框架全套视频教程，Spring6+SpringBoot3最新SSM企业级开发（笔记）
## P102：SpringMVC流程调用和体验
1. 创建父工程引入依赖
```xml
<dependencies>
        <!-- spring-ioc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.1.3</version>
        </dependency>

        <!-- web相关依赖 -->
        <dependency>
            <groupId>jakarta.platform</groupId>
            <artifactId>jakarta.jakartaee-web-api</artifactId>
            <version>9.1.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>6.1.3</version>
        </dependency>
</dependencies>
```
2. 创建子模块继承父工程
* 编写Controller方法
```java
@Controller
public class HelloController {
    //handler -> springmvc/hello return "hello springmvc!"

    @RequestMapping("spring/mvc/hello") //对外访问的地址 到handlerMapping注册的注解
    @ResponseBody //直接返回给前端 不要找视图解析器
    public String hello() {
        //返回给前端
        return "hello springmvc!";
    }
}
```
* 编写配置类
```java
@Configuration
@ComponentScan("com.sunl19ht.controller")
public class MvcConfig {
    //1. 将controller配置到IoC容器
    @Bean
    public RequestMappingHandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }
    //2. handlerMapping handlerAdapter放到IoC容器
    @Bean
    public RequestMappingHandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }
}
```
* 编写SpringMvc初始化方法
```java
public class SpringMvcInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    //设置项目对应的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    //配置springmvc自带servlet的访问地址
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
```