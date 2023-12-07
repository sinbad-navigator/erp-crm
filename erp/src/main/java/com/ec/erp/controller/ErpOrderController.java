package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpOrder;
import com.ec.erp.service.IErpClientService;
import com.ec.erp.service.IErpOrderService;
import com.ec.erp.service.IErpStorageFlowService;
import com.ec.erp.service.IErpTaxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存销售订单Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/order")
public class ErpOrderController extends BaseController {
    @Autowired
    private IErpOrderService erpOrderService;

    @Autowired
    private IErpClientService erpClientService;

    @Autowired
    private IErpTaxInfoService erpTaxInfoService;

    @Autowired
    private IErpStorageFlowService erpStorageFlowService;

    /**
     * 查询库存销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('erp:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpOrder erpOrder) {
        startPage();
        List<ErpOrder> list = erpOrderService.selectErpOrderList(erpOrder);
        return getDataTable(list);
    }

    /**
     * 导出库存销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('erp:order:export')")
    @Log(title = "库存销售订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpOrder erpOrder) {
        List<ErpOrder> list = erpOrderService.selectErpOrderList(erpOrder);
        ExcelUtil<ErpOrder> util = new ExcelUtil<ErpOrder>(ErpOrder.class);
        return util.exportExcel(list, "库存销售订单数据");
    }

    /**
     * 获取库存销售订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpOrderService.selectErpOrderById(id));
    }

    /**
     * 新增库存销售订单
     */
    @PreAuthorize("@ss.hasPermi('erp:order:add')")
    @Log(title = "库存销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpOrder erpOrder) {
        try {
            erpOrderService.insertErpOrder(erpOrder);
        } catch (Exception e) {
            return AjaxResult.error(e.toString());
        }
        return toAjax(1);
    }

    /**
     * 修改库存销售订单
     */
    @PreAuthorize("@ss.hasPermi('erp:order:edit')")
    @Log(title = "库存销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpOrder erpOrder) {
        return toAjax(erpOrderService.updateErpOrder(erpOrder));
    }

    /**
     * 删除库存销售订单
     */
    @PreAuthorize("@ss.hasPermi('erp:order:remove')")
    @Log(title = "库存销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpOrderService.deleteErpOrderByIds(ids));
    }
}
