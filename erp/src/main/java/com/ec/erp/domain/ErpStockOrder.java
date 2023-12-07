package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 进货流水单对象 erp_stock_order
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpStockOrder extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 进货单ID
     */
    private Long id;

    /**
     * 订单流水ID
     */
    @Excel(name = "订单流水ID")
    private Long flowId;

    /**
     * 应付总金额
     */
    @Excel(name = "应付总金额")
    private BigDecimal totalPrice;

    /**
     * 实付总金额
     */
    @Excel(name = "实付总金额")
    private BigDecimal actualPayment;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String supplierName;

    /**
     * 经手人
     */
    @Excel(name = "经手人")
    private String handlerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("flowId", getFlowId())
                .append("createTime", getCreateTime())
                .append("totalPrice", getTotalPrice())
                .append("actualPayment", getActualPayment())
                .append("supplierName", getSupplierName())
                .append("handlerName", getHandlerName())
                .append("remark", getRemark())
                .toString();
    }
}
