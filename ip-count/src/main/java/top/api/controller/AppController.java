package top.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.api.dao.IpDao;
import top.api.domain.App;
import top.api.domain.User;
import top.api.domain.msg.MsgApp;
import top.api.service.AppService;
import top.api.utils.AESUtil;
import top.api.utils.Code;
import top.api.utils.Msg;
import top.api.utils.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private IpDao ipDao;

    /**
     * 添加项目
     * @param app
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Msg addApp(@RequestBody App app, HttpServletRequest request) throws Exception {
        if (app.getAppName() == null || "".equals(app.getAppName())){
            return new Msg(Code.CODE_NO,"请输入项目名称!",null);
        }
        return appService.add(app, request);
    }

    /**
     * 根据token查询指定项目
     * @param token
     * @return
     */
    @GetMapping("/getOneApp")
    public Msg getOneApp(@RequestParam String token, HttpServletRequest request) throws Exception {

        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        Integer userId = (Integer) request.getAttribute("id");

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        return appService.getOneApp(userId, Integer.valueOf(appId));
    }


    /**
     * 查询全部项目
     * @param request
     * @return
     */
    @GetMapping("/getAllApp")
    public Msg getAllApp(HttpServletRequest request) throws Exception {

        Integer userId = (Integer) request.getAttribute("id");

        return appService.getAllApp(userId);
    }

    /**
     * app模糊查询 名称+备注
     * @param str
     * @return
     */
    @GetMapping("/selectLikeAppName")
    public Msg selectLikeAppName(@RequestParam String str, HttpServletRequest request) throws Exception {

        Integer userId = (Integer) request.getAttribute("id");

        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId,userId);

        queryWrapper.and(LambdaQueryWrapper -> LambdaQueryWrapper.like(App::getAppName,str).or().like(App::getNotes,str));


        List<App> apps = appService.list(queryWrapper);

        // 进行加密
        List<MsgApp> msgApps = new ArrayList<>();
        for (App app : apps) {
            MsgApp msgApp = new MsgApp();

            msgApp.setToken(AESUtil.encrypt(app.getAppId()+"",AESUtil.AESKey));
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
            msgApp.setYesterdayRequestNumber(yesterdayRequestSum);
            msgApp.setIpCount(ipCount);
            msgApp.setRequestCount(requestSum);
            msgApps.add(msgApp);
        }

        return new Msg(apps!=null?Code.CODE_OK:Code.CODE_NO,apps!=null?"ok":"no",msgApps);

    }

    /**
     * 删除项目
     * @param token
     * @param request
     * @return
     */
    @GetMapping("/deleteApp")
    public Msg deleteApp(@RequestParam String token, HttpServletRequest request){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        Integer userId = (Integer) request.getAttribute("id");

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        boolean flag = appService.deleteApp(userId, Integer.valueOf(appId));

        return new Msg(flag?Code.CODE_OK:Code.CODE_NO,flag?"删除成功":"删除失败",null);
    }
}
