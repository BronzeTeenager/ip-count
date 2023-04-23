package top.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.api.dao.AppDao;
import top.api.dao.UserDao;
import top.api.domain.App;
import top.api.domain.IpInfo;
import top.api.domain.User;
import top.api.domain.msg.MsgUser;
import top.api.service.UserService;
import top.api.utils.Code;
import top.api.utils.Msg;
import top.api.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class) // 开启事务
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppDao appDao;

    @Override
    public Msg login(User user, HttpServletRequest request) throws Exception {
        // 查询信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        queryWrapper.eq(User::getPassword, user.getPassword());
        User dbUser = userDao.selectOne(queryWrapper);

        if (dbUser == null) {
            return new Msg(Code.CODE_NO, "账号或密码错误!", "制作不易,请各位手下留情,跪谢!");
        }

        // 判断是否封号
        if (dbUser.getStatus() == 1){
            return new Msg(Code.CODE_NO,"账号异常!","制作不易,请各位手下留情,跪谢!");
        }

        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        // 更新用户最后登录时间
        if (ipInfo != null) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getId, dbUser.getId());
            updateWrapper.set(User::getLoginTime, time);
            updateWrapper.set(User::getIp, ipInfo.getIp());
            updateWrapper.set(User::getIpInfo, ipInfo.getIpInfo());
            userDao.update(null, updateWrapper);

            dbUser.setLoginTime(time);
            dbUser.setIp(ipInfo.getIp());
            dbUser.setIpInfo(ipInfo.getIpInfo());
        }

        // 生成token
        Map<String, Object> map = new HashMap<>();
        map.put("userName", dbUser.getUserName());
        map.put("id", dbUser.getId());

        MsgUser msgUser = new MsgUser();
        msgUser.setUserName(dbUser.getUserName());
        msgUser.setMoney(dbUser.getMoney());

        msgUser.setToken(TokenUtils.getToken(map));

        msgUser.setMsg("制作不易,请各位手下留情,跪谢!");

        return new Msg(Code.CODE_OK, "登录成功!", msgUser);
    }

    @Override
    public Msg register(User user, HttpServletRequest request) throws Exception {

        // 查询账号是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());

        User one = userDao.selectOne(queryWrapper);

        if (one != null) {
            return new Msg(Code.CODE_NO, "账号已存在!", "制作不易,请各位手下留情,跪谢!");
        }

        // 获取ipInfo信息
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        user.setLoginTime(time);
        user.setIp(ipInfo.getIp());
        user.setIpInfo(ipInfo.getIpInfo());

        // 注册
        int flag = userDao.insert(user);

        MsgUser msgUser = new MsgUser();
        msgUser.setUserName(user.getUserName());
        msgUser.setMoney(0);
        msgUser.setMsg("制作不易,请各位手下留情,跪谢!");

        return new Msg(flag > 0 ? Code.CODE_OK : Code.CODE_NO, "注册成功,请登录!", msgUser);
    }

    @Override
    public Msg resetPassword(User user, HttpServletRequest request) throws Exception {

        // 查询账号是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());

        User dbUser = userDao.selectOne(queryWrapper);

        if (dbUser == null) {
            return new Msg(Code.CODE_NO, "账号不存在!", null);
        }

        // 获取ipInfo信息
        IpInfo ipInfo = (IpInfo) request.getAttribute("ipInfo");

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        dbUser.setLoginTime(time);
        dbUser.setIp(ipInfo.getIp());
        dbUser.setIpInfo(ipInfo.getIpInfo());
        dbUser.setPassword(user.getPassword());

        // 重置密码
        int flag = userDao.updateById(dbUser);

        return new Msg(flag > 0 ? Code.CODE_OK : Code.CODE_NO, flag > 0 ? "重置密码成功!" : "重置密码失败!", null);
    }

    @Override
    public boolean updateMoney(Integer id,Integer money) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getMoney,money);
        updateWrapper.eq(User::getId,id);

        int flag = userDao.update(null, updateWrapper);

        return flag > 0;
    }

    @Override
    public boolean updateSignInStatus(Integer id, Integer status) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getSignInStatus,status);
        updateWrapper.eq(User::getId,id);

        int flag = userDao.update(null, updateWrapper);

        return flag > 0;
    }

    @Override
    public Msg userInfo(Integer id,IpInfo ipInfo) {
        User dbUser = userDao.selectById(id);

        if (dbUser == null) {
            return new Msg(Code.CODE_NO, "账号异常", null);
        }

        // 更新用户登录最新时间
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, dbUser.getId());
        updateWrapper.set(User::getLoginTime, time);
        updateWrapper.set(User::getIp, ipInfo.getIp());
        updateWrapper.set(User::getIpInfo, ipInfo.getIpInfo());
        userDao.update(null, updateWrapper);



        Map<String, String> map = new HashMap<>();
        map.put("userName", dbUser.getUserName());
        map.put("money", dbUser.getMoney() + "");

        if (dbUser.getSignInStatus() == 0 || dbUser.getSignInStatus() == null){
            map.put("signInStatus","no");
        }else{
            map.put("signInStatus",dbUser.getSignInStatus()+"");
        }

        // 判断该用户的等级
        if (dbUser.getStatus() == 0) {
            map.put("grade", "普通用户");
        } else if (dbUser.getStatus() == 6) {
            map.put("grade", "普通会员");
        } else if (dbUser.getStatus() == 8) {
            map.put("grade", "终身会员");
        }

        // 查询(今日)总项目数,总访问数,总ip数

        //查询总app数
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId,id);
        Integer appCount = appDao.selectCount(queryWrapper);
        map.put("appCount",appCount+"");

        //查询总访问数
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        Integer appRequestCount = appDao.selectAppRequestCount(id,date);

        if (appRequestCount == null){
            map.put("appRequestCount","0");
        }else {
            map.put("appRequestCount",appRequestCount+"");
        }

        // 查询总ip数
        Integer ipCount = appDao.selectAppIpCount(id, date);
        map.put("ipCount",ipCount+"");

        return new Msg(Code.CODE_OK, "ok", map);
    }

    @Override
    public boolean updateSignInStatus0() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql("sign_in_status = 0");

        int flag = userDao.update(null, updateWrapper);

        return flag > 0;
    }
}
