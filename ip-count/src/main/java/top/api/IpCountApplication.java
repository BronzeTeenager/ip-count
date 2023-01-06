package top.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@SpringBootApplication
public class IpCountApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpCountApplication.class, args);
        log.info("ip-count启动成功!");
    }

}


