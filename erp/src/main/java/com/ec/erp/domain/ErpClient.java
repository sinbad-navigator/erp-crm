package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存客户对象 erp_client
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpClient extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String clientNickname;

    /**
     * 客户手机
     */
    @Excel(name = "客户手机")
    private String clientPhone;

    /**
     * 客户收货地址
     */
    @Excel(name = "客户收货地址")
    private String shippingAddress;

    /**
     * 性别 先生:0 女士:1
     */
    @Excel(name = "性别 先生:0 女士:1")
    private Long gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNickname() {
        return clientNickname;
    }

    public void setClientNickname(String clientNickname) {
        this.clientNickname = clientNickname;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clientNickname", getClientNickname())
                .append("clientPhone", getClientPhone())
                .append("shippingAddress", getShippingAddress())
                .append("gender", getGender())
                .toString();
    }
}
