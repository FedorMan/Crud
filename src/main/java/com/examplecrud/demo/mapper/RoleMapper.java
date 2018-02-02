package com.examplecrud.demo.mapper;

import com.examplecrud.demo.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select * from roles")
    public List<Role> findAll();

    @Insert("insert into roles(name_role) values(#{name_role})")
    public void create(@Param("name_role") String name_role);

    @Delete("delete from roles where id = #{id}")
    public void delete(@Param("id") Long id);

    @Select("select * from roles where id = #{id}")
    public Role read(@Param("id") Long id);

    @Update("update role set name_role = #{name_role}, where id = #{id}")
    public void update(@Param("username") String name_role, @Param("id") Long id);

}
