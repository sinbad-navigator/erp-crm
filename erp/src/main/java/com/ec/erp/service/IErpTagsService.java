package com.ec.erp.service;

import com.ec.erp.domain.ErpTags;

import java.util.List;

/**
 * 标签Service接口
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
public interface IErpTagsService {
    /**
     * 查询标签
     *
     * @param tagUuid 标签主键
     * @return 标签
     */
    public ErpTags selectErpTagsByTagUuid(String tagUuid);

    /**
     * 查询标签列表
     *
     * @param erpTags 标签
     * @return 标签集合
     */
    public List<ErpTags> selectErpTagsList(ErpTags erpTags);

    /**
     * 新增标签
     *
     * @param erpTags 标签
     * @return 结果
     */
    public int insertErpTags(ErpTags erpTags);

    /**
     * 修改标签
     *
     * @param erpTags 标签
     * @return 结果
     */
    public int updateErpTags(ErpTags erpTags);

    /**
     * 批量删除标签
     *
     * @param tagUuids 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteErpTagsByTagUuids(String[] tagUuids);

    /**
     * 删除标签信息
     *
     * @param tagUuid 标签主键
     * @return 结果
     */
    public int deleteErpTagsByTagUuid(String tagUuid);
}
