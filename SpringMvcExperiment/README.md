# 实验十二：Spring MVC 基础与数据绑定演示 (SpringMvcExperiment)

## 1. 项目简介

本项目是"实验十二：Spring MVC"的实践项目，旨在演示 Spring MVC 框架的基础配置、请求处理流程以及多种数据绑定机制。通过此项目，可以学习和理解 Spring MVC 如何接收 HTTP 请求、如何将请求参数绑定到控制器方法、如何与视图 (JSP) 进行交互以及如何处理 JSON 数据。

## 2. 项目目的

*   掌握 Spring MVC 项目的基本搭建和配置方法（`web.xml`, Spring MVC配置文件 `applicationContext.xml`）。
*   理解 `DispatcherServlet` 的作用和请求处理流程。
*   学习使用 `@Controller`, `@RequestMapping`, `@RequestParam` 等常用注解。
*   实践 Spring MVC 的各种数据绑定功能：
    *   简单数据类型绑定 (如 `int`, `String`)。
    *   POJO (Plain Old Java Object) 对象绑定。
    *   包装 POJO 对象绑定（对象中包含其他对象）。
    *   自定义类型转换器 (`Converter`) 的使用。
    *   集合类型绑定 (如 `List`, 数组)。
    *   JSON 数据的接收 (`@RequestBody`) 和响应 (`@ResponseBody`)。
*   了解如何配置视图解析器 (`InternalResourceViewResolver`) 以映射到 JSP 视图。
*   熟悉在 JSP 中使用 JSTL 和 EL 表达式展示动态数据。

## 3. 技术栈

*   Java (JDK 1.8+)
*   Spring MVC (版本查看 `pom.xml`)
*   Apache Maven (用于项目构建和依赖管理)
*   Apache Tomcat (通过 `tomcat7-maven-plugin` 插件嵌入式运行)
*   JSP & JSTL
*   Servlet API

## 4. 环境要求

*   **JDK**: 1.8 或更高版本。
*   **Maven**: 3.3 或更高版本。
*   **IDE (可选但推荐)**: IntelliJ IDEA, Eclipse 等支持 Maven 项目的 IDE。

## 5. 如何运行项目

1.  **克隆/下载项目**:
    确保您已获得项目代码。

2.  **配置 Maven (如果尚未配置)**:
    确保您的系统中已安装 Maven，并且 `M2_HOME` 和 `PATH` 环境变量已正确设置。

3.  **打开终端/命令行**:
    导航到项目的根目录 (即包含 `pom.xml` 文件的 `SpringMvcExperiment` 目录)。
    例如: `cd /Users/wyrain/作业/Java/实验12/SpringMvcExperiment/`

4.  **运行项目**:
    在项目根目录下执行以下 Maven 命令来启动嵌入式 Tomcat 服务器：
    ```bash
    mvn tomcat7:run
    ```
    *注意：如果您的 `mvn` 命令不在 `PATH` 中，您可能需要提供完整的 `mvn` 路径，或者按照之前的对话临时设置 `PATH` (例如：`PATH=/path/to/your/maven/bin:$PATH mvn tomcat7:run`)。*

5.  **访问应用**:
    项目成功启动后，默认会在 `8888` 端口运行 (已在 `pom.xml` 中配置)。在浏览器中访问以下 URL：
    [http://localhost:8888/SpringMvcExperiment/](http://localhost:8888/SpringMvcExperiment/)

    您应该能看到应用的首页 (`index.jsp`)。

## 6. 主要功能点及测试 URL

应用首页 (`index.jsp`) 提供了到各个功能演示页面的链接。以下是主要的功能点及其对应的 URL (相对于应用根路径 `http://localhost:8888/SpringMvcExperiment/`)：

*   **Servlet 对象演示**:
    *   URL: `servletObjects.form`
    *   演示: 获取 `HttpServletRequest`, `HttpSession` 中的数据，以及 `Model` 对象的使用。
    *   测试: `servletObjects.form?requestParam=一些测试文本`

*   **简单数据类型绑定**:
    *   URL: `simpleData.form`
    *   演示: 绑定 `int` 和 `String` 类型的请求参数。
    *   测试: `simpleData.form?id=101&userName=张三`

*   **POJO 数据绑定**:
    *   URL: `pojoData.form`
    *   演示: 将请求参数自动绑定到 `User` 对象。
    *   测试: `pojoData.form?id=1&name=爱丽丝&email=alice@example.com&age=30`

*   **包装 POJO 数据绑定**:
    *   URL: `wrappedPojo.form`
    *   演示: 绑定包含嵌套对象 (`User`) 的 `Order` 对象。
    *   测试: `wrappedPojo.form?orderId=ORD123&itemName=笔记本电脑&user.id=10&user.name=查理&user.age=28`

*   **自定义类型绑定**:
    *   URL: `customType.form`
    *   演示: 使用自定义的 `StringToPhoneNumberConverter` 将字符串参数转换为 `PhoneNumber` 对象。
    *   测试: `customType.form?phone=123-456-7890`

*   **数组/集合绑定**:
    *   URL: `collectionData.form`
    *   演示: 将多个同名请求参数绑定到 `List<String>` 和 `Integer[]`。
    *   测试: `collectionData.form?names=张三&names=李四&ids=1&ids=2`

*   **JSON 数据绑定**:
    *   URL: `jsonData.form` (GET请求显示测试页面, POST请求处理数据)
    *   演示:
        *   GET: 显示一个包含表单的页面，允许用户输入 JSON 字符串。
        *   POST: 接收客户端发送的 JSON 格式的 `User` 数据 (`@RequestBody`)，并返回一个修改后的 `User` 对象 (JSON 格式, `@ResponseBody`)。
    *   测试: 访问 `jsonData.form`，在文本框中输入 User 对象的 JSON 表示，然后点击"发送 JSON 数据"按钮。

*   **根路径映射**:
    *   URL: `/`
    *   演示: `TestController` 中的 `showIndexPage` 方法处理对应用根路径的访问，返回 `index.jsp`。

## 7. 项目结构简介

```
SpringMvcExperiment/
├── pom.xml                   # Maven 项目配置文件，包含依赖和插件
├── README.md                 # 本文件
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── experimental/
        │           ├── controller/
        │           │   └── TestController.java  # 主要的 Spring MVC 控制器
        │           ├── converter/
        │           │   └── StringToPhoneNumberConverter.java # 自定义类型转换器
        │           └── entity/
        │               ├── Order.java         # Order POJO
        │               ├── PhoneNumber.java   # PhoneNumber POJO
        │               └── User.java          # User POJO
        └── webapp/
            ├── WEB-INF/
            │   ├── applicationContext.xml # Spring MVC 配置文件
            │   ├── jsp/                   # JSP 视图文件存放目录
            │   │   ├── index.jsp
            │   │   ├── servletObjectsView.jsp
            │   │   ├── simpleDataView.jsp
            │   │   ├── pojoView.jsp
            │   │   ├── wrappedPojoView.jsp
            │   │   ├── customTypeView.jsp
            │   │   ├── collectionView.jsp
            │   │   └── jsonDataView.jsp
            │   └── web.xml                # Web 应用部署描述符，配置 DispatcherServlet
            └── index.jsp                  # (可选) 如果WEB-INF外有首页，但本项目首页在jsp目录下由Controller指定
```

---
希望这个 `README.md` 文件能帮助您更好地理解和运行此项目。 