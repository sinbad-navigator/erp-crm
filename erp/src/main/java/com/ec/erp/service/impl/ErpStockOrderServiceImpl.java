package com.ec.erp.service.impl;

import com.ec.common.utils.DateUtils;
import com.ec.erp.domain.ErpStockOrder;
import com.ec.erp.mapper.ErpStockOrderMapper;
import com.ec.erp.service.IErpStockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进货流水单Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpStockOrderServiceImpl implements IErpStockOrderService {
    @Autowired
    private ErpStockOrderMapper erpStockOrderMapper;

    /**
     * 查询进货流水单
     *
     * @param id 进货流水单主键
     * @return 进货流水单
     */
    @Override
    public ErpStockOrder selectErpStockOrderById(Long id) {
        return erpStockOrderMapper.selectErpStockOrderById(id);
    }

    /**
     * 查询进货流水单列表
     *
     * @param erpStockOrder 进货流水单
     * @return 进货流水单
     */
    @Override
    public List<ErpStockOrder> selectErpStockOrderList(ErpStockOrder erpStockOrder) {
        return erpStockOrderMapper.selectErpStockOrderList(erpStockOrder);
    }

    /**
     * 新增进货流水单
     *
     * @param erpStockOrder 进货流水单
     * @return 结果
     */
    @Override
    public int insertErpStockOrder(ErpStockOrder erpStockOrder) {
        erpStockOrder.setCreateTime(DateUtils.getNowDate());
        return erpStockOrderMapper.insertErpStockOrder(erpStockOrder);
    }

    /**
     * 修改进货流水单
     *
     * @param erpStockOrder 进货流水单
     * @return 结果
     */
    @Override
    public int updateErpStockOrder(ErpStockOrder erpStockOrder) {
        return erpStockOrderMapper.updateErpStockOrder(erpStockOrder);
    }

    /**
     * 批量删除进货流水单
     *
     * @param ids 需要删除的进货流水单主键
     * @return 结果
     */
    @Override
    public int deleteErpStockOrderByIds(Long[] ids) {
        return erpStockOrderMapper.deleteErpStockOrderByIds(ids);
    }

    /**
     * 删除进货流水单信息
     *
     * @param id 进货流水单主键
     * @return 结果
     */
    @Override
    public int deleteErpStockOrderById(Long id) {
        return erpStockOrderMapper.deleteErpStockOrderById(id);
    }
}
