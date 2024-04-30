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
## P103：Web容器初始化原理
### 1. Web容器初始化原理
* AbstractAnnotationConfigDispatcherServletInitializer间接实现了WebApplicationInitializer接口进行IoC容器初始化
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

public class Main implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //只要web程序一启动就会执行此方法
        System.out.println("web程序启动了");
        //IoC容器初始化
    }
}
```
## P104：SpringMVC路径设置注解
### 1. 设置访问路径
* @RequestMapping注解作用是将请求的URL地址和处理请求的方式handler方法关联 建立映射关系
```java
@Controller
@RequestMapping("/user")    //指定UserController开头地址
public class UserController {

    //handler -> handlerMapping 每一个方法就是一个handler 将handler注册到handlerMapping并且指定访问地址

    /**
     * @WebServlet("/必须斜杠开头")
     * @RequestMapping("非必须斜杠开头")
     * 1. 精准地址[一个/多个] /user/login, {"地址1", "地址2"}
     * 2. 支持模糊地址 [*任意一层字符串 /user/*, **任意多层字符串 /user/**]
     * 3. 类上添加@RequesstMapping 提取通用访问地址 见第7行
     * 4. 请求方式指定
     *    客户端 -> http(get | post | put | delete) -> ds -> handler
     *    默认只要地址正确任何请求方式都可以访问
     *    指定请求方式：method = RequestMethod.GET
     *        @RequestMapping(value = "/login", method = RequestMethod.GET)
     *    不符合请求方式：405异常!
     * 5. 注解进阶(只能使用在方法上)
     *    @GetMapping
     *    @PostMapping
     *    @PutMapping
     *    @DeleteMapping
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)//类 接口 方法 作用：将handler注册到handlerMapping
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping
    public String index() {
        return "index";
    }
}
```
## P105：json和param对比
### 参数对比
* param 参数
```
key = value & key = value
```
* json
```json
{
  "key": "value",
  "key": "value"
}
```
1. 参数编码：
   * param类型的参数会被编码为ASCII
   * JSON类型参数会被编码为UTF-8
2. 参数顺序
   * param类型的参数没有顺序限制
   * JSON类型的参数是有序的，JSON采用键值对的形式进行传递
3. 数据类型
   * param只支持字符串参数
   * JSON支持任意数据类型 [数组、对象、数据类型] 可以无限嵌套
## P106：参数接收
```java
@Controller
@RequestMapping("param")
public class ParamController {
    //直接接收 /param/data?name=zhangsan&age=19
    //形参列表填写对应名称的参数即可
    //1. 名称相同
    //2. 可以不传递 不报错
    @RequestMapping("data")
    @ResponseBody
    public String data(String name, int age) {
        System.out.println("name: " + name);
        System.out.println("name: " + name + " age: " + age);
        return "name: " + name + " age: " + age;
    }

    //注解指定 /param/data1?account=root&page=1
    // account 必须传递
    // page 可以不传递 默认值1
    // @RequestParam(value = "指定参数名 如果和请求参数名一样可以忽略",required = "是否必须传递true/false", defaultValue = "默认值")
    @GetMapping("data1")
    @ResponseBody
    public String data1(@RequestParam(value = "account") String account, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        System.out.println("account: " + account + " page: " + page);
        return "account: " + account + " page: " + page;
    }
    //特殊值
    //一名多值 key=1&key=2 直接使用集合接收
    //param/data2/hbs=吃饭&hbs=睡觉&hbs=打豆豆
    //集合必须加@RequestParam 如果不加会将对应的字符串赋值给集合 导致类型错误
    @GetMapping("data2")
    @ResponseBody
    public String data2(@RequestParam("hbs") List<String>[] hbs) {
        for (List<String> hb : hbs) {
            System.out.println(hb);
        }
        return "hbs: " + hbs;
    }

    //使用实体对象接收值
    //param/data3?name=zhangsan&age=19

    /**
     * user的属性名必须要等于参数名
     * 默认值直接在属性上设置 private String name = "张三";
     * @param user
     * @return
     */
    @RequestMapping("data3")
    @ResponseBody
    public String data3(User user) {
        System.out.println(user);
        return user.toString();
    }
}
```
## P107：路径传参
### 路径接收参数
格式：http://localhost:8080/path/user/zhangsan/19
1. 设置动态路径 接收动态路径
```java
@RequestMapping("{account}/{password}")
public String login(@PathVariable String account, @PathVariable String password) { //接收动态参数public String login(String account, String password) { //接收动态参数
    return null;
}
```
## P108：JSON接收
* JSON必须使用POST方式
```java
@RequestMapping("json")
@Controller
@ResponseBody
public class JsonController {
    //data -> 请求体 post{name, age, gender}
    /**
     * 报错415
     * 原因：Java原生api不支持接收json数据
     * 解决：1.导入Json处理的依赖 2. handlerAdapter配置json转化器
     * @param person
     * @return
     */
    @PostMapping("data")
    public String data(@RequestBody Person person) {
        System.out.println(person.toString());
        return person.toString();
    }
}
```
* 定义一个实体类接收JSON数据
```java
public class Person {
    private String name;
    private Integer age;
    private String gender;
}
```
* 使用@RequestBody注解
```java
public String data(@RequestBody Person person) {}
```
* 报错415
   * 报错415
   * 原因：Java原生api不支持接收json数据
   * 解决：1.导入Json处理的依赖 2. handlerAdapter配置json转化器
* 导入jackson
```xml
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.15.0</version>
</dependency>
```
* handlerAdapter配置json转化器
  * MvcConfig上添加 @EnableWebMvc 注解
* 添加了@EnableWebMvc之后会自动添加RequestMappingHandlerMapping 和 RequestMappingHandlerAdapter
## P110：接收请求头和cookie
* cookie获取
```java
@Controller
@RequestMapping("cookie")
@ResponseBody
public class CookieController {
    @RequestMapping("data")
    public String data(@CookieValue(value = "cookieName") String value) {
        System.out.println(value);
        return value;
    }

    @GetMapping("save")
    public String save(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookieName", "root");
        response.addCookie(cookie);
        return "ok";
    }
}
```
* header请求头获取
```java
@Controller
@RequestMapping("header")
@ResponseBody
public class HeaderController {

    @GetMapping("data")
    public String data(@RequestHeader("Host") String host) {
        System.out.println(host);
        return host;
    }
}
```
## P111：原生对象获取
| Controller method argument             | Description              |
|----------------------------------------|--------------------------|
| jakarta.servlet.ServletRequest         | 请求/响应对象                  |
| jakarta.servlet.ServletResponse        |                          |
| jakarta.servlet.http.HttpSession       | 强制会话存在                   |
| java.io.InputStream<br/>java.io.Reader | 用于访问Servlet API公开的原始请求正文 |
| java.io.OutputStream<br/>java.io.Writer | 用于访问Servlet API公开的原始响应正文 |
```java
public class ApiController {

    @Autowired
    private ServletContext servletContext;
    public void data(HttpServletResponse response,
                     HttpServletRequest request,
                     HttpSession session) {
        //使用原生对象就可以获取
        ServletContext servletContext = request.getServletContext();
        ServletContext servletContext1 = session.getServletContext();
    }
}
```