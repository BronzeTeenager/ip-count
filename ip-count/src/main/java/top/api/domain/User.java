package top.api.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class User {
    private Integer id;
    @TableField(exist = false) // 排除字段
    private String checkCode;
    private String userName;
    private String password;
    private Integer money;
    private Integer signInStatus; // 签到状态
    private String ip;
    private String ipInfo;
    private String loginTime;
    private Integer status;
}
