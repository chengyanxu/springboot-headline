package com.xuxulearn.springbootheadline.service;

import com.xuxulearn.springbootheadline.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuxulearn.springbootheadline.utils.Result;

/**
* @author 36229
* @description 针对表【news_type】的数据库操作Service
* @createDate 2025-10-15 20:30:11
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();

}
