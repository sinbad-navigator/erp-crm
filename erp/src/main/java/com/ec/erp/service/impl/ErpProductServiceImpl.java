package com.ec.erp.service.impl;

import com.ec.erp.domain.ErpProduct;
import com.ec.erp.mapper.ErpProductMapper;
import com.ec.erp.service.IErpProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存产品Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpProductServiceImpl implements IErpProductService {
    @Autowired
    private ErpProductMapper erpProductMapper;

    /**
     * 查询库存产品
     *
     * @param id 库存产品主键
     * @return 库存产品
     */
    @Override
    public ErpProduct selectErpProductById(Long id) {
        return erpProductMapper.selectErpProductById(id);
    }

    /**
     * 查询库存产品列表
     *
     * @param erpProduct 库存产品
     * @return 库存产品
     */
    @Override
    public List<ErpProduct> selectErpProductList(ErpProduct erpProduct) {
        return erpProductMapper.selectErpProductList(erpProduct);
    }

    /**
     * 新增库存产品
     *
     * @param erpProduct 库存产品
     * @return 结果
     */
    @Override
    public int insertErpProduct(ErpProduct erpProduct) {
        return erpProductMapper.insertErpProduct(erpProduct);
    }

    /**
     * 修改库存产品
     *
     * @param erpProduct 库存产品
     * @return 结果
     */
    @Override
    public int updateErpProduct(ErpProduct erpProduct) {
        return erpProductMapper.updateErpProduct(erpProduct);
    }

    /**
     * 批量删除库存产品
     *
     * @param ids 需要删除的库存产品主键
     * @return 结果
     */
    @Override
    public int deleteErpProductByIds(Long[] ids) {
        return erpProductMapper.deleteErpProductByIds(ids);
    }

    /**
     * 删除库存产品信息
     *
     * @param id 库存产品主键
     * @return 结果
     */
    @Override
    public int deleteErpProductById(Long id) {
        return erpProductMapper.deleteErpProductById(id);
    }

    @Override
    public List<ErpProduct> selectProductListByTag(ErpProduct erpProduct) {
        return erpProductMapper.selectProductListByTag(erpProduct);
    }
}
