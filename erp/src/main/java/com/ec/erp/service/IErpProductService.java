package com.ec.erp.service;

import com.ec.erp.domain.ErpProduct;

import java.util.List;

/**
 * 库存产品Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpProductService {
    /**
     * 查询库存产品
     *
     * @param id 库存产品主键
     * @return 库存产品
     */
    public ErpProduct selectErpProductById(Long id);

    /**
     * 查询库存产品列表
     *
     * @param erpProduct 库存产品
     * @return 库存产品集合
     */
    public List<ErpProduct> selectErpProductList(ErpProduct erpProduct);

    /**
     * 新增库存产品
     *
     * @param erpProduct 库存产品
     * @return 结果
     */
    public int insertErpProduct(ErpProduct erpProduct);

    /**
     * 修改库存产品
     *
     * @param erpProduct 库存产品
     * @return 结果
     */
    public int updateErpProduct(ErpProduct erpProduct);

    /**
     * 批量删除库存产品
     *
     * @param ids 需要删除的库存产品主键集合
     * @return 结果
     */
    public int deleteErpProductByIds(Long[] ids);

    /**
     * 删除库存产品信息
     *
     * @param id 库存产品主键
     * @return 结果
     */
    public int deleteErpProductById(Long id);

    public List<ErpProduct> selectProductListByTag(ErpProduct erpProduct);
}
