package com.ec.web.controller.system;

import com.ec.common.constant.TenantConstants;
import com.ec.auth.web.service.SysLoginService;
import com.ec.auth.web.service.TenantRegisterService;
import com.ec.saas.dto.TenantDatabaseDTO;
import com.ec.saas.form.TenantRegisterBody;
import com.ec.saas.service.IMasterTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.auth.web.service.SysRegisterService;
import com.ec.sys.service.ISysConfigService;

import java.sql.SQLException;

/**
 * 注册验证
 *
 * @author ec
 */
@RestController
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private TenantRegisterService tenantRegisterService;

    @Autowired
    private IMasterTenantService masterTenantService;

    @Autowired
    private SysLoginService loginService;


    @PostMapping("/register")
    public AjaxResult registerTenant(@RequestBody TenantRegisterBody tenantRegisterBody) {
        loginService.validateCaptcha(tenantRegisterBody.getTenantName(), tenantRegisterBody.getCode(), tenantRegisterBody.getUuid());

        if (TenantConstants.NOT_UNIQUE.equals(masterTenantService.checkTenantNameUnique(tenantRegisterBody.tenantName))) {
            return AjaxResult.error("注册'" + tenantRegisterBody.getTenantName() + "'失败，账号已存在");
        }
        TenantDatabaseDTO tenantDatabase = null;
        try {
            tenantDatabase = tenantRegisterService.initDatabase(tenantRegisterBody);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return AjaxResult.error("注册'" + tenantRegisterBody.getTenantName() + "'失败，创建租户时发生错误");
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error("注册'" + tenantRegisterBody.getTenantName() + "'失败，请与我们联系");
        }
        int i = masterTenantService.insertMasterTenant(tenantDatabase);
        return toAjax(i);
    }
}
