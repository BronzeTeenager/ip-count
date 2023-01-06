package top.api.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.api.domain.App;

@Repository
@Mapper
public interface AppDao extends BaseMapper<App> {

    /**
     * 查询用户所有app的总访问数量
     * @param id
     * @return
     */
    @Select("SELECT SUM(tb_ip.request_count) '总和' from tb_ip,tb_app WHERE tb_app.app_id = tb_ip.id and tb_ip.date= #{date} and tb_app.id= #{id}")
    Integer selectAppRequestCount(@Param("id") Integer id,@Param("date")String date);

    /**
     * 查询用户所有app的总IP数量
     * @param id
     * @param date
     * @return
     */
    @Select("SELECT count(tb_ip.ip) '总和' from tb_ip,tb_app WHERE tb_app.app_id = tb_ip.id and tb_ip.date= #{date} and tb_app.id= #{id}")
    Integer selectAppIpCount(@Param("id") Integer id,@Param("date")String date);

    /**
     * 将今日次数复制到昨日上
     * @return
     */
    @Update("UPDATE tb_app set yesterday_request_number = today_request_number, yesterday_ip_number = today_ip_number;")
    Integer updateIPRequest();

    /**
     * 将今日字段全部初始化为0
     * @return
     */
    @Update("UPDATE tb_app set today_request_number = 0, today_ip_number = 0;")
    Integer updateIPRequestSet0();
}
