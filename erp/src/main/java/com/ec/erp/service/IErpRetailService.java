package com.ec.erp.service;

import com.ec.erp.domain.ErpRetail;

import java.util.List;

/**
 * 零售出库Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpRetailService {
    /**
     * 查询零售出库
     *
     * @param id 零售出库主键
     * @return 零售出库
     */
    public ErpRetail selectErpRetailById(Long id);

    /**
     * 查询零售出库列表
     *
     * @param erpRetail 零售出库
     * @return 零售出库集合
     */
    public List<ErpRetail> selectErpRetailList(ErpRetail erpRetail);

    /**
     * 新增零售出库
     *
     * @param erpRetail 零售出库
     * @return 结果
     */
    public int insertErpRetail(ErpRetail erpRetail);

    /**
     * 修改零售出库
     *
     * @param erpRetail 零售出库
     * @return 结果
     */
    public int updateErpRetail(ErpRetail erpRetail);

    /**
     * 批量删除零售出库
     *
     * @param ids 需要删除的零售出库主键集合
     * @return 结果
     */
    public int deleteErpRetailByIds(Long[] ids);

    /**
     * 删除零售出库信息
     *
     * @param id 零售出库主键
     * @return 结果
     */
    public int deleteErpRetailById(Long id);
}
