package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpRetail;
import com.ec.erp.service.IErpRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 零售出库Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/retail")
public class ErpRetailController extends BaseController {
    @Autowired
    private IErpRetailService erpRetailService;

    /**
     * 查询零售出库列表
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpRetail erpRetail) {
        startPage();
        List<ErpRetail> list = erpRetailService.selectErpRetailList(erpRetail);
        return getDataTable(list);
    }

    /**
     * 导出零售出库列表
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:export')")
    @Log(title = "零售出库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpRetail erpRetail) {
        List<ErpRetail> list = erpRetailService.selectErpRetailList(erpRetail);
        ExcelUtil<ErpRetail> util = new ExcelUtil<ErpRetail>(ErpRetail.class);
        return util.exportExcel(list, "零售出库数据");
    }

    /**
     * 获取零售出库详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpRetailService.selectErpRetailById(id));
    }

    /**
     * 新增零售出库
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:add')")
    @Log(title = "零售出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpRetail erpRetail) {
        return toAjax(erpRetailService.insertErpRetail(erpRetail));
    }

    /**
     * 修改零售出库
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:edit')")
    @Log(title = "零售出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpRetail erpRetail) {
        return toAjax(erpRetailService.updateErpRetail(erpRetail));
    }

    /**
     * 删除零售出库
     */
    @PreAuthorize("@ss.hasPermi('erp:retail:remove')")
    @Log(title = "零售出库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpRetailService.deleteErpRetailByIds(ids));
    }
}
