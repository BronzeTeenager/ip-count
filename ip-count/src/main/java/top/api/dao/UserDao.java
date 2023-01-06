package top.api.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.api.domain.User;


@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
}
