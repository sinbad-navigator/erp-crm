package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品条码对象 erp_product_sn
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpProductSn extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String snUuid;

    /**
     * 商品sn条码
     */
    @Excel(name = "商品sn条码")
    private String snCode;

    /**
     * 产品ID
     */
    @Excel(name = "产品ID")
    private Long productId;

    /**
     * 在库: 0 已出库: 1
     */
    @Excel(name = "在库: 0 已出库: 1")
    private Long status;

    public String getSnUuid() {
        return snUuid;
    }

    public void setSnUuid(String snUuid) {
        this.snUuid = snUuid;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("snUuid", getSnUuid())
                .append("snCode", getSnCode())
                .append("createTime", getCreateTime())
                .append("productId", getProductId())
                .append("status", getStatus())
                .toString();
    }
}
