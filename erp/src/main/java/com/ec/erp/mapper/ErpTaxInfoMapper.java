package com.ec.erp.mapper;

import com.ec.erp.domain.ErpTaxInfo;

import java.util.List;

/**
 * 库存开票信息Mapper接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface ErpTaxInfoMapper {
    /**
     * 查询库存开票信息
     *
     * @param id 库存开票信息主键
     * @return 库存开票信息
     */
    public ErpTaxInfo selectErpTaxInfoById(Long id);

    /**
     * 查询库存开票信息列表
     *
     * @param erpTaxInfo 库存开票信息
     * @return 库存开票信息集合
     */
    public List<ErpTaxInfo> selectErpTaxInfoList(ErpTaxInfo erpTaxInfo);

    /**
     * 新增库存开票信息
     *
     * @param erpTaxInfo 库存开票信息
     * @return 结果
     */
    public int insertErpTaxInfo(ErpTaxInfo erpTaxInfo);

    /**
     * 修改库存开票信息
     *
     * @param erpTaxInfo 库存开票信息
     * @return 结果
     */
    public int updateErpTaxInfo(ErpTaxInfo erpTaxInfo);

    /**
     * 删除库存开票信息
     *
     * @param id 库存开票信息主键
     * @return 结果
     */
    public int deleteErpTaxInfoById(Long id);

    /**
     * 批量删除库存开票信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpTaxInfoByIds(Long[] ids);
}
