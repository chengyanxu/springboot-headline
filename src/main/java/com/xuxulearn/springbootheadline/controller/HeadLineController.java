package com.xuxulearn.springbootheadline.controller;

import com.xuxulearn.springbootheadline.pojo.Headline;
import com.xuxulearn.springbootheadline.service.HeadlineService;
import com.xuxulearn.springbootheadline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("headline")
@RestController
@CrossOrigin
public class HeadLineController {
    @Autowired
    private final HeadlineService headlineService;

    public HeadLineController(HeadlineService headlineService) {
        this.headlineService = headlineService;
    }

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token){

        Result result=headlineService.publish(headline,token);
        return result;
    }
    @PostMapping("findHeadlineByHid")
    public Result findHeadlinByHid(Integer hid){
        Headline headline = headlineService.getById(hid);
        Map data=new HashMap();
        data.put("headline",headline);
        return Result.ok(data);
    }
    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result=headlineService.updateDate(headline);
        return result;
    }

    @PostMapping("removeByHid")
        public Result removeByHid(Integer Hid){
        headlineService.removeById(Hid);
        return Result.ok(null);
    }

}
