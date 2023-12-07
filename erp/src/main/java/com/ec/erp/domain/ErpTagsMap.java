package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标签映射对象 erp_tags_map
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpTagsMap extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品条码 UUID
     */
    @Excel(name = "产品条码 UUID")
    private String tagUuid;

    /**
     * 产品 ID
     */
    @Excel(name = "产品 ID")
    private String productId;

    public String getTagUuid() {
        return tagUuid;
    }

    public void setTagUuid(String tagUuid) {
        this.tagUuid = tagUuid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tagUuid", getTagUuid())
                .append("productId", getProductId())
                .toString();
    }
}
