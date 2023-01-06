package top.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.api.dao.AppDao;
import top.api.dao.IpDao;
import top.api.domain.App;
import top.api.domain.Ip;
import top.api.domain.msg.MsgApp;
import top.api.service.AppService;
import top.api.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class) // 开启事务
public class AppServiceImpl extends ServiceImpl<AppDao, App> implements AppService {

    @Autowired
    private AppDao appDao;

    @Autowired
    private IpDao ipDao;

    @Override
    public Msg add(App app, HttpServletRequest request) throws Exception {

        Integer id = (Integer) request.getAttribute("id");

        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId, id);
        queryWrapper.eq(App::getAppName, app.getAppName());

        App dbApp = appDao.selectOne(queryWrapper);


        if (dbApp != null) {
            return new Msg(Code.CODE_NO, "该项目已存在!", null);
        }

        // 将appid主键设置成userid一致
        app.setId(id);

        int flag = appDao.insert(app);

        MsgApp msgApp = new MsgApp();
        msgApp.setAppName(app.getAppName());
        msgApp.setNotes(app.getNotes());
        msgApp.setToken(AESUtil.encrypt(app.getAppId() + "", AESUtil.AESKey));

        return new Msg(flag > 0 ? Code.CODE_OK : Code.CODE_NO, flag > 0 ? "添加成功!" : "添加失败!", msgApp);
    }

    @Override
    public Msg getOneApp(Integer userId, Integer appId) throws Exception {

        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId, userId);
        queryWrapper.eq(App::getAppId, appId);
        App dbApp = appDao.selectOne(queryWrapper);

        MsgApp msgApp = null;
        if (dbApp != null) {
            // 查询到该项目
            msgApp = new MsgApp();
            msgApp.setToken(AESUtil.encrypt(dbApp.getAppId() + "", AESUtil.AESKey));
            msgApp.setAppName(dbApp.getAppName());
            msgApp.setNotes(dbApp.getNotes());


            // 查询今日ip
            Integer todayIpCount = ipDao.selectIpCountAndDate(appId, Tools.getTodayDate());
            // 查询今日请求数量
            Integer todayRequestSum = ipDao.selectRequestSumAndDate(appId, Tools.getTodayDate());
            // 查询昨日ip
            Integer yesterdayIpCount = ipDao.selectIpCountAndDate(appId, Tools.getYesterdayDate());
            // 查询昨日请求数量
            Integer yesterdayRequestSum = ipDao.selectRequestSumAndDate(appId, Tools.getYesterdayDate());
            // 查询项目总ip
            Integer ipCount = ipDao.selectIpCount(appId);
            // 查询项目总请求数量
            Integer requestSum = ipDao.selectRequestSum(appId);

            msgApp.setTodayIpNumber(todayIpCount);
            msgApp.setYesterdayIpNumber(yesterdayIpCount);
            msgApp.setTodayRequestNumber(todayRequestSum == null ? 0 : todayRequestSum);
            msgApp.setYesterdayRequestNumber(yesterdayRequestSum == null ? 0 : yesterdayRequestSum);
            msgApp.setIpCount(ipCount);
            msgApp.setRequestCount(requestSum == null ? 0 : requestSum);
        }
        return new Msg(Code.CODE_OK, "查询成功", msgApp);
    }

    @Override
    public Msg getAllApp(Integer userId) throws Exception {

        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId, userId);
        List<App> apps = appDao.selectList(queryWrapper);

        List<MsgApp> msgApps = new ArrayList<>();
        // 进行加密
        for (App app : apps) {
            MsgApp msgApp = new MsgApp();
            msgApp.setToken(AESUtil.encrypt(app.getAppId() + "", AESUtil.AESKey));
            msgApp.setAppName(app.getAppName());
            msgApp.setNotes(app.getNotes());

            // 查询今日ip
            Integer todayIpCount = ipDao.selectIpCountAndDate(app.getAppId(), Tools.getTodayDate());
            // 查询今日请求数量
            Integer todayRequestSum = ipDao.selectRequestSumAndDate(app.getAppId(), Tools.getTodayDate());
            // 查询昨日ip
            Integer yesterdayIpCount = ipDao.selectIpCountAndDate(app.getAppId(), Tools.getYesterdayDate());
            // 查询昨日请求数量
            Integer yesterdayRequestSum = ipDao.selectRequestSumAndDate(app.getAppId(), Tools.getYesterdayDate());
            // 查询项目总ip
            Integer ipCount = ipDao.selectIpCount(app.getAppId());
            // 查询项目总请求数量
            Integer requestSum = ipDao.selectRequestSum(app.getAppId());

            msgApp.setTodayIpNumber(todayIpCount);
            msgApp.setYesterdayIpNumber(yesterdayIpCount);
            msgApp.setTodayRequestNumber(todayRequestSum == null ? 0 : todayRequestSum);
            msgApp.setYesterdayRequestNumber(yesterdayRequestSum == null ? 0 : yesterdayRequestSum);
            msgApp.setIpCount(ipCount);
            msgApp.setRequestCount(requestSum == null ? 0 : requestSum);

            msgApps.add(msgApp);
        }

        return new Msg(0, "查询成功!", msgApps);

    }

    @Override
    public boolean updateIPRequest() {
        Integer flag = 0;
        Integer flag2 = 0;

        try {
            // 将今日次数复制到昨日上
            flag = appDao.updateIPRequest();

            // 将今日次数全部 初始化为 0
            flag2 = appDao.updateIPRequestSet0();

        } catch (Exception e) {
            log.error("将今日次数复制到昨日字段上,将今日次数全部初始化为0, 出错了!");
        }

        return flag > 0 && flag2 > 0;
    }

    @Override
    public boolean deleteApp(Integer userId, Integer appId) {
        // 删除tb_ip表中的数据
        LambdaQueryWrapper<Ip> ipLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ipLambdaQueryWrapper.eq(Ip::getId, appId);

        int deleteIpFlag = ipDao.delete(ipLambdaQueryWrapper);

        // 删除tb_app表中的项目
        LambdaQueryWrapper<App> appLambdaQueryWrapper = new LambdaQueryWrapper<>();

        appLambdaQueryWrapper.eq(App::getId, userId);
        appLambdaQueryWrapper.eq(App::getAppId, appId);
        int deleteAppFlag = appDao.delete(appLambdaQueryWrapper);

        return deleteIpFlag >= 0 && deleteAppFlag > 0;
    }
}
