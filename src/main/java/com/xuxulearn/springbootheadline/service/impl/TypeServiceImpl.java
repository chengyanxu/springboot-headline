package com.xuxulearn.springbootheadline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxulearn.springbootheadline.mapper.TypeMapper;
import com.xuxulearn.springbootheadline.pojo.Type; // 添加缺失的导入
import com.xuxulearn.springbootheadline.service.TypeService;
import com.xuxulearn.springbootheadline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Result findAllTypes(){
        List<Type> types=typeMapper.selectList(null);
        return Result.ok(types);

    }
}