package com.ec.erp.service.impl;

import com.ec.common.utils.DateUtils;
import com.ec.common.utils.uuid.IdUtils;
import com.ec.erp.domain.ErpTags;
import com.ec.erp.mapper.ErpTagsMapper;
import com.ec.erp.service.IErpTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签Service业务层处理
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Service
public class ErpTagsServiceImpl implements IErpTagsService {
    @Autowired
    private ErpTagsMapper erpTagsMapper;

    /**
     * 查询标签
     *
     * @param tagUuid 标签主键
     * @return 标签
     */
    @Override
    public ErpTags selectErpTagsByTagUuid(String tagUuid) {
        return erpTagsMapper.selectErpTagsByTagUuid(tagUuid);
    }

    /**
     * 查询标签列表
     *
     * @param erpTags 标签
     * @return 标签
     */
    @Override
    public List<ErpTags> selectErpTagsList(ErpTags erpTags) {
        return erpTagsMapper.selectErpTagsList(erpTags);
    }

    /**
     * 新增标签
     *
     * @param erpTags 标签
     * @return 结果
     */
    @Override
    public int insertErpTags(ErpTags erpTags) {
        erpTags.setCreateTime(DateUtils.getNowDate());
        erpTags.setTagUuid(IdUtils.randomUUID());
        return erpTagsMapper.insertErpTags(erpTags);
    }

    /**
     * 修改标签
     *
     * @param erpTags 标签
     * @return 结果
     */
    @Override
    public int updateErpTags(ErpTags erpTags) {
        return erpTagsMapper.updateErpTags(erpTags);
    }

    /**
     * 批量删除标签
     *
     * @param tagUuids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteErpTagsByTagUuids(String[] tagUuids) {
        return erpTagsMapper.deleteErpTagsByTagUuids(tagUuids);
    }

    /**
     * 删除标签信息
     *
     * @param tagUuid 标签主键
     * @return 结果
     */
    @Override
    public int deleteErpTagsByTagUuid(String tagUuid) {
        return erpTagsMapper.deleteErpTagsByTagUuid(tagUuid);
    }
}
