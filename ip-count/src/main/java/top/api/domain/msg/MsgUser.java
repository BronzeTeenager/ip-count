package top.api.domain.msg;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

@Data
public class MsgUser {
    private String userName;
    private Integer money;
    private String token;
    private String msg;
}
