package com.itheima.mapper;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) value (#{name},now(),now())")
    public void add(String name);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    public Dept select(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    public void update(Dept dept);
}
