package com.ec.erp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存产品对象 erp_product
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public class ErpProductExtended extends ErpProduct {
    public List<ErpTags> tags = new ArrayList<ErpTags>();

    public ErpProductExtended() {
        super();
    }

    public List<ErpTags> getTags() {
        return tags;
    }

    public void setTags(List<ErpTags> tags) {
        this.tags = tags;
    }
}
