package top.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.api.domain.IpInfo;
import top.api.service.IpService;
import top.api.utils.AESUtil;
import top.api.utils.Code;
import top.api.utils.Msg;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ip")
public class IpController {

    @Autowired
    private IpService ipService;

    /**
     * app 计数
     * @param request
     * @return
     */
    @RequestMapping("/ipCount")
    public Msg ipCount(@RequestParam String token, HttpServletRequest request){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);


        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");


        return ipService.ipCount(appId,ipInfo);
    }



    /**
     * 查询今日省份所有ip访问量(查询日期)
     * @param token
     * @return
     */
    @GetMapping("/getTodayAllIp")
    public Msg getTodayAllIp(@RequestParam String token){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        return ipService.getTodayAllIp(Integer.valueOf(appId));
    }


    /**
     * 查询指定省份的所有总ip访问量 (不查询日期)
     * @param token
     * @return
     */
    @GetMapping("/getAllProvinceCount")
    public Msg getAllProvinceCount(@RequestParam String token){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        return ipService.getAllProvinceCount(Integer.valueOf(appId));
    }


    /**
     * ip 模糊查询
     * @param token appId
     * @param queryStr 查询内容
     * @return
     */
    @GetMapping("/selectLikeIp")
    public Msg selectLikeIp(@RequestParam String token,@RequestParam String queryStr){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        Msg msg = ipService.selectLikeIp(Integer.valueOf(appId), "%"+queryStr+"%");

        return msg;
    }

    /**
     * 根据指定日期查询 ip 信息
     * @param token
     * @param date
     * @return
     */
    @GetMapping("/getIpAndDate")
    public Msg getIpAndDate(@RequestParam String token,@RequestParam String date){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        return ipService.getIpAndDate(Integer.valueOf(appId),date);
    }

    /**
     * 查询本月内 每日IP量和访问量
     * @param token
     * @return
     */
    @GetMapping("/getMonthIpCountAndSum")
    public Msg getMonthIpCountAndSum(@RequestParam String token){
        if (token.length() < 15){
            return new Msg(Code.CODE_NO,"appID is err",null);
        }

        // 将空格转为+ (浏览器编码导致的)
        token = token.replace(' ', '+');

        String appId = AESUtil.decrypt(token, AESUtil.AESKey);

        return ipService.getMonthIpCountAndSum(Integer.valueOf(appId));
    }

}
