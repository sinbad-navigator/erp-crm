package com.ec.erp.service.impl;

import com.ec.common.utils.DateUtils;
import com.ec.common.utils.uuid.IdUtils;
import com.ec.erp.domain.ErpProductSn;
import com.ec.erp.mapper.ErpProductSnMapper;
import com.ec.erp.service.IErpProductSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品条码Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpProductSnServiceImpl implements IErpProductSnService {
    @Autowired
    private ErpProductSnMapper erpProductSnMapper;

    /**
     * 查询商品条码
     *
     * @param snUuid 商品条码主键
     * @return 商品条码
     */
    @Override
    public ErpProductSn selectErpProductSnBySnUuid(String snUuid) {
        return erpProductSnMapper.selectErpProductSnBySnUuid(snUuid);
    }

    /**
     * 查询商品条码列表
     *
     * @param erpProductSn 商品条码
     * @return 商品条码
     */
    @Override
    public List<ErpProductSn> selectErpProductSnList(ErpProductSn erpProductSn) {
        return erpProductSnMapper.selectErpProductSnList(erpProductSn);
    }

    /**
     * 新增商品条码
     *
     * @param erpProductSn 商品条码
     * @return 结果
     */
    @Override
    public int insertErpProductSn(ErpProductSn erpProductSn) {
        erpProductSn.setCreateTime(DateUtils.getNowDate());
        erpProductSn.setSnUuid(IdUtils.randomUUID());
        return erpProductSnMapper.insertErpProductSn(erpProductSn);
    }

    /**
     * 修改商品条码
     *
     * @param erpProductSn 商品条码
     * @return 结果
     */
    @Override
    public int updateErpProductSn(ErpProductSn erpProductSn) {
        return erpProductSnMapper.updateErpProductSn(erpProductSn);
    }

    /**
     * 批量删除商品条码
     *
     * @param snUuids 需要删除的商品条码主键
     * @return 结果
     */
    @Override
    public int deleteErpProductSnBySnUuids(String[] snUuids) {
        return erpProductSnMapper.deleteErpProductSnBySnUuids(snUuids);
    }

    /**
     * 删除商品条码信息
     *
     * @param snUuid 商品条码主键
     * @return 结果
     */
    @Override
    public int deleteErpProductSnBySnUuid(String snUuid) {
        return erpProductSnMapper.deleteErpProductSnBySnUuid(snUuid);
    }
}
