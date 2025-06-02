package com.experimental.controller;

import com.experimental.entity.User; // Import the User POJO
import com.experimental.entity.Order; // Import the Order POJO
import com.experimental.entity.PhoneNumber; // Import the PhoneNumber POJO
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

@Controller
public class TestController {

    @RequestMapping(value = "/showName.form") // Matching the *.form pattern in web.xml
    public String showName(HttpServletRequest request, // Included as per lab guide
                           HttpServletResponse response, // Included as per lab guide
                           Model model,
                           @RequestParam(value = "name", required = false, defaultValue = "Guest") String name) {
        
        // The lab guide mentions "//访问传递的参数 ;", which implies accessing parameters.
        // Using @RequestParam is the Spring MVC way to do this for simple parameters.
        // The 'name' variable already holds the value of the "name" request parameter.

        System.out.println("Controller: Received request for /showName.form with name: " + name);

        // Add the name to the model to make it accessible in the JSP
        model.addAttribute("displayName", name);

        // The lab guide says "return "index.jsp";". 
        // With InternalResourceViewResolver, we return the logical view name "index".
        return "index"; 
    }

    // Method to demonstrate HttpServletRequest, HttpSession, Model/ModelMap
    @RequestMapping(value = "/servletObjects.form")
    public String handleServletObjects(HttpServletRequest request,
                                     HttpSession session,
                                     Model model,
                                     @RequestParam(value = "requestParam", required = false) String requestParam) {
        
        // 1. Using HttpServletRequest
        String paramFromRequestObject = request.getParameter("requestParam"); // Can still get it this way
        model.addAttribute("paramFromRequestObject", "(来自 request.getParameter): " + paramFromRequestObject);
        model.addAttribute("requestParamInjected", "(通过 @RequestParam): " + requestParam);

        // 2. Using HttpSession
        session.setAttribute("sessionTestAttribute", "来自 HttpSession 的问候!");
        String sessionAttribute = (String) session.getAttribute("sessionTestAttribute");
        model.addAttribute("sessionAttributeValue", sessionAttribute);

        // 3. Using Model (ModelMap is also an option, Model is an interface)
        model.addAttribute("modelAttributeValue", "来自 Model 的问候!");
        
        // Setting a title for the page via Model
        model.addAttribute("pageTitle", "Servlet 对象演示");

        System.out.println("Controller: Handling /servletObjects.form. Request param: " + requestParam);

        return "servletObjectsView"; // Logical view name
    }

    // Method to demonstrate simple data type binding (int, String)
    @RequestMapping(value = "/simpleData.form")
    public String simpleDataBinding(@RequestParam(value = "id", defaultValue = "0") int userId,
                                  @RequestParam(value = "userName", defaultValue = "Anonymous") String userName,
                                  Model model) {
        
        model.addAttribute("pageTitle", "简单数据类型绑定演示");
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);

        System.out.println("Controller: Handling /simpleData.form. ID: " + userId + ", Name: " + userName);
        
        return "simpleDataView";
    }

    // Method to demonstrate POJO data binding
    @RequestMapping(value = "/pojoData.form")
    public String pojoBinding(User user, Model model) { // Spring MVC automatically instantiates User and populates its fields
        
        model.addAttribute("pageTitle", "POJO 数据绑定演示");
        model.addAttribute("boundUser", user); // Add the entire User object to the model

        System.out.println("Controller: Handling /pojoData.form. Bound User: " + user);
        
        return "pojoView";
    }

    // Method to demonstrate wrapped POJO data binding
    @RequestMapping(value = "/wrappedPojo.form")
    public String wrappedPojoBinding(Order order, Model model) { // Spring MVC binds to Order and its nested User object
        
        model.addAttribute("pageTitle", "包装 POJO 数据绑定演示");
        model.addAttribute("boundOrder", order);

        System.out.println("Controller: Handling /wrappedPojo.form. Bound Order: " + order);
        if (order != null && order.getUser() != null) {
            System.out.println("Controller: Nested User: " + order.getUser());
        }
        
        return "wrappedPojoView";
    }

    // Method to demonstrate custom data type binding (PhoneNumber)
    @RequestMapping(value = "/customType.form")
    public String customTypeBinding(@RequestParam("phone") PhoneNumber phoneNumber, Model model) {
        model.addAttribute("pageTitle", "自定义数据类型绑定演示");
        model.addAttribute("boundPhoneNumber", phoneNumber);

        System.out.println("Controller: Handling /customType.form. Bound PhoneNumber: " + phoneNumber);
        
        return "customTypeView";
    }

    // Method to demonstrate array and list binding
    @RequestMapping(value = "/collectionData.form")
    public String collectionBinding(@RequestParam(value = "names", required = false) List<String> nameList,
                                  @RequestParam(value = "ids", required = false) Integer[] idArray,
                                  Model model) {
        
        model.addAttribute("pageTitle", "数组/集合绑定演示");
        model.addAttribute("nameList", nameList);
        model.addAttribute("idArray", idArray);

        System.out.println("Controller: Handling /collectionData.form.");
        if (nameList != null) {
            System.out.println("  Bound Name List: " + nameList);
        }
        if (idArray != null) {
            System.out.println("  Bound ID Array: " + java.util.Arrays.toString(idArray));
        }
        
        return "collectionView";
    }

    // Method to demonstrate JSON data binding (@RequestBody and @ResponseBody)
    @RequestMapping(value = "/jsonData.form", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody // Indicates the return value should be directly bound to the web response body
    public User handleJsonData(@RequestBody User user) { // @RequestBody indicates the parameter should be bound from the request body (JSON)
        System.out.println("Controller: Handling /jsonData.form (POST). Received User (from JSON): " + user);
        
        // For demonstration, we can modify the user or create a response object
        // Here, we'll just echo back the received user, perhaps with a slight modification
        if (user != null) {
            user.setName(user.getName() + " (processed)");
            user.setEmail(user.getEmail() != null ? user.getEmail().toUpperCase() : null);
        }
        return user; // Spring MVC with Jackson will convert this User object back to JSON
    }

    // Optional: A simple GET handler to show the page with instructions/form for jsonData.form
    @RequestMapping(value = "/jsonData.form", method = RequestMethod.GET)
    public String showJsonDemoPage(Model model) {
        model.addAttribute("pageTitle", "JSON 数据绑定演示");
        System.out.println("Controller: Showing JSON demo page.");
        return "jsonDataView";
    }

    // Method to handle requests to the context root
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        // Optionally add any default attributes for the index page if needed
        model.addAttribute("displayName", "欢迎访问 (来自根路径映射)！");
        System.out.println("Controller: Handling request for / (root).");
        return "index"; // Returns the logical view name for index.jsp
    }
} 