package com.ec.erp.service.impl;

import com.ec.erp.domain.ErpClient;
import com.ec.erp.mapper.ErpClientMapper;
import com.ec.erp.service.IErpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存客户Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpClientServiceImpl implements IErpClientService {
    @Autowired
    private ErpClientMapper erpClientMapper;

    /**
     * 查询库存客户
     *
     * @param id 库存客户主键
     * @return 库存客户
     */
    @Override
    public ErpClient selectErpClientById(Long id) {
        return erpClientMapper.selectErpClientById(id);
    }

    /**
     * 查询库存客户列表
     *
     * @param erpClient 库存客户
     * @return 库存客户
     */
    @Override
    public List<ErpClient> selectErpClientList(ErpClient erpClient) {
        return erpClientMapper.selectErpClientList(erpClient);
    }

    /**
     * 新增库存客户
     *
     * @param erpClient 库存客户
     * @return 结果
     */
    @Override
    public int insertErpClient(ErpClient erpClient) {
        return erpClientMapper.insertErpClient(erpClient);
    }

    /**
     * 修改库存客户
     *
     * @param erpClient 库存客户
     * @return 结果
     */
    @Override
    public int updateErpClient(ErpClient erpClient) {
        return erpClientMapper.updateErpClient(erpClient);
    }

    /**
     * 批量删除库存客户
     *
     * @param ids 需要删除的库存客户主键
     * @return 结果
     */
    @Override
    public int deleteErpClientByIds(Long[] ids) {
        return erpClientMapper.deleteErpClientByIds(ids);
    }

    /**
     * 删除库存客户信息
     *
     * @param id 库存客户主键
     * @return 结果
     */
    @Override
    public int deleteErpClientById(Long id) {
        return erpClientMapper.deleteErpClientById(id);
    }
}
