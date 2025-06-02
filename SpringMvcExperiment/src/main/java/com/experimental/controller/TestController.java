package com.experimental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
} 