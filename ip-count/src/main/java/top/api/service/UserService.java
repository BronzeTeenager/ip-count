package top.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.api.domain.IpInfo;
import top.api.domain.User;
import top.api.utils.Msg;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    /**
     * 登录
     * @param user
     * @param request
     * @return
     */
    Msg login(User user, HttpServletRequest request) throws Exception;

    /**
     * 注册
     * @param user
     * @return
     */
    Msg register(User user, HttpServletRequest request) throws Exception;

    /**
     * 重置密码
     * @param user
     * @return
     */
    Msg resetPassword(User user, HttpServletRequest request) throws Exception;

    /**
     * 修改用户余额 (重要,请不要随意调用)
     * @param id
     * @return
     */
    boolean updateMoney(Integer id, Integer money);

    /**
     * 修改sign_in_status 字段(写入签到状态)
     * @param id 用户id
     * @param status 随机签到余额
     * @return
     */
    boolean updateSignInStatus(Integer id, Integer status);

    /**
     * 返回用户信息
     * return 用户名,金额,会员等级,总项目数,总访问数,总IP数
     */
    Msg userInfo(Integer id, IpInfo ipInfo) throws Exception;

    /**
     * 将sign_in_status 全部初始化为 0
     * @return
     */
    boolean updateSignInStatus0();
}
