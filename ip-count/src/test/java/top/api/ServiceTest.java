package top.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.api.service.IpService;
import top.api.utils.Msg;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IpService ipService;

    @Test
    void getMonthIpCountAndSum(){
        Msg msg = ipService.getMonthIpCountAndSum(6);

        System.out.println(msg);
    }
}
