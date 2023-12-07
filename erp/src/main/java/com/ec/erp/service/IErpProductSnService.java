package com.ec.erp.service;

import com.ec.erp.domain.ErpProductSn;

import java.util.List;

/**
 * 商品条码Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpProductSnService {
    /**
     * 查询商品条码
     *
     * @param snUuid 商品条码主键
     * @return 商品条码
     */
    public ErpProductSn selectErpProductSnBySnUuid(String snUuid);

    /**
     * 查询商品条码列表
     *
     * @param erpProductSn 商品条码
     * @return 商品条码集合
     */
    public List<ErpProductSn> selectErpProductSnList(ErpProductSn erpProductSn);

    /**
     * 新增商品条码
     *
     * @param erpProductSn 商品条码
     * @return 结果
     */
    public int insertErpProductSn(ErpProductSn erpProductSn);

    /**
     * 修改商品条码
     *
     * @param erpProductSn 商品条码
     * @return 结果
     */
    public int updateErpProductSn(ErpProductSn erpProductSn);

    /**
     * 批量删除商品条码
     *
     * @param snUuids 需要删除的商品条码主键集合
     * @return 结果
     */
    public int deleteErpProductSnBySnUuids(String[] snUuids);

    /**
     * 删除商品条码信息
     *
     * @param snUuid 商品条码主键
     * @return 结果
     */
    public int deleteErpProductSnBySnUuid(String snUuid);
}
