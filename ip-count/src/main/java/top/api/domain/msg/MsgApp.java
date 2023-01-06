package top.api.domain.msg;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class MsgApp {
    private String token;
    // 名称
    private String appName;
    // 备注
    private String notes;
    // 今日ip数量
    private Integer todayIpNumber;
    // 昨日ip数量
    private Integer yesterdayIpNumber;
    // 今日请求数量
    private Integer todayRequestNumber;
    // 昨日请求数量
    private Integer yesterdayRequestNumber;
    // 累计ip数量
    private Integer ipCount;
    // 累计请求数量
    private Integer requestCount;

    // 在线数量存在redis
    private Integer online;
}
