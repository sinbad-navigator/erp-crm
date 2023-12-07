package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpProductSn;
import com.ec.erp.service.IErpProductSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品条码Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/sn")
public class ErpProductSnController extends BaseController {
    @Autowired
    private IErpProductSnService erpProductSnService;

    /**
     * 查询商品条码列表
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductSn erpProductSn) {
        startPage();
        List<ErpProductSn> list = erpProductSnService.selectErpProductSnList(erpProductSn);
        return getDataTable(list);
    }

    /**
     * 导出商品条码列表
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:export')")
    @Log(title = "商品条码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpProductSn erpProductSn) {
        List<ErpProductSn> list = erpProductSnService.selectErpProductSnList(erpProductSn);
        ExcelUtil<ErpProductSn> util = new ExcelUtil<ErpProductSn>(ErpProductSn.class);
        return util.exportExcel(list, "商品条码数据");
    }

    /**
     * 获取商品条码详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:query')")
    @GetMapping(value = "/{snUuid}")
    public AjaxResult getInfo(@PathVariable("snUuid") String snUuid) {
        return AjaxResult.success(erpProductSnService.selectErpProductSnBySnUuid(snUuid));
    }

    /**
     * 新增商品条码
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:add')")
    @Log(title = "商品条码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductSn erpProductSn) {
        return toAjax(erpProductSnService.insertErpProductSn(erpProductSn));
    }

    /**
     * 修改商品条码
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:edit')")
    @Log(title = "商品条码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductSn erpProductSn) {
        return toAjax(erpProductSnService.updateErpProductSn(erpProductSn));
    }

    /**
     * 删除商品条码
     */
    @PreAuthorize("@ss.hasPermi('erp:sn:remove')")
    @Log(title = "商品条码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{snUuids}")
    public AjaxResult remove(@PathVariable String[] snUuids) {
        return toAjax(erpProductSnService.deleteErpProductSnBySnUuids(snUuids));
    }
}
