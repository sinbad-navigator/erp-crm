package com.ec.crm.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: eddie
 * @Date: xxxx/1/19
 * @Description:
 */
@Data
public class TodayUpdatesVO {
    private String id;
    private String owner;
    private String customerName;
    private String content;
    private Date createTime;
}
