package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpStockOrder;
import com.ec.erp.service.IErpStockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进货流水单Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/stock")
public class ErpStockOrderController extends BaseController {
    @Autowired
    private IErpStockOrderService erpStockOrderService;

    /**
     * 查询进货流水单列表
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpStockOrder erpStockOrder) {
        startPage();
        List<ErpStockOrder> list = erpStockOrderService.selectErpStockOrderList(erpStockOrder);
        return getDataTable(list);
    }

    /**
     * 导出进货流水单列表
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:export')")
    @Log(title = "进货流水单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpStockOrder erpStockOrder) {
        List<ErpStockOrder> list = erpStockOrderService.selectErpStockOrderList(erpStockOrder);
        ExcelUtil<ErpStockOrder> util = new ExcelUtil<ErpStockOrder>(ErpStockOrder.class);
        return util.exportExcel(list, "进货流水单数据");
    }

    /**
     * 获取进货流水单详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpStockOrderService.selectErpStockOrderById(id));
    }

    /**
     * 新增进货流水单
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:add')")
    @Log(title = "进货流水单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpStockOrder erpStockOrder) {
        return toAjax(erpStockOrderService.insertErpStockOrder(erpStockOrder));
    }

    /**
     * 修改进货流水单
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:edit')")
    @Log(title = "进货流水单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpStockOrder erpStockOrder) {
        return toAjax(erpStockOrderService.updateErpStockOrder(erpStockOrder));
    }

    /**
     * 删除进货流水单
     */
    @PreAuthorize("@ss.hasPermi('erp:stock:remove')")
    @Log(title = "进货流水单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpStockOrderService.deleteErpStockOrderByIds(ids));
    }
}
