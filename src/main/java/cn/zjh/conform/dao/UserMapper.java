package cn.zjh.conform.dao;

import cn.zjh.conform.model.Permission;
import cn.zjh.conform.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from sys_user where username=#{username}")
    public User findByUsername(String username);

    @Update("update sys_user set password=#{password} where username=#{username}")
    void updateUserPassword(User user);


    //多对多不好做外键
    @Select("select permission.* from sys_user user " +
            "inner join sys_user_role user_role on user.id=user_role.user_id" +
            "inner join sys_role_permission role_permission on user_role.role_id=role_permission.role_id" +
            "inner join sys_permission permission permission.id=role_permission.permission_id")
    public List<Permission> findPermissionByUsername(String username);
}
