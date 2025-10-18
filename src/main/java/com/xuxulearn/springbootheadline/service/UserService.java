package com.xuxulearn.springbootheadline.service;

import com.xuxulearn.springbootheadline.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuxulearn.springbootheadline.utils.Result;

/**
* @author 36229
* @description 针对表【news_user】的数据库操作Service
* @createDate 2025-10-15 20:30:11
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);//检查账号的用户名是否被占用

    Result register(User user);

}
