package com.xuxulearn.springbootheadline.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * @TableName news_headline
 */
@Data
@TableName(value ="news_headline")
public class Headline {
    @TableId
    private Integer hid;

    private String title;

    private String article;

    private Integer type;
    private Integer publisher;
    private Integer pageViews;
    private Date createTime;

    private Date updateTime;

    @Version
    private Integer version;

    private Integer isDeleted;
}