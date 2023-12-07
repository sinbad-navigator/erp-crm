package com.ec.saas.service;

import com.ec.saas.dto.TenantDatabaseDTO;
import com.ec.saas.domain.MasterTenant;

import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author ec
 */
public interface IMasterTenantService {

    MasterTenant selectMasterTenant(String tenant);

    List<MasterTenant> selectMasterTenants(MasterTenant masterTenant);

    int insertMasterTenant(TenantDatabaseDTO tenantDatabaseDTO);

    int insertMasterTenant(MasterTenant masterTenant);

    String checkTenantNameUnique(String tenantName);

    /**
     * 修改保存租户信息
     *
     * @param tenant 租户信息
     * @return 结果
     */
    public int updateMasterTenant(MasterTenant tenant);

    public MasterTenant selectMasterTenantById(Long id);

    public int deleteMasterTenantByIds(Long[] ids);
}
