package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean page(String name, Short gender, LocalDate begin, LocalDate end,Integer pageNum, Integer pageSize);

    void delete(ArrayList<Integer> arrayList);

    void add(Emp emp);

    Emp select (Integer id);
    void revise(Emp emp);
    Emp login(Emp emp);
}
