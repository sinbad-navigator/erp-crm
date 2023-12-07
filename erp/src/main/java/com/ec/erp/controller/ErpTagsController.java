package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpTags;
import com.ec.erp.service.IErpTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/tags")
public class ErpTagsController extends BaseController {
    @Autowired
    private IErpTagsService erpTagsService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpTags erpTags) {
        startPage();
        List<ErpTags> list = erpTagsService.selectErpTagsList(erpTags);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpTags erpTags) {
        List<ErpTags> list = erpTagsService.selectErpTagsList(erpTags);
        ExcelUtil<ErpTags> util = new ExcelUtil<ErpTags>(ErpTags.class);
        return util.exportExcel(list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:query')")
    @GetMapping(value = "/{tagUuid}")
    public AjaxResult getInfo(@PathVariable("tagUuid") String tagUuid) {
        return AjaxResult.success(erpTagsService.selectErpTagsByTagUuid(tagUuid));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpTags erpTags) {
        return toAjax(erpTagsService.insertErpTags(erpTags));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpTags erpTags) {
        return toAjax(erpTagsService.updateErpTags(erpTags));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('erp:tags:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagUuids}")
    public AjaxResult remove(@PathVariable String[] tagUuids) {
        return toAjax(erpTagsService.deleteErpTagsByTagUuids(tagUuids));
    }
}
