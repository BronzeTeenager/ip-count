package top.api.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.api.domain.Ip;
import top.api.domain.ProvinceCount;
import top.api.domain.msg.MsgIp;

import java.util.List;
@Repository
@Mapper
public interface IpDao extends BaseMapper<Ip> {


    /**
     * 查询今日指定省份的总ip访问量
     * @param id
     * @param date
     * @param province
     * @return
     */
    @Select("select province,sum(request_count) as count from tb_ip where id = #{id} and date = #{date} and province = #{province}")
    ProvinceCount getTodayAllProvinceCount(@Param("id") Integer id, @Param("date") String date, @Param("province") String province);

    /**
     * 查询指定日期 所有ip信息
     * @param id
     * @return
     */
    @Select("SELECT ip,ip_info,request_count,date from tb_ip where id = #{id} and date = #{date}")
    List<MsgIp> selectAllIp(@Param("id") Integer id, @Param("date") String date);

    /**
     * 查询项目中所有省份
     * @param id
     * @return
     */
    @Select("SELECT distinct province from tb_ip where id = #{id}")
    List<ProvinceCount> selectAllProvince(@Param("id") Integer id);

    /**
     * 查询指定省份的所有总请求访问量
     * @param id
     * @param province
     * @return
     */
    @Select("select province,sum(request_count) as RequestCount from tb_ip where id = #{id} and province = #{province}")
    ProvinceCount selectAllProvinceRequestCount(@Param("id") Integer id,@Param("province") String province);

    /**
     * 查询指定省份的所有总ip数量
     * @param id
     * @param province
     * @return
     */
    @Select("select province,count(ip) as IpCount from tb_ip where id = #{id} and province = #{province}")
    ProvinceCount selectAllProvinceIpCount(@Param("id") Integer id,@Param("province") String province);

    /**
     * 模糊查询ip
     * @param id
     * @param str
     * @return
     */
    @Select("select * from tb_ip where ip like #{str} and id = #{id};")
    List<MsgIp> selectLikeIp(@Param("id") Integer id,@Param("str") String str);

    /**
     * 根据appId,date 查询指定日期的ip数量
     * @param appId
     * @param date
     * @return
     */
    @Select("SELECT count(*) as count from tb_ip where id = #{id} and date = #{date};")
    Integer selectIpCountAndDate(@Param("id")Integer appId,@Param("date") String date);

    /**
     * 根据appID,date 查询指定日期的请求数量 总和
     * @param appId
     * @param date
     * @return
     */
    @Select("SELECT sum(request_count) as sum from tb_ip where id = #{id} and date = #{date};")
    Integer selectRequestSumAndDate(@Param("id")Integer appId,@Param("date") String date);

    /**
     * 根据appId 查询项目总ip数量
     * @param appId
     * @return
     */
    @Select("SELECT count(*) as count from tb_ip where id = #{id};")
    Integer selectIpCount(@Param("id")Integer appId);

    /**
     * 根据appID 查询项目总 请求数量 总和
     * @param appId
     * @return
     */
    @Select("SELECT sum(request_count) as sum from tb_ip where id = #{id};")
    Integer selectRequestSum(@Param("id")Integer appId);

//    /**
//     * 将项目 IP 请求 字段 +1
//     * @param id
//     * @return
//     */
//    @Update("update tb_app set today_ip_number = (today_ip_number + 1), today_request_number = (today_request_number + 1),ip_count = (ip_count + 1), request_count = (request_count + 1) where app_id = #{id}")
//    Integer updateIpRequestAddOne(@Param("id") Integer id);
//
//    /**
//     * 将项目 请求字段 +1
//     * @param id
//     * @return
//     */
//    @Update("update tb_app set today_request_number = (today_request_number + 1), request_count = (request_count + 1) where app_id = #{id}")
//    Integer updateRequestAddOne(@Param("id") Integer id);
}
