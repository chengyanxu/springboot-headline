package com.xuxulearn.springbootheadline.controller;

import com.xuxulearn.springbootheadline.pojo.vo.PortalVo;
import com.xuxulearn.springbootheadline.service.HeadlineService;
import com.xuxulearn.springbootheadline.service.TypeService;
import com.xuxulearn.springbootheadline.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController//放入容器
@RequestMapping("portal")//端口访问
@CrossOrigin//跨域
public class ProtalController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }
    @PostMapping("findNewsPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result=headlineService.findNewsPage(portalVo);
        return result;

    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }
}
