package com.ec.erp.service;

import com.ec.erp.domain.ErpOrder;
import com.ec.erp.domain.ErpOrder;

import java.util.List;

/**
 * 库存销售订单Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpOrderService {
    /**
     * 查询库存销售订单
     *
     * @param id 库存销售订单主键
     * @return 库存销售订单
     */
    public ErpOrder selectErpOrderById(Long id);

    /**
     * 查询库存销售订单列表
     *
     * @param erpOrder 库存销售订单
     * @return 库存销售订单集合
     */
    public List<ErpOrder> selectErpOrderList(ErpOrder erpOrder);

    /**
     * 新增库存销售订单
     *
     * @param erpOrder 库存销售订单
     * @return 结果
     */
    public int insertErpOrder(ErpOrder erpOrder);

    /**
     * 修改库存销售订单
     *
     * @param erpOrder 库存销售订单
     * @return 结果
     */
    public int updateErpOrder(ErpOrder erpOrder);

    /**
     * 批量删除库存销售订单
     *
     * @param ids 需要删除的库存销售订单主键集合
     * @return 结果
     */
    public int deleteErpOrderByIds(Long[] ids);

    /**
     * 删除库存销售订单信息
     *
     * @param id 库存销售订单主键
     * @return 结果
     */
    public int deleteErpOrderById(Long id);
}
