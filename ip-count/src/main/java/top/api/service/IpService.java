package top.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.api.domain.Ip;
import top.api.domain.IpInfo;
import top.api.utils.Msg;

public interface IpService extends IService<Ip> {
    /**
     * 添加访问
     * @param appId
     * @param ipInfo
     * @return
     */
    Msg ipCount(String appId, IpInfo ipInfo);



    /**
     * 查询今日所有ip信息
     * @param appId
     * @return
     */
    Msg getTodayAllIp(Integer appId);

    /**
     * 查询指定省份的所有总请求访问量
     * @param appId
     * @return
     */
    Msg getAllProvinceCount(Integer appId);


    /**
     * 模糊查询 ip
     * @param appId
     * @param str 模糊查询 内容
     * @return
     */
    Msg selectLikeIp(Integer appId,String str);

    /**
     * 根据指定日期查询 ip 信息
     * @param appId
     * @param date
     * @return
     */
    Msg getIpAndDate(Integer appId,String date);

    /**
     * 查询本月内 每日IP量和访问量
     * @param appId
     * @return
     */
    Msg getMonthIpCountAndSum(Integer appId);

}
