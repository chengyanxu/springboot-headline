package com.xuxulearn.springbootheadline.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxulearn.springbootheadline.pojo.User;
import com.xuxulearn.springbootheadline.service.UserService;
import com.xuxulearn.springbootheadline.mapper.UserMapper;
import com.xuxulearn.springbootheadline.utils.JwtHelper;
import com.xuxulearn.springbootheadline.utils.MD5Util;
import com.xuxulearn.springbootheadline.utils.Result;
import com.xuxulearn.springbootheadline.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 36229
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2025-10-15 20:30:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result login(@RequestBody yUser user){
//根据前端传回的账户密码，在数据库中取出对应的用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);//返回h501
        }

        if(!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            String token = jwtHelper.createToken(loginUser.getUid());
            Map data = new HashMap();
            data.put("token", token);

            return Result.ok(data);//如果成功返回200
        }
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);//返回503
    }

    @Override
    public Result getUserInfo(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);//登录失效，返回504
        }
        int userid = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userid);
        user.setUserPwd( null);
        Map data = new HashMap();
        data.put("loginuser", user);
        return Result.ok(data);
    }
    @Override
    public Result checkUserName(String username){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();//查询构造器
        queryWrapper.eq(User::getUsername,username);//查询数据库中是已有用户名
        Long count=userMapper.selectCount(queryWrapper);
        if (count==0) {
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.USERNAME_USED);// USERNAME_USED表示用户名已被占用
        //返回505

    }
    @Override
    public Result register(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count=userMapper.selectCount(queryWrapper);
        if(count>0){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));//对密码加密
        userMapper.insert(user);
        return Result.ok(null);
    }

}


