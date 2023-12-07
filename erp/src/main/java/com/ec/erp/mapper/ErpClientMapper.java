package com.ec.erp.mapper;

import com.ec.erp.domain.ErpClient;

import java.util.List;

/**
 * 库存客户Mapper接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface ErpClientMapper {
    /**
     * 查询库存客户
     *
     * @param id 库存客户主键
     * @return 库存客户
     */
    public ErpClient selectErpClientById(Long id);

    /**
     * 查询库存客户列表
     *
     * @param erpClient 库存客户
     * @return 库存客户集合
     */
    public List<ErpClient> selectErpClientList(ErpClient erpClient);

    /**
     * 新增库存客户
     *
     * @param erpClient 库存客户
     * @return 结果
     */
    public int insertErpClient(ErpClient erpClient);

    /**
     * 修改库存客户
     *
     * @param erpClient 库存客户
     * @return 结果
     */
    public int updateErpClient(ErpClient erpClient);

    /**
     * 删除库存客户
     *
     * @param id 库存客户主键
     * @return 结果
     */
    public int deleteErpClientById(Long id);

    /**
     * 批量删除库存客户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpClientByIds(Long[] ids);
}
