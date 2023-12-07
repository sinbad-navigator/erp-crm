package com.ec.auth.manager.factory;

import com.ec.common.constant.Constants;
import com.ec.common.utils.LogUtils;
import com.ec.common.utils.ServletUtils;
import com.ec.common.utils.StringUtils;
import com.ec.common.utils.ip.AddressUtils;
import com.ec.common.utils.ip.IpUtils;
import com.ec.common.utils.spring.SpringUtils;
import com.ec.auth.datasource.DynamicDataSourceContextHolder;
import com.ec.sys.domain.SysLogininfor;
import com.ec.sys.domain.SysOperLog;
import com.ec.sys.service.ISysLogininforService;
import com.ec.sys.service.ISysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author ec
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        return recordLogininfor(null, username, status, message, args);
    }

    /**
     * 记录登录信息
     *
     * @param tenant
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(String tenant, final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                //切换数据源
                if (StringUtils.isNotBlank(tenant))
                    DynamicDataSourceContextHolder.setDataSourceKey(tenant);
                // 插入数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);
            }
        };
    }


    /**
     * 操作日志记录
     *
     * @param tenant
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(String tenant, final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                //切换数据源
                DynamicDataSourceContextHolder.setDataSourceKey(tenant);
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }
}
