package top.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.api.domain.App;
import top.api.utils.Msg;

import javax.servlet.http.HttpServletRequest;

public interface AppService extends IService<App> {

    /**
     * 添加项目
     * @param app
     * @param request
     * @return
     */
    Msg add(App app, HttpServletRequest request) throws Exception;

    /**
     * 查询指定项目
     */
    Msg getOneApp(Integer userId,Integer appId) throws Exception;


    /**
     * 查询全部项目
     * @param
     * @return
     * @throws Exception
     */
    Msg getAllApp(Integer userId) throws Exception;

    /**
     * 把今日数据复制到昨日,并把今日全部改为 0
     * @return
     */
    boolean updateIPRequest();

    /**
     * 根据id, appID 删除
     * @param userId
     * @param appId
     * @return
     */
    boolean deleteApp(Integer userId,Integer appId);
}
