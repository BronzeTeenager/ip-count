package top.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.dao.AppDao;
import top.api.dao.IpDao;
import top.api.dao.UserDao;
import top.api.domain.App;
import top.api.domain.Ip;
import top.api.domain.msg.MsgIp;
import top.api.service.AppService;
import top.api.service.IpService;
import top.api.service.UserService;
import top.api.utils.Msg;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MysqlTest {

    @Autowired
    private AppDao appDao;

    @Autowired
    private IpDao ipDao;

    @Autowired
    private AppService appService;

    @Autowired
    private IpService ipService;

    @Test
    void selectAppCount(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

        Integer count = appDao.selectAppIpCount(1, date);
        System.out.println(count);
    }

    @Test
    void selectLike(@Autowired AppService appService){
        String str = "一";
        Integer id = 1;
        // 有问题,明天解决 ~~~
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getId,id);

        queryWrapper.and(LambdaQueryWrapper -> LambdaQueryWrapper.like(App::getAppName,str).or().like(App::getNotes,str));

        List<App> apps = appService.list(queryWrapper);

        System.out.println(apps);
    }

    @Test
    void selectUser(@Autowired UserService userService ){
        List<MsgIp> msgIps = ipDao.selectLikeIp(1, "%192%");
        System.out.println(msgIps);
    }

    @Test
    void deleteApp(){
        Msg msg = ipService.getMonthIpCountAndSum(6);

        System.out.println(msg);
    }

    @Test
    void selectTodayIpCount(){
        LambdaQueryWrapper<Ip> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ip::getId,6);
        queryWrapper.eq(Ip::getDate,"2022-11-18");


        Integer count = ipDao.selectCount(queryWrapper);

        System.out.println(count);
    }

    @Test
    void selectTodayIpSum(){
        QueryWrapper<Ip> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sum(request_count) as sum");
        queryWrapper.eq("id",6);
        queryWrapper.eq("date","2022-11-18");

        List<Object> objs = ipDao.selectObjs(queryWrapper);

        System.out.println(objs.get(0));
    }
}
