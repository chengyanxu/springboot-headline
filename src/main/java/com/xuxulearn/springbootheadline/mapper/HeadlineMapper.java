package com.xuxulearn.springbootheadline.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuxulearn.springbootheadline.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuxulearn.springbootheadline.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 36229
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2025-10-15 20:30:11
* @Entity com.xuxulearn.pojo.Headline
*/
@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMypage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);

}




