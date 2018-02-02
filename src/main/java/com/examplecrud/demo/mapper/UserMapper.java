package com.examplecrud.demo.mapper;

import com.examplecrud.demo.entity.Role;
import com.examplecrud.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into users(username,password) values(#{username},#{password})")
    public void create(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user_roles(user_id,role_id) values(#{user_id},#{role_id})")
    public void addRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

    @Delete("delete from users where id = #{id}")
    public void delete(@Param("id") Long id);

    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "findAllRoles"))
    })
    @Select("select * from users where username = #{username}")
    public User read(@Param("username") String username);

    @Results(id = "userRoles", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nameRole", column = "name_role")
    })
    @Select("select * from roles where id = (select role_id from user_roles where user_id = #{id})")
    public List<Role> findAllRoles(@Param("id") Long id);

    @Update("update users set username = #{username} password = #{password}, where id = #{id}")
    public void update(@Param("username") String username, @Param("password") String password, @Param("id") Long id);

}
