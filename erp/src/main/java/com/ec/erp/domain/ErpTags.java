package com.ec.erp.domain;

import com.ec.common.annotation.Excel;
import com.ec.common.core.domain.ErpBaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标签对象 erp_tags
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpTags extends ErpBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标签 UUID
     */
    private String tagUuid;

    /**
     * 标签名
     */
    @Excel(name = "标签名")
    private String tagName;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTagUuid() {
        return tagUuid;
    }

    public void setTagUuid(String tagUuid) {
        this.tagUuid = tagUuid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tagUuid", getTagUuid())
                .append("tagName", getTagName())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .toString();
    }
}
