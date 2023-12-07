package com.ec.erp.service.impl;

import com.ec.common.utils.DateUtils;
import com.ec.erp.domain.ErpRetail;
import com.ec.erp.mapper.ErpRetailMapper;
import com.ec.erp.service.IErpRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 零售出库Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpRetailServiceImpl implements IErpRetailService {
    @Autowired
    private ErpRetailMapper erpRetailMapper;

    /**
     * 查询零售出库
     *
     * @param id 零售出库主键
     * @return 零售出库
     */
    @Override
    public ErpRetail selectErpRetailById(Long id) {
        return erpRetailMapper.selectErpRetailById(id);
    }

    /**
     * 查询零售出库列表
     *
     * @param erpRetail 零售出库
     * @return 零售出库
     */
    @Override
    public List<ErpRetail> selectErpRetailList(ErpRetail erpRetail) {
        return erpRetailMapper.selectErpRetailList(erpRetail);
    }

    /**
     * 新增零售出库
     *
     * @param erpRetail 零售出库
     * @return 结果
     */
    @Override
    public int insertErpRetail(ErpRetail erpRetail) {
        erpRetail.setCreateTime(DateUtils.getNowDate());
        return erpRetailMapper.insertErpRetail(erpRetail);
    }

    /**
     * 修改零售出库
     *
     * @param erpRetail 零售出库
     * @return 结果
     */
    @Override
    public int updateErpRetail(ErpRetail erpRetail) {
        return erpRetailMapper.updateErpRetail(erpRetail);
    }

    /**
     * 批量删除零售出库
     *
     * @param ids 需要删除的零售出库主键
     * @return 结果
     */
    @Override
    public int deleteErpRetailByIds(Long[] ids) {
        return erpRetailMapper.deleteErpRetailByIds(ids);
    }

    /**
     * 删除零售出库信息
     *
     * @param id 零售出库主键
     * @return 结果
     */
    @Override
    public int deleteErpRetailById(Long id) {
        return erpRetailMapper.deleteErpRetailById(id);
    }
}
