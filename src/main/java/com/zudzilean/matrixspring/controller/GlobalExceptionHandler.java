package com.zudzilean.matrixspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalExceptionHandler {

    // 映射任何GET或POST请求到"/error"的路径
    @RequestMapping(value = "/error", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public String handleError() {
        return "error"; // "error" 是 Thymeleaf 模板的名称
    }
}