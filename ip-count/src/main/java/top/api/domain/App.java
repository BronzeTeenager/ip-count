package top.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * app实体类
 */
@Data
public class App {
    // 外键
    //@TableId(value = "id", type = IdType.INPUT) //设置id不自增,自己输入
    private Integer id;
    // 唯一外界标识
    @TableId(value = "app_id", type = IdType.AUTO)
    private Integer appId;

    @TableField(exist = false) // 排除字段
    private String token;
    // 名称
    private String appName;
    // 备注
    private String notes;
/*    // 今日ip数量
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
    private Integer requestCount;*/

}
