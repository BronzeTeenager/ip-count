package top.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.api.dao.AppDao;
import top.api.dao.IpDao;
import top.api.domain.App;
import top.api.domain.Ip;
import top.api.domain.IpInfo;
import top.api.domain.ProvinceCount;
import top.api.domain.msg.MsgIp;
import top.api.service.IpService;
import top.api.utils.CheckDateUtil;
import top.api.utils.Code;
import top.api.utils.Msg;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class) // 开启事务
public class IpServiceImpl extends ServiceImpl<IpDao, Ip> implements IpService {

    @Autowired
    private AppDao appDao;

    @Autowired
    private IpDao ipDao;

    @Override
    public Msg ipCount(String appId, IpInfo ipInfo) {
        /**
         * 多次写入tb_app表中的ip,request字段+1,导致高并发update发生死锁
         * 解决方案,使用select进行查询,替代update
         */
        // tb_app今日请求 + 1
        //LambdaUpdateWrapper<App> updateWrapper = new LambdaUpdateWrapper<>();
        //String sql = "";


        // 查询tb_ip(今日)该ip是否存入,没有则添加,有则+1
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

        LambdaQueryWrapper<Ip> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ip::getId,appId);
        queryWrapper.eq(Ip::getIp,ipInfo.getIp());
        queryWrapper.eq(Ip::getDate,time);

        Ip dbIp = ipDao.selectOne(queryWrapper);

        if (dbIp == null){
            // 没有则添加
            Ip ip = new Ip();
            ip.setId(Integer.valueOf(appId));
            ip.setProvince(ipInfo.getProvince());
            ip.setIp(ipInfo.getIp());
            ip.setIpInfo(ipInfo.getIpInfo());
            ip.setRequestCount(1);
            ip.setDate(time);

            ipDao.insert(ip);

            //sql = "today_ip_number = (today_ip_number + 1), today_request_number = (today_request_number + 1),ip_count = (ip_count + 1), request_count = (request_count + 1) where app_id = "+appId;
            //ipDao.updateIpRequestAddOne(Integer.valueOf(appId));
        }else {
            // 有则 + 1
            LambdaUpdateWrapper<Ip> ipLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            ipLambdaUpdateWrapper.setSql("request_count = (request_count + 1) WHERE id = "+appId+" and ip = '"+ipInfo.getIp()+"' and date = '"+time+"'");
            ipDao.update(null,ipLambdaUpdateWrapper);

            //sql = "today_request_number = (today_request_number + 1), request_count = (request_count + 1) where app_id = "+appId;
            //ipDao.updateRequestAddOne(Integer.valueOf(appId));
        }

        // tb_app
        //updateWrapper.setSql(sql);

        //appDao.update(null,updateWrapper);

        return new Msg(Code.CODE_OK,"ok",null);
    }


    @Override
    public Msg getTodayAllIp(Integer appId) {

        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

        List<MsgIp> msgIps = ipDao.selectAllIp(appId, time);

        return new Msg(Code.CODE_OK,"查询成功!",msgIps);
    }

    @Override
    public Msg getAllProvinceCount(Integer appId) {

        // 查询所有省
        List<ProvinceCount> provinceCounts = ipDao.selectAllProvince(appId);

        List<ProvinceCount> qcs = new ArrayList<>();

        for (ProvinceCount provinceCount : provinceCounts) {
            // 单个地区
            String province = provinceCount.getProvince();
            // 查询单个地区的访问数量
            ProvinceCount provinceCount1 = ipDao.selectAllProvinceRequestCount(appId, province);

            // 查询单个地区的ip数量
            ProvinceCount provinceIpCount = ipDao.selectAllProvinceIpCount(appId, province);

            provinceCount1.setIpCount(provinceIpCount.getIpCount());

            qcs.add(provinceCount1);
        }

        return new Msg(Code.CODE_OK, "查询成功!", qcs);

    }

    @Override
    public Msg selectLikeIp(Integer appId, String str) {

        List<MsgIp> ipList = ipDao.selectLikeIp(appId, str);

        return new Msg(Code.CODE_OK,null,ipList);
    }

    @Override
    public Msg getIpAndDate(Integer appId, String date) {
        boolean dateFlag = CheckDateUtil.isValidDate(date);
        if (! dateFlag){
            return new Msg(Code.CODE_NO,"日期格式错误",null);
        }

        List<MsgIp> ipList = ipDao.selectAllIp(appId, date);

        return new Msg(Code.CODE_OK,"查询成功!",ipList);
    }

    @Override
    public Msg getMonthIpCountAndSum(Integer appId) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        // 获取当月的每一天
        List<String> dateList = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = year + "-" + month + "-" + i;
            dateList.add(aDate);
        }

        // 保存数据的list
        List list = new ArrayList();

        // 遍历查询,查询到当天结束
        for (String date : dateList) {
            // 查询每日ip数量
            LambdaQueryWrapper<Ip> ipLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ipLambdaQueryWrapper.eq(Ip::getId,appId);
            ipLambdaQueryWrapper.eq(Ip::getDate,date);
            Integer IpCount = ipDao.selectCount(ipLambdaQueryWrapper);

            // 查询每日 访问数量
            QueryWrapper<Ip> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("sum(request_count) as sum");
            queryWrapper.eq("id",appId);
            queryWrapper.eq("date",date);
            List<Object> objs = ipDao.selectObjs(queryWrapper);
            // 防止没有查询到 空指针异常
            if (objs.get(0) == null){
                objs.set(0,0);
            }
            Integer requestSum = Integer.valueOf(objs.get(0).toString());

            // 将查询到的数据封装到 json
            Map<String, Object> map = new HashMap<>();
            map.put("date",date);
            map.put("ipCount",IpCount);
            map.put("requestSum",requestSum);

            list.add(map);

            // 截止到当天
            if (time.equals(date)){
                break;
            }
        }


        return new Msg(Code.CODE_OK,"ok",list);
    }

}
