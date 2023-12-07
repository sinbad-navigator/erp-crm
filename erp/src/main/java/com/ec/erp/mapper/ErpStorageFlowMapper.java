package com.ec.erp.mapper;

import com.ec.erp.domain.ErpStorageFlow;

import java.util.List;

/**
 * 库存流水Mapper接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface ErpStorageFlowMapper {
    /**
     * 查询库存流水
     *
     * @param id 库存流水主键
     * @return 库存流水
     */
    public ErpStorageFlow selectErpStorageFlowById(Long id);

    /**
     * 查询库存流水列表
     *
     * @param erpStorageFlow 库存流水
     * @return 库存流水集合
     */
    public List<ErpStorageFlow> selectErpStorageFlowList(ErpStorageFlow erpStorageFlow);

    /**
     * 新增库存流水
     *
     * @param erpStorageFlow 库存流水
     * @return 结果
     */
    public int insertErpStorageFlow(ErpStorageFlow erpStorageFlow);

    /**
     * 修改库存流水
     *
     * @param erpStorageFlow 库存流水
     * @return 结果
     */
    public int updateErpStorageFlow(ErpStorageFlow erpStorageFlow);

    /**
     * 删除库存流水
     *
     * @param id 库存流水主键
     * @return 结果
     */
    public int deleteErpStorageFlowById(Long id);

    /**
     * 批量删除库存流水
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpStorageFlowByIds(Long[] ids);
}
