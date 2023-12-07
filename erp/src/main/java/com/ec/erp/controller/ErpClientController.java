package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.erp.domain.ErpClient;
import com.ec.erp.service.IErpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存客户Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@RestController
@RequestMapping("/erp/client")
public class ErpClientController extends BaseController {
    @Autowired
    private IErpClientService erpClientService;

    /**
     * 查询库存客户列表
     */
    //@ApiOperation("查询库存客户列表")
    @PreAuthorize("@ss.hasPermi('erp:client:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpClient erpClient) {
        startPage();
        List<ErpClient> list = erpClientService.selectErpClientList(erpClient);
        return getDataTable(list);
    }

    /**
     * 导出库存客户列表
     */
    //@ApiOperation("导出库存客户列表")
    @PreAuthorize("@ss.hasPermi('erp:client:export')")
    @Log(title = "库存客户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpClient erpClient) {
        List<ErpClient> list = erpClientService.selectErpClientList(erpClient);
        ExcelUtil<ErpClient> util = new ExcelUtil<ErpClient>(ErpClient.class);
        return util.exportExcel(list, "库存客户数据");
    }

    /**
     * 获取库存客户详细信息
     */
    //@ApiOperation("获取库存客户详细信息")
    @PreAuthorize("@ss.hasPermi('erp:client:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpClientService.selectErpClientById(id));
    }

    /**
     * 新增库存客户
     */
    //@ApiOperation("新增库存客户")
    @PreAuthorize("@ss.hasPermi('erp:client:add')")
    @Log(title = "库存客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpClient erpClient) {
        return toAjax(erpClientService.insertErpClient(erpClient));
    }

    /**
     * 修改库存客户
     */
    //@ApiOperation("修改库存客户")
    @PreAuthorize("@ss.hasPermi('erp:client:edit')")
    @Log(title = "库存客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpClient erpClient) {
        return toAjax(erpClientService.updateErpClient(erpClient));
    }

    /**
     * 删除库存客户
     */
    @PreAuthorize("@ss.hasPermi('erp:client:remove')")
    @Log(title = "库存客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpClientService.deleteErpClientByIds(ids));
    }
}
