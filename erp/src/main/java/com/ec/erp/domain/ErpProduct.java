package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存产品对象 erp_product
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpProduct extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 产品ID
     */
    private Long id;
    /**
     * 名称
     */
    @Excel(name = "名称")
    private String productName;
    /**
     * 型号
     */
    @Excel(name = "型号")
    private String productModel;
    /**
     * 单位
     */
    @Excel(name = "单位")
    private String productUnit;
    /**
     * 销售价格
     */
    @Excel(name = "销售价格")
    private BigDecimal productPrice;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String productRemark;
    private List<ErpTags> tags = new ArrayList<ErpTags>();
    private int num = 0;
    private Long productAmount;

    public ErpProduct() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("productName", getProductName())
                .append("productModel", getProductModel())
                .append("productUnit", getProductUnit())
                .append("productPrice", getProductPrice())
                .append("productRemark", getProductRemark())
                .toString();
    }

    public List<ErpTags> getTags() {
        return tags;
    }

    public void setTags(List<ErpTags> tags) {
        this.tags = tags;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }
}
