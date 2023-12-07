package com.ec.crm.form;

import lombok.Data;

/**
 * @Author: xxxx
 * @Date: xxxx/1/14
 * @Description: 转化成客的DTO
 */
@Data
public class ToCustomerDTO {
    public Long id;
    public String name;
    public String customerStatus;
    public String customerRank;
}
