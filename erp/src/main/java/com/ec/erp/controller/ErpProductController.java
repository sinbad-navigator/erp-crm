package com.ec.erp.controller;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.common.utils.poi.ExcelUtil;
import com.ec.common.annotation.Log;
import com.ec.common.core.domain.AjaxResult;
import com.ec.erp.domain.ErpProduct;
import com.ec.erp.domain.ErpTags;
import com.ec.erp.service.IErpProductService;
import com.ec.erp.service.IErpTagsMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存产品Controller
 *
 * @author xxx
 * @date xxxx-xx-xx
 */
@Api("产品信息管理")
@RestController
@RequestMapping("/erp/product")
public class ErpProductController extends BaseController {
    @Autowired
    private IErpProductService erpProductService;

    @Autowired
    private IErpTagsMapService erpTagsMapService;

    /**
     * 查询库存产品列表
     */
    @ApiOperation("查询库存产品列表")
    @PreAuthorize("@ss.hasPermi('erp:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProduct erpProduct) {
        startPage();
        List<ErpProduct> list = erpProductService.selectErpProductList(erpProduct);
        list = list.stream().map(item -> {
            List<ErpTags> erpTagsList = erpTagsMapService.selectProductTagListByProdProductID(item.getId());
            item.setTags(erpTagsList);
            return item;
        }).collect(Collectors.toList());
//        List<ErpProductExtended> erpProductExtendedList = list.stream().map(item -> {
//            ErpProductExtended productItem = (ErpProductExtended)item;
//            List<ErpTags> erpTagsList = erpTagsMapService.selectProductTagListByProdProductID(productItem.getId());
//            productItem.setTags(erpTagsList);
//            return productItem;
//        }).collect(Collectors.toList());
        return getDataTable(list);
    }

    @PostMapping("/querPorductByTags")
    public AjaxResult querPorductByTags(@RequestBody ErpProduct erpProduct) {
        return AjaxResult.success(erpProductService.selectProductListByTag(erpProduct));
    }

    /**
     * 导出库存产品列表
     */
    @PreAuthorize("@ss.hasPermi('erp:product:export')")
    @Log(title = "库存产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ErpProduct erpProduct) {
        List<ErpProduct> list = erpProductService.selectErpProductList(erpProduct);
        ExcelUtil<ErpProduct> util = new ExcelUtil<ErpProduct>(ErpProduct.class);
        return util.exportExcel(list, "库存产品数据");
    }

    /**
     * 获取库存产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('erp:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(erpProductService.selectErpProductById(id));
    }

    /**
     * 新增库存产品
     */
    @PreAuthorize("@ss.hasPermi('erp:product:add')")
    @Log(title = "库存产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProduct erpProduct) {
        return toAjax(erpProductService.insertErpProduct(erpProduct));
    }

    /**
     * 修改库存产品
     */
    @PreAuthorize("@ss.hasPermi('erp:product:edit')")
    @Log(title = "库存产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProduct erpProduct) {
        return toAjax(erpProductService.updateErpProduct(erpProduct));
    }

    /**
     * 删除库存产品
     */
    @PreAuthorize("@ss.hasPermi('erp:product:remove')")
    @Log(title = "库存产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(erpProductService.deleteErpProductByIds(ids));
    }
}
