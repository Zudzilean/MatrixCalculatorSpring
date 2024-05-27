package com.zudzilean.matrixspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制器类，用于处理与应用程序主页相关的请求。
 */
@Controller
public class IndexController {

    /**
     * 处理根URL("/")的GET请求，用于显示计算器表单。
     *
     * @return 计算器表单的模板名称。
     */
    @GetMapping("/")
    public String index() {
        // 实际的方法实现，返回视图的名称，Spring MVC将会解析为对应的HTML模板。
        return "calculatorForm";
    }
}