package top.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Ip {
    @TableId(value = "id", type = IdType.INPUT) //设置id不自增,自己输入
    private Integer id;
    private String province;
    private String ip;
    private String IpInfo;
    private Integer requestCount;
    private String date;
}
