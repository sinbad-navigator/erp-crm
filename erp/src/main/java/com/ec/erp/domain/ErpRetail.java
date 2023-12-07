package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 零售出库对象 erp_retail
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpRetail extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 零售出货单ID
     */
    private Long id;

    /**
     * 库存流水ID
     */
    @Excel(name = "库存流水ID")
    private Long flowId;

    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal actualPayment;

    /**
     * 经手人
     */
    @Excel(name = "经手人")
    private String handlerName;

    /**
     * 商品条码 UUID
     */
    @Excel(name = "商品条码 UUID")
    private String snUuid;

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

    public String getSnUuid() {
        return snUuid;
    }

    public void setSnUuid(String snUuid) {
        this.snUuid = snUuid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("flowId", getFlowId())
                .append("createTime", getCreateTime())
                .append("actualPayment", getActualPayment())
                .append("handlerName", getHandlerName())
                .append("remark", getRemark())
                .append("snUuid", getSnUuid())
                .toString();
    }
}
