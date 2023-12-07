package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpTaxInfo;
import com.ec.erp.service.IErpTaxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存开票信息Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/info")
public class ErpTaxInfoController extends BaseController {
    @Autowired
    private IErpTaxInfoService erpTaxInfoService;

    /**
     * 查询库存开票信息列表
     */
    @PreAuthorize("@ss.hasPermi('erp:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpTaxInfo erpTaxInfo) {
        startPage();
        List<ErpTaxInfo> list = erpTaxInfoService.selectErpTaxInfoList(erpTaxInfo);
        return getDataTable(list);
    }

    /**
     * 导出库存开票信息列表
     */
    @PreAuthorize("@ss.hasPermi('erp:info:export')")
    @Log(title = "库存开票信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpTaxInfo erpTaxInfo) {
        List<ErpTaxInfo> list = erpTaxInfoService.selectErpTaxInfoList(erpTaxInfo);
        ExcelUtil<ErpTaxInfo> util = new ExcelUtil<ErpTaxInfo>(ErpTaxInfo.class);
        return util.exportExcel(list, "库存开票信息数据");
    }

    /**
     * 获取库存开票信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpTaxInfoService.selectErpTaxInfoById(id));
    }

    /**
     * 新增库存开票信息
     */
    @PreAuthorize("@ss.hasPermi('erp:info:add')")
    @Log(title = "库存开票信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpTaxInfo erpTaxInfo) {
        return toAjax(erpTaxInfoService.insertErpTaxInfo(erpTaxInfo));
    }

    /**
     * 修改库存开票信息
     */
    @PreAuthorize("@ss.hasPermi('erp:info:edit')")
    @Log(title = "库存开票信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpTaxInfo erpTaxInfo) {
        return toAjax(erpTaxInfoService.updateErpTaxInfo(erpTaxInfo));
    }

    /**
     * 删除库存开票信息
     */
    @PreAuthorize("@ss.hasPermi('erp:info:remove')")
    @Log(title = "库存开票信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpTaxInfoService.deleteErpTaxInfoByIds(ids));
    }
}
