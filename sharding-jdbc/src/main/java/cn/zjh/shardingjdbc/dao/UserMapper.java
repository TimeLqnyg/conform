package cn.zjh.shardingjdbc.dao;

import cn.zjh.shardingjdbc.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.dao
 * @date:2019/11/6
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 保存
     */
    @Insert("INSERT INTO t_user(name,phone,email,city_id,sex,password)\n" +
            "        VALUES\n" +
            "        (\n" +
            "        #{name},#{phone},#{email},#{cityId},#{sex},#{password}\n" +
            "        )")
    void save(User user);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from t_user where id = #{id}")
    User get(Long id);
}
