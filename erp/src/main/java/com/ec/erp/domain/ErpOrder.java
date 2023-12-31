package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存销售订单对象 erp_order
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpOrder extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 销售订单ID
     */
    private Long id;

    /**
     * 库存流水ID
     */
    @Excel(name = "库存流水ID")
    private Long detailId;

    /**
     * 开票信息ID
     */
    @Excel(name = "开票信息ID")
    private Long taxInfoId;

    /**
     * 订单金额
     */
    @Excel(name = "订单金额")
    private BigDecimal totalPrice;

    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal actualPayment;

    /**
     * 经手人姓名
     */
    @Excel(name = "经手人姓名")
    private String handlerName;

    /**
     * 客户ID
     */
    @Excel(name = "客户ID")
    private Long clientId;

    /**
     * 客户信息
     */
    private ErpClient clientInfo;
    /**
     * 是否需要开票
     */
    private Long taxNeed;
    /**
     * 产品清单
     */
    private List<ErpProduct> productList;
    /**
     * 开票信息
     */
    private ErpTaxInfo taxInfo;
    /**
     * 订单名称
     */
    private String orderName;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getTaxInfoId() {
        return taxInfoId;
    }

    public void setTaxInfoId(Long taxInfoId) {
        this.taxInfoId = taxInfoId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ErpClient getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ErpClient clientInfo) {
        this.clientInfo = clientInfo;
    }

    public ErpTaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(ErpTaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }

    public Long getTaxNeed() {
        return taxNeed;
    }

    public void setTaxNeed(Long taxNeed) {
        this.taxNeed = taxNeed;
    }

    public List<ErpProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ErpProduct> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("detailId", getDetailId())
                .append("createTime", getCreateTime())
                .append("taxInfoId", getTaxInfoId())
                .append("totalPrice", getTotalPrice())
                .append("actualPayment", getActualPayment())
                .append("handlerName", getHandlerName())
                .append("clientId", getClientId())
                .append("remark", getRemark())
                .toString();
    }
}
