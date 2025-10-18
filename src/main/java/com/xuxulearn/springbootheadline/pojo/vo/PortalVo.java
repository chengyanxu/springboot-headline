package com.xuxulearn.springbootheadline.pojo.vo;

import lombok.Data;

@Data
public class PortalVo {
    private String keywords;
    private int type=0;
    private int pageNum=1;
    private int pageSize=10;
}
