package com.ec.web.controller.system;

import com.ec.common.annotation.Log;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.page.TableDataInfo;
import com.ec.common.enums.BusinessType;
import com.ec.saas.domain.MasterTenant;
import com.ec.saas.service.IMasterTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户信息操作处理
 *
 * @author ec
 */
@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController {
    @Autowired
    private IMasterTenantService masterTenantService;

    /**
     * 获取租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(MasterTenant masterTenant) {
        List<MasterTenant> t = masterTenantService.selectMasterTenants(masterTenant);
        return getDataTable(t);
    }

    /**
     * 根据id获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(masterTenantService.selectMasterTenantById(id));
    }
    /**
     * 修改租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "租户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody MasterTenant tenant) {
/*        if (UserConstants.UNIQUE.equals(masterTenantService.checkTenantNameUnique(tenant.getTenant()))) {
            //return AjaxResult.error("修改租户'" + tenant.getTenant() + "'失败，租户名称已存在");

        }*/
        return toAjax(masterTenantService.updateMasterTenant(tenant));
    }

    /**
     * 删除租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "租户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long[] id) {

        return toAjax(masterTenantService.deleteMasterTenantByIds(id));
    }

}
