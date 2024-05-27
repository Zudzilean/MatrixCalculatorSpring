package com.zudzilean.matrixspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 定义全局异常处理控制器。
 * <p>
 * 此控制器用于处理应用程序中的所有异常，并将其映射到一个统一的错误处理视图。
 * </p>
 */
@Controller
public class GlobalExceptionHandler {

    /**
     * 处理应用程序中发生的任何错误，并将其映射到错误页面。
     * <p>
     * 当捕获到异常时，Spring MVC将转发到"/error"路径，并由此方法处理。
     * </p>
     * <p>
     * 这个方法特别映射了GET请求到"/error"路径。
     * </p>
     *
     * @return 错误页面的模板名称。
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError() {
        // 返回错误处理页面的名称，Thymeleaf将解析为对应的HTML模板。
        return "error";
    }
}