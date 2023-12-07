package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpStorageFlow;
import com.ec.erp.service.IErpStorageFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存流水Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/flow")
public class ErpStorageFlowController extends BaseController {
    @Autowired
    private IErpStorageFlowService erpStorageFlowService;

    /**
     * 查询库存流水列表
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpStorageFlow erpStorageFlow) {
        startPage();
        List<ErpStorageFlow> list = erpStorageFlowService.selectErpStorageFlowList(erpStorageFlow);
        return getDataTable(list);
    }

    /**
     * 导出库存流水列表
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:export')")
    @Log(title = "库存流水", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpStorageFlow erpStorageFlow) {
        List<ErpStorageFlow> list = erpStorageFlowService.selectErpStorageFlowList(erpStorageFlow);
        ExcelUtil<ErpStorageFlow> util = new ExcelUtil<ErpStorageFlow>(ErpStorageFlow.class);
        return util.exportExcel(list, "库存流水数据");
    }

    /**
     * 获取库存流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpStorageFlowService.selectErpStorageFlowById(id));
    }

    /**
     * 新增库存流水
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:add')")
    @Log(title = "库存流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpStorageFlow erpStorageFlow) {
        return toAjax(erpStorageFlowService.insertErpStorageFlow(erpStorageFlow));
    }

    /**
     * 修改库存流水
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:edit')")
    @Log(title = "库存流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpStorageFlow erpStorageFlow) {
        return toAjax(erpStorageFlowService.updateErpStorageFlow(erpStorageFlow));
    }

    /**
     * 删除库存流水
     */
    @PreAuthorize("@ss.hasPermi('erp:flow:remove')")
    @Log(title = "库存流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpStorageFlowService.deleteErpStorageFlowByIds(ids));
    }
}
