package com.ec.saas.form;

import com.ec.common.core.domain.model.LoginBody;
import lombok.Data;

/**
 * @Author:
 * @Date:
 * @Description:
 */
@Data
public class TenantRegisterBody extends LoginBody {

    /**
     * 租户ID，唯一
     */
    public String tenantName;

    /**
     * 管理员用户名
     */
    public String adminName;

    /**
     * 管理员密码
     */
    public String adminPass;

}
