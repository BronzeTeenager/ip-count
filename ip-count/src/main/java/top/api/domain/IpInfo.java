package top.api.domain;

import lombok.Data;


@Data
public class IpInfo {
    /*** 国家 */
    private String country;
    /*** 地区 */
    private String region;
    /*** 省 */
    private String province;
    /*** 市 */
    private String city;
    /*** 运营商 */
    private String isp;
    /*** ip*/
    private String ip;

    public String getIpInfo(){
        return country+province+city+isp;
    }
}