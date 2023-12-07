package com.ec.crm.controller;

import com.ec.common.core.controller.BaseController;
import com.ec.common.core.domain.AjaxResult;
import com.ec.common.core.domain.entity.SysUser;
import com.ec.crm.vo.OwnerVO;
import com.ec.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xxxx
 * @Date:
 * @Description: 负责人控制器
 */
@RestController
@RequestMapping("/crm/owner")
public class OwnerController extends BaseController {
    @Autowired
    private ISysUserService userService;

    /**
     * 查询负责人列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        List<SysUser> list = userService.selectUserList(new SysUser());
        List<OwnerVO> map = list.stream().map(owner -> new OwnerVO(owner.getUserName(), owner.getNickName())).collect(Collectors.toList());

        return AjaxResult.success(map);
    }
}
