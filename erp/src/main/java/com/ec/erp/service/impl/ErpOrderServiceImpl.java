package com.ec.erp.service.impl;

import com.ec.common.utils.DateUtils;
import com.ec.erp.domain.*;
import com.ec.erp.mapper.*;
import com.ec.erp.service.IErpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 库存销售订单Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpOrderServiceImpl implements IErpOrderService {
    @Autowired
    ErpClientMapper erpClientMapper;
    @Autowired
    ErpTaxInfoMapper erpTaxInfoMapper;
    @Autowired
    ErpStorageFlowMapper erpStorageFlowMapper;
    @Autowired
    ErpProductMapper erpProductMapper;
    @Autowired
    private ErpOrderMapper erpOrderMapper;

    /**
     * 查询库存销售订单
     *
     * @param id 库存销售订单主键
     * @return 库存销售订单
     */
    @Override
    public ErpOrder selectErpOrderById(Long id) {
        ErpOrder erpOrder = erpOrderMapper.selectErpOrderById(id);
        erpOrder.setClientInfo(erpClientMapper.selectErpClientById(erpOrder.getClientId()));
        erpOrder.setTaxInfo(erpTaxInfoMapper.selectErpTaxInfoById(erpOrder.getTaxInfoId()));
        erpOrder.setProductList(erpProductMapper.selectErpProductByOrderId(erpOrder.getId()));
        return erpOrder;
    }

    /**
     * 查询库存销售订单列表
     *
     * @param erpOrder 库存销售订单
     * @return 库存销售订单
     */
    @Override
    public List<ErpOrder> selectErpOrderList(ErpOrder erpOrder) {
        List<ErpOrder> erpOrderList = erpOrderMapper.selectErpOrderList(erpOrder);

        // 查询客户信息
        erpOrderList.stream().forEach(item -> {
            ErpClient erpClient = erpClientMapper.selectErpClientById(item.getClientId());
            item.setClientInfo(erpClient);
        });
        return erpOrderList;
    }

    /**
     * 新增库存销售订单
     *
     * @param erpOrder 库存销售订单
     * @return 结果
     */
    @Override
    @Transactional
    public int insertErpOrder(ErpOrder erpOrder){
        int rows = 0;
        erpOrder.setCreateTime(DateUtils.getNowDate());
        Long clientID = erpOrder.getClientInfo().getId();

        // 如果是新客户
        if (clientID == null) {
            rows += erpClientMapper.insertErpClient(erpOrder.getClientInfo());
            clientID = erpOrder.getClientInfo().getId();
            System.out.println(String.format("clientID: %s", clientID));
        }

        // 关联客户
        erpOrder.setClientId(clientID);

        // 如果需要开票
        if (erpOrder.getTaxNeed() == 1) {
            rows += erpTaxInfoMapper.insertErpTaxInfo(erpOrder.getTaxInfo());
            Long taxInfoID = erpOrder.getTaxInfo().getId();
            erpOrder.setTaxInfoId(taxInfoID); // 关联开票信息
        }

        // 新增销售订单
        rows += erpOrderMapper.insertErpOrder(erpOrder);

        // 新增库存流水
        rows += erpOrder.getProductList().stream().mapToInt(erpProduct -> {
            ErpStorageFlow erpStorageFlow = new ErpStorageFlow();
            erpStorageFlow.setType((long) 11);
            erpStorageFlow.setOrderId(erpOrder.getId());
            erpStorageFlow.setProductId(erpProduct.getId());
            erpStorageFlow.setAmount(erpProduct.getProductAmount());
            erpStorageFlow.setPrice(erpProduct.getProductPrice());
            return erpStorageFlowMapper.insertErpStorageFlow(erpStorageFlow);
        }).sum();

        return rows;
    }

    /**
     * 修改库存销售订单
     *
     * @param erpOrder 库存销售订单
     * @return 结果
     */
    @Override
    public int updateErpOrder(ErpOrder erpOrder) {
        return erpOrderMapper.updateErpOrder(erpOrder);
    }

    /**
     * 批量删除库存销售订单
     *
     * @param ids 需要删除的库存销售订单主键
     * @return 结果
     */
    @Override
    public int deleteErpOrderByIds(Long[] ids) {
        return erpOrderMapper.deleteErpOrderByIds(ids);
    }

    /**
     * 删除库存销售订单信息
     *
     * @param id 库存销售订单主键
     * @return 结果
     */
    @Override
    public int deleteErpOrderById(Long id) {
        return erpOrderMapper.deleteErpOrderById(id);
    }
}
