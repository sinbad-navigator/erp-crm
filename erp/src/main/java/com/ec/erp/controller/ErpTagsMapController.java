package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpTagsMap;
import com.ec.erp.service.IErpTagsMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签映射Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/map")
public class ErpTagsMapController extends BaseController {
    @Autowired
    private IErpTagsMapService erpTagsMapService;

    /**
     * 查询标签映射列表
     */
    @PreAuthorize("@ss.hasPermi('erp:map:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpTagsMap erpTagsMap) {
        startPage();
        List<ErpTagsMap> list = erpTagsMapService.selectErpTagsMapList(erpTagsMap);
        return getDataTable(list);
    }

    /**
     * 导出标签映射列表
     */
    @PreAuthorize("@ss.hasPermi('erp:map:export')")
    @Log(title = "标签映射", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpTagsMap erpTagsMap) {
        List<ErpTagsMap> list = erpTagsMapService.selectErpTagsMapList(erpTagsMap);
        ExcelUtil<ErpTagsMap> util = new ExcelUtil<ErpTagsMap>(ErpTagsMap.class);
        return util.exportExcel(list, "标签映射数据");
    }

    /**
     * 获取标签映射详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:map:query')")
    @GetMapping(value = "/{tagUuid}")
    public AjaxResult getInfo(@PathVariable("tagUuid") String tagUuid) {
        return AjaxResult.success(erpTagsMapService.selectErpTagsMapByTagUuid(tagUuid));
    }

    /**
     * 新增标签映射
     */
    @PreAuthorize("@ss.hasPermi('erp:map:add')")
    @Log(title = "标签映射", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpTagsMap erpTagsMap) {
        return toAjax(erpTagsMapService.insertErpTagsMap(erpTagsMap));
    }

    /**
     * 修改标签映射
     */
    @PreAuthorize("@ss.hasPermi('erp:map:edit')")
    @Log(title = "标签映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpTagsMap erpTagsMap) {
        return toAjax(erpTagsMapService.updateErpTagsMap(erpTagsMap));
    }

    /**
     * 删除标签映射
     */
    @PreAuthorize("@ss.hasPermi('erp:map:remove')")
    @Log(title = "标签映射", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagUuids}")
    public AjaxResult remove(@PathVariable String[] tagUuids) {
        return toAjax(erpTagsMapService.deleteErpTagsMapByTagUuids(tagUuids));
    }
}
