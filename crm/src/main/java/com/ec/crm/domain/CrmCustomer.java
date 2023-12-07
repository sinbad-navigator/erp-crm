package com.ec.crm.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户对象 crm_customer
 *
 * @author xxxx
 * @date xxxx-01-13
 */
public class CrmCustomer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private String code;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String name;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String linkman;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 地区
     */
    @Excel(name = "地区")
    private String region;

    /**
     * 客户行业
     */
    @Excel(name = "客户行业")
    private String customerIndustry;

    /**
     * 客户级别
     */
    @Excel(name = "客户级别")
    private String customerRank;

    /**
     * 客户状态
     */
    @Excel(name = "客户状态")
    private String customerStatus;

    /**
     * 状态(0线索 1客户 2公海)
     */
    private String status;

    /**
     * 线索名称
     */
    @Excel(name = "线索名称")
    private String cluesName;

    /**
     * 线索来源
     */
    @Excel(name = "线索来源")
    private String cluesSource;

    /**
     * 线索状态
     */
    @Excel(name = "线索状态")
    private String cluesStatus;

    /**
     * 是否成交(0未成交 1成交)
     */
    private String dealStatus;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    private String owner;

    /**
     * 删除标志(0代表存在 2代表删除)
     */
    private String delFlag;

    /**
     * 版本
     */
    private Long version;

    /**
     * 最后跟进记录
     */
    private String lastUpdateRecord;

    /**
     * 最后跟进时间
     */
    private Date lastFollowupTime;

    /**
     * 下次跟进时间
     */
    private Date nextFollowupTime;

    /**
     * 前负责人
     */
    private String preOwner;

    /**
     * 转公海时间
     */
    private Date toPoolTime;

    /**
     * 转客户时间
     */
    private Date toCustomerTime;

    /**
     * 公海类型
     */
    private String poolType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCustomerIndustry() {
        return customerIndustry;
    }

    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry;
    }

    public String getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(String customerRank) {
        this.customerRank = customerRank;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCluesName() {
        return cluesName;
    }

    public void setCluesName(String cluesName) {
        this.cluesName = cluesName;
    }

    public String getCluesSource() {
        return cluesSource;
    }

    public void setCluesSource(String cluesSource) {
        this.cluesSource = cluesSource;
    }

    public String getCluesStatus() {
        return cluesStatus;
    }

    public void setCluesStatus(String cluesStatus) {
        this.cluesStatus = cluesStatus;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLastUpdateRecord() {
        return lastUpdateRecord;
    }

    public void setLastUpdateRecord(String lastUpdateRecord) {
        this.lastUpdateRecord = lastUpdateRecord;
    }

    public Date getLastFollowupTime() {
        return lastFollowupTime;
    }

    public void setLastFollowupTime(Date lastFollowupTime) {
        this.lastFollowupTime = lastFollowupTime;
    }

    public Date getNextFollowupTime() {
        return nextFollowupTime;
    }

    public void setNextFollowupTime(Date nextFollowupTime) {
        this.nextFollowupTime = nextFollowupTime;
    }

    public String getPreOwner() {
        return preOwner;
    }

    public void setPreOwner(String preOwner) {
        this.preOwner = preOwner;
    }

    public Date getToPoolTime() {
        return toPoolTime;
    }

    public void setToPoolTime(Date toPoolTime) {
        this.toPoolTime = toPoolTime;
    }

    public Date getToCustomerTime() {
        return toCustomerTime;
    }

    public void setToCustomerTime(Date toCustomerTime) {
        this.toCustomerTime = toCustomerTime;
    }

    public String getPoolType() {
        return poolType;
    }

    public void setPoolType(String poolType) {
        this.poolType = poolType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("name", getName())
                .append("linkman", getLinkman())
                .append("phone", getPhone())
                .append("region", getRegion())
                .append("customerIndustry", getCustomerIndustry())
                .append("customerRank", getCustomerRank())
                .append("customerStatus", getCustomerStatus())
                .append("status", getStatus())
                .append("cluesName", getCluesName())
                .append("cluesSource", getCluesSource())
                .append("cluesStatus", getCluesStatus())
                .append("dealStatus", getDealStatus())
                .append("owner", getOwner())
                .append("delFlag", getDelFlag())
                .append("version", getVersion())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("lastUpdateRecord", getLastUpdateRecord())
                .append("lastFollowupTime", getLastFollowupTime())
                .append("nextFollowupTime", getNextFollowupTime())
                .append("preOwner", getPreOwner())
                .append("toPoolTime", getToPoolTime())
                .append("toCustomerTime", getToCustomerTime())
                .append("poolType", getPoolType())
                .toString();
    }
}
