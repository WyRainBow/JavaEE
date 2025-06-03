package com.experimental.controller;

import com.experimental.entity.User; // 导入 User POJO
import com.experimental.entity.Order; // 导入 Order POJO
import com.experimental.entity.PhoneNumber; // 导入 PhoneNumber POJO
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Arrays;

@Controller
public class TestController {

    @RequestMapping(value = "/showName.form") // 匹配 web.xml 中的 *.form 模式
    public String showName(HttpServletRequest request, // 根据实验指导包含此参数
                           HttpServletResponse response, // 根据实验指导包含此参数
                           Model model,
                           @RequestParam(value = "name", required = false, defaultValue = "Guest") String name) {
        
        // 实验指导中提到 "//访问传递的参数 ;"，这意味着访问参数。
        // 对于简单参数，使用 @RequestParam 是 Spring MVC 的方式。
        // 'name' 变量已经持有 "name" 请求参数的值。

        System.out.println("控制器: 收到 /showName.form 请求，名称: " + name);

        // 将名称添加到模型中，使其在 JSP 中可访问
        model.addAttribute("displayName", name);

        // 实验指导说 "return "index.jsp";"。
        // 使用 InternalResourceViewResolver 时，我们返回逻辑视图名 "index"。
        return "index"; 
    }

    // 用于演示 HttpServletRequest, HttpSession, Model/ModelMap 的方法
    @RequestMapping(value = "/servletObjects.form")
    public String handleServletObjects(HttpServletRequest request,
                                     HttpSession session,
                                     Model model,
                                     @RequestParam(value = "requestParam", required = false) String requestParam) {
        
        // 1. 使用 HttpServletRequest
        String paramFromRequestObject = request.getParameter("requestParam"); // 仍然可以通过这种方式获取
        model.addAttribute("paramFromRequestObject", "(来自 request.getParameter): " + paramFromRequestObject);
        model.addAttribute("requestParamInjected", "(通过 @RequestParam): " + requestParam);

        // 2. 使用 HttpSession
        session.setAttribute("sessionTestAttribute", "来自 HttpSession 的问候!");
        String sessionAttribute = (String) session.getAttribute("sessionTestAttribute");
        model.addAttribute("sessionAttributeValue", sessionAttribute);

        // 3. 使用 Model (ModelMap 也是一个选项, Model 是一个接口)
        model.addAttribute("modelAttributeValue", "来自 Model 的问候!");
        
        // 通过 Model 为页面设置标题
        model.addAttribute("pageTitle", "Servlet 对象演示");

        System.out.println("控制器: 处理 /servletObjects.form. 请求参数: " + requestParam);

        return "servletObjectsView"; // 逻辑视图名称
    }

    // 用于演示简单数据类型绑定 (int, String) 的方法
    @RequestMapping(value = "/simpleData.form")
    public String simpleDataBinding(@RequestParam(value = "id", defaultValue = "0") int userId,
                                  @RequestParam(value = "userName", defaultValue = "Anonymous") String userName,
                                  Model model) {
        
        model.addAttribute("pageTitle", "简单数据类型绑定演示");
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);

        System.out.println("控制器: 处理 /simpleData.form. ID: " + userId + ", 名称: " + userName);
        
        return "simpleDataView";
    }

    // 用于演示 POJO 数据绑定的方法
    @RequestMapping(value = "/pojoData.form")
    public String pojoBinding(User user, Model model) { // Spring MVC 自动实例化 User 并填充其字段
        
        model.addAttribute("pageTitle", "POJO 数据绑定演示");
        model.addAttribute("boundUser", user); // 将整个 User 对象添加到模型中

        System.out.println("控制器: 处理 /pojoData.form. 绑定的用户: " + user);
        
        return "pojoView";
    }

    // 用于演示包装 POJO 数据绑定的方法
    @RequestMapping(value = "/wrappedPojo.form")
    public String wrappedPojoBinding(Order order, Model model) { // Spring MVC 绑定到 Order 及其嵌套的 User 对象
        
        model.addAttribute("pageTitle", "包装 POJO 数据绑定演示");
        model.addAttribute("boundOrder", order);

        System.out.println("控制器: 处理 /wrappedPojo.form. 绑定的订单: " + order);
        if (order != null && order.getUser() != null) {
            System.out.println("控制器: 嵌套的用户: " + order.getUser());
        }
        
        return "wrappedPojoView";
    }

    // 用于演示自定义数据类型绑定 (PhoneNumber) 的方法
    @RequestMapping(value = "/customType.form")
    public String customTypeBinding(@RequestParam("phoneNumber") PhoneNumber phoneNumber, Model model) {
        model.addAttribute("phoneNumber", phoneNumber);
        return "customTypeView";
    }

    // 用于演示数组和列表绑定的方法
    @RequestMapping("/collectionBinding.form")
    public String collectionBinding(@RequestParam("names") List<String> names,
                                    @RequestParam("ids") Integer[] ids,
                                    Model model) {
        model.addAttribute("nameList", names);
        model.addAttribute("idArray", Arrays.toString(ids)); // Arrays.toString for better display
        return "collectionBindingView";
    }

    // 用于演示JSON数据绑定 (@RequestBody 和 @ResponseBody) 的方法
    @RequestMapping(value = "/jsonData.form", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody // 表示返回值应直接绑定到 Web 响应体
    public User handleJsonData(@RequestBody User user) { // @RequestBody 表示参数应从请求体 (JSON) 绑定
        System.out.println("控制器: 处理 /jsonData.form (POST). 收到的用户 (来自JSON): " + user);
        
        // 作为演示，我们可以修改用户或创建响应对象
        // 这里，我们只是回显收到的用户，可能稍作修改
        if (user != null) {
            user.setName(user.getName() + " (已处理)");
            user.setEmail(user.getEmail() != null ? user.getEmail().toUpperCase() : null);
        }
        return user; // Spring MVC 与 Jackson 将此 User 对象转换回 JSON
    }

    // 可选: 一个简单的 GET 处理程序，用于显示 jsonData.form 的说明/表单页面
    @RequestMapping(value = "/jsonData.form", method = RequestMethod.GET)
    public String showJsonDemoPage(Model model) {
        model.addAttribute("pageTitle", "JSON 数据绑定演示");
        System.out.println("控制器: 显示 JSON 演示页面。");
        return "jsonDataView";
    }

    // 处理对上下文根路径请求的方法
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        // 如果需要，可选择为索引页添加任何默认属性
        model.addAttribute("displayName", "欢迎访问 (来自根路径映射)！");
        System.out.println("控制器: 处理根路径 / 的请求。");
        return "index"; // 返回 index.jsp 的逻辑视图名称
    }
} 