package com.ly.swagger.controller;

import com.ly.swagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Operation接口
@Api(value = "YmqController 测试",tags = "这是一个测试")
@RestController
public class HelloController {

    //除了这个hello请求外，每个项目一定会有一个默认请求：/error
    @GetMapping("/hello")
    public String hello111(){
        return "hello";
    }

    @PostMapping("/user")
    public User user(){
        return new User();
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @PostMapping("/hello2")
    public User hello2(@ApiParam("用户") User user){
        return user;
    }
}
