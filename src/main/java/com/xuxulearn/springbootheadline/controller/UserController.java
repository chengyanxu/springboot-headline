package com.xuxulearn.springbootheadline.controller;

import com.xuxulearn.springbootheadline.pojo.User;
import com.xuxulearn.springbootheadline.service.UserService;
import com.xuxulearn.springbootheadline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin//前端访问要跨域
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result =userService.login(user);
        return result;
    }
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;


    }
    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }
    @PostMapping("regist")
    public Result register(@RequestBody User user){
        Result result=userService.register(user);
        return result;
    }
}
