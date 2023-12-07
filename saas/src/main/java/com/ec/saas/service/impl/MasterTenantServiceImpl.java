package com.ec.saas.service.impl;

import com.ec.common.annotation.DataSource;
import com.ec.common.constant.TenantConstants;
import com.ec.common.enums.DataSourceType;
import com.ec.common.exception.ServiceException;
import com.ec.common.utils.DateUtils;
import com.ec.saas.domain.MasterTenant;
import com.ec.saas.dto.TenantDatabaseDTO;
import com.ec.saas.mapper.MasterTenantMapper;
import com.ec.saas.service.IMasterTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:
 * @Date:
 * @Description:
 */
@Service
public class MasterTenantServiceImpl implements IMasterTenantService {

    @Autowired
    private MasterTenantMapper masterTenantMapper;

    @Override
    @DataSource(DataSourceType.MASTER)
    public MasterTenant selectMasterTenant(String tenant) {
        MasterTenant masterTenant = new MasterTenant();
        masterTenant.setTenant(tenant);
        return masterTenantMapper.selectMasterTenant(masterTenant);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public List<MasterTenant> selectMasterTenants(MasterTenant masterTenant) {
        return masterTenantMapper.selectMasterTenantList(masterTenant);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public int insertMasterTenant(TenantDatabaseDTO tenantDatabaseDTO) {
        MasterTenant masterTenant = new MasterTenant();
        masterTenant.setTenant(tenantDatabaseDTO.getTenantName());
        masterTenant.setUrl(tenantDatabaseDTO.getUrl());
        masterTenant.setDatabaseName(tenantDatabaseDTO.getTenantDatabase());
        masterTenant.setUsername(tenantDatabaseDTO.getDbUser());
        masterTenant.setPassword(tenantDatabaseDTO.getDbPass());
        masterTenant.setCreateTime(DateUtils.getNowDate());
        masterTenant.setExpirationDate(DateUtils.addDays(DateUtils.getNowDate(), 7));//有效期7天
        return masterTenantMapper.insertMasterTenant(masterTenant);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public int insertMasterTenant(MasterTenant masterTenant) {
        return masterTenantMapper.insertMasterTenant(masterTenant);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public String checkTenantNameUnique(String tenantName) {
        int count = masterTenantMapper.checkTenantNameUnique(tenantName);
        if (count > 0) {
            return TenantConstants.NOT_UNIQUE;
        }
        return TenantConstants.UNIQUE;
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public int updateMasterTenant(MasterTenant tenant) {
        return masterTenantMapper.updateMasterTenant(tenant);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public MasterTenant selectMasterTenantById(Long id) {
        return masterTenantMapper.selectMasterTenantById(id);
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public int deleteMasterTenantByIds(Long[] ids) {
        for (Long id : ids) {
            MasterTenant tenant = selectMasterTenantById(id);
            if (tenant == null) {
                throw new ServiceException(String.format("%1$s的数据不存在,不能删除", id));
            }
        }
        return masterTenantMapper.deleteMasterTenantByIds(ids);
    }
}
