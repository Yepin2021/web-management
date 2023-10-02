package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept select(Integer id);

    void update(Dept dept);
}
