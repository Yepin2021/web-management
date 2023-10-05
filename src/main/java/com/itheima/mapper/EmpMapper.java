package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    public List<Emp> page(String name, Short gender, LocalDate begin, LocalDate end);
    public void delete(ArrayList<Integer> arrayList);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUE (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void add(Emp emp);

    //@Update("update emp set username = #{username},name=#{name},gender=#{gender},image=#{image},dept_id=#{deptId},entrydate=#{entrydate},job=#{job},update_time=#{updateTime} where id = #{id}")
    public void revise(Emp emp);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where id = #{id}")
    public Emp select(Integer id);

    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp where  username= #{username} and password =#{password}")
    public Emp login(Emp emp);

    @Delete("delete from emp where dept_id=#{dept}")
    public void deleteDept(Integer dept);
}
