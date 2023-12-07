package com.ec.erp.mapper;

import com.ec.erp.domain.ErpTags;
import com.ec.erp.domain.ErpTagsMap;

import java.util.List;

/**
 * 标签映射Mapper接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface ErpTagsMapMapper {
    /**
     * 查询标签映射
     *
     * @param tagUuid 标签映射主键
     * @return 标签映射
     */
    public ErpTagsMap selectErpTagsMapByTagUuid(String tagUuid);

    /**
     * 查询标签映射列表
     *
     * @param erpTagsMap 标签映射
     * @return 标签映射集合
     */
    public List<ErpTagsMap> selectErpTagsMapList(ErpTagsMap erpTagsMap);

    /**
     * 新增标签映射
     *
     * @param erpTagsMap 标签映射
     * @return 结果
     */
    public int insertErpTagsMap(ErpTagsMap erpTagsMap);

    /**
     * 修改标签映射
     *
     * @param erpTagsMap 标签映射
     * @return 结果
     */
    public int updateErpTagsMap(ErpTagsMap erpTagsMap);

    /**
     * 删除标签映射
     *
     * @param tagUuid 标签映射主键
     * @return 结果
     */
    public int deleteErpTagsMapByTagUuid(String tagUuid);

    /**
     * 批量删除标签映射
     *
     * @param tagUuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpTagsMapByTagUuids(String[] tagUuids);

    List<ErpTagsMap> selectErpTagsMapListByProductID(long productID);

    /**
     * 查询产品相关的标签
     *
     * @param id 产品ID
     * @return 结果
     */
    List<ErpTags> selectProductTagListByProdProductID(Long id);
}
