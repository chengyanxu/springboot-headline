package com.xuxulearn.springbootheadline.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxulearn.springbootheadline.pojo.Headline;
import com.xuxulearn.springbootheadline.pojo.vo.PortalVo;
import com.xuxulearn.springbootheadline.service.HeadlineService;
import com.xuxulearn.springbootheadline.mapper.HeadlineMapper;
import com.xuxulearn.springbootheadline.utils.JwtHelper;
import com.xuxulearn.springbootheadline.utils.Result;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cglib.core.AbstractClassGenerator.getCurrent;

/**
* @author 36229
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2025-10-15 20:30:11
*/
@Service
public class  HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo){
        IPage<Map> page=new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMypage(page,portalVo);
        List<Map> records = page.getRecords();
        Map data= new HashMap();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());

        Map pageInfo=new HashMap();
        pageInfo.put("pageInfo",data);
        return Result.ok(pageInfo);
    }
   @Override
    public Result showHeadlineDetail(Integer hid){
        Map data=headlineMapper.queryDetailMap(hid);
        Map headlineMap=new HashMap();
        headlineMap.put("headline",data);
        Headline headline=new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer)data.get("pageViews")+1);
        headlineMapper.updateById(headline);
        return Result.ok(headlineMap);
   }

    @Override
    public Result publish(Headline headline,String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(headline);
    }

    @Override
    public Result updateDate(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(headline);
    }
}




