package com.ec.saas.domain;

import com.ec.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author: xxxx
 * @Date: 2021/7/25
 * @Description:
 */
@Data
public class MasterTenant extends BaseEntity {
    private Long id;
    private String tenant;
    private String url;
    private String databaseName;
    private String username;
    private String password;
    private String hostName;
    private String status;
    private Date expirationDate;
}
