package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存开票信息对象 erp_tax_info
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpTaxInfo extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 开票信息 ID
     */
    private Long id;

    /**
     * 纳税人识别号
     */
    @Excel(name = "纳税人识别号")
    private String taxpayerId;

    /**
     * 公司名称
     */
    @Excel(name = "公司名称")
    private String companyName;

    /**
     * 公司地址
     */
    @Excel(name = "公司地址")
    private String companyAddress;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String contactTel;

    /**
     * 银行账户
     */
    @Excel(name = "银行账户")
    private String bankAccount;

    /**
     * 开户行
     */
    @Excel(name = "开户行")
    private String depositBank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taxpayerId", getTaxpayerId())
                .append("companyName", getCompanyName())
                .append("companyAddress", getCompanyAddress())
                .append("contactTel", getContactTel())
                .append("bankAccount", getBankAccount())
                .append("depositBank", getDepositBank())
                .toString();
    }
}
