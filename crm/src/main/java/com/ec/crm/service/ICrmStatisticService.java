package com.ec.crm.service;

import com.ec.crm.domain.CrmCustomer;

import java.util.List;
import java.util.Map;

/**
 * @Author: xxxx
 * @Date: xxxx/1/27
 * @Description: 客户统计
 */
public interface ICrmStatisticService {

    Map<String, Object> followupInfo(String owner);

    Map<String, Object> customerInfo(String owner);

    List<CrmCustomer> todayFollowupList(String owner);
}
