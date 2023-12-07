package com.ec.saas.mapper;

import java.util.List;

import com.ec.saas.domain.MasterTenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户
 *
 * @author ec
 */
@Mapper
public interface MasterTenantMapper {

    MasterTenant selectMasterTenant(MasterTenant masterTenant);

    List<MasterTenant> selectMasterTenants();

    /**
     * 新增租户
     *
     * @param masterTenant 租户数据库连接信息
     * @return 结果
     */
    int insertMasterTenant(MasterTenant masterTenant);


    List<MasterTenant> selectMasterTenantList(MasterTenant masterTenant);

    int checkTenantNameUnique(String tenantName);

    int updateMasterTenant(MasterTenant tenant);

    MasterTenant selectMasterTenantById(Long id);

    public int deleteMasterTenantByIds(Long[] ids);
}
