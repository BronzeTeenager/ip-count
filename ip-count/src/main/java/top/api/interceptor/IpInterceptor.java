package top.api.interceptor;



import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.api.domain.IpInfo;
import top.api.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ip拦截器
 */
@Slf4j
@Component
public class IpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取ip地址
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = ip.replace(" ", "").trim();
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = ip.split(",");
            ip = ips[0];
        }

        // 使用自带方法获取ip,如果不一致,优先使用自带的
        String _ip = request.getRemoteAddr();
        if (_ip != null){
            if (! ip.equals(_ip)){
                ip = _ip;
            }
        }

        IpInfo ipInfo = IpUtils.getAddress(ip);

        if (ipInfo == null){
            ipInfo = new IpInfo();
            ipInfo.setProvince("未知");
            ipInfo.setCountry("未知");
            ipInfo.setCity("未知");
            ipInfo.setRegion("未知");
            ipInfo.setIsp("未知");
            ipInfo.setIp(ip);
        }

        ipInfo.setIp(ip);

        // 将ip信息存入到域中
        request.setAttribute("ipInfo",ipInfo);

        return true;
    }
}
