package com.ec.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ec.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ec.common.constant.Constants;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.domain.entity.SysMenu;
import com.ec.common.core.domain.entity.SysUser;
import com.ec.common.core.domain.model.LoginBody;
import com.ec.common.utils.SecurityUtils;
import com.ec.auth.web.service.SysLoginService;
import com.ec.auth.web.service.SysPermissionService;
import com.ec.sys.service.ISysMenuService;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证
 *
 * @author ec
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法，前端通过Header方式传递tenant信息
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, @RequestBody LoginBody loginBody) {
        String tenant = request.getHeader("tenant");

        if (StringUtils.isEmpty(tenant)) {
            return AjaxResult.error("租户ID不能为空");
        }

        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(tenant, loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
