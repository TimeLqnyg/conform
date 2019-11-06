package cn.zjh.shardingjdbc.dao;

import cn.zjh.shardingjdbc.model.Address;
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
public interface AddressMapper {
    /**
     * 保存
     */
    @Insert("INSERT INTO t_address(code,name,pid,type,lit)\n" +
            "        VALUES\n" +
            "        (\n" +
            "        #{code},#{name},#{pid},#{type},#{lit}\n" +
            "        )")
    void save(Address address);

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from t_address where id = #{id}")
    Address get(Long id);
}
