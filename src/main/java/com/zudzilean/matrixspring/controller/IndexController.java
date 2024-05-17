package com.zudzilean.matrixspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // 返回模板的名称，不包括文件扩展名
        return "calculatorForm";
    }
}