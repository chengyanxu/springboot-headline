package com.xuxulearn.springbootheadline.service;

import com.xuxulearn.springbootheadline.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuxulearn.springbootheadline.pojo.vo.PortalVo;
import com.xuxulearn.springbootheadline.utils.Result;

/**
* @author 36229
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2025-10-15 20:30:11
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateDate(Headline headline);

}
