package com.ec.erp.service;

import com.ec.erp.domain.ErpStockOrder;

import java.util.List;

/**
 * 进货流水单Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpStockOrderService {
    /**
     * 查询进货流水单
     *
     * @param id 进货流水单主键
     * @return 进货流水单
     */
    public ErpStockOrder selectErpStockOrderById(Long id);

    /**
     * 查询进货流水单列表
     *
     * @param erpStockOrder 进货流水单
     * @return 进货流水单集合
     */
    public List<ErpStockOrder> selectErpStockOrderList(ErpStockOrder erpStockOrder);

    /**
     * 新增进货流水单
     *
     * @param erpStockOrder 进货流水单
     * @return 结果
     */
    public int insertErpStockOrder(ErpStockOrder erpStockOrder);

    /**
     * 修改进货流水单
     *
     * @param erpStockOrder 进货流水单
     * @return 结果
     */
    public int updateErpStockOrder(ErpStockOrder erpStockOrder);

    /**
     * 批量删除进货流水单
     *
     * @param ids 需要删除的进货流水单主键集合
     * @return 结果
     */
    public int deleteErpStockOrderByIds(Long[] ids);

    /**
     * 删除进货流水单信息
     *
     * @param id 进货流水单主键
     * @return 结果
     */
    public int deleteErpStockOrderById(Long id);
}
