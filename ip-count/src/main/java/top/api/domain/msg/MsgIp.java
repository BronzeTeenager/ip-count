package top.api.domain.msg;

import lombok.Data;

@Data
public class MsgIp {
    private String ip;
    private String IpInfo;
    private Integer requestCount;
    private String date;
}
