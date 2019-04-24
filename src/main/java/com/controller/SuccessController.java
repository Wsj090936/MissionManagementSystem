package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SuccessController {

    @RequestMapping("success")
    public String toSuccess(){
        return "success";
    }

    @RequestMapping("/test")

    public String testmethod(){

        return "main";
    }
}
