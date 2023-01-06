package top.api.domain;

import lombok.Data;

@Data
public class ProvinceCount {
    private String province;
    // 请求数量
    private Integer RequestCount;
    // ip数量
    private Integer IpCount;
}
