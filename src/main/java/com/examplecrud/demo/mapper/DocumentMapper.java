package com.examplecrud.demo.mapper;

import com.examplecrud.demo.entity.Document;
import com.examplecrud.demo.typeHandler.UUIDTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DocumentMapper {
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id", id = true, typeHandler = UUIDTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "description", column = "description")
    })
    @Select("select * from documents")
    public List<Document> findAll();

    @Insert("insert into documents(id,name,code,description) values(#{id},#{name},#{code},#{description})")
    public void create(@Param("id") UUID id, @Param("name") String name, @Param("code") String code, @Param("description") String description);

    @Delete("delete from documents where id = #{id}")
    public void delete(@Param("id") UUID id);

    @Results(id = "user", value = {
            @Result(property = "id", column = "id", id = true, typeHandler = UUIDTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "description", column = "description")
    })
    @Select("select * from documents where id = #{id}")
    public Document read(@Param("id") UUID id);

    @Update("update documents set name = #{name}, code = #{code}, description = #{description} where id = #{id}")
    public void update(@Param("id") UUID id, @Param("name") String name, @Param("code") String code, @Param("description") String description);

    @Results(id = "filterUserResult", value = {
            @Result(property = "id", column = "id", id = true, typeHandler = UUIDTypeHandler.class),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "description", column = "description")
    })
    @Select("select * from documents where name like #{filterText}")
    public List<Document> findByNameStart(@Param("filterText") String filterText);
}
