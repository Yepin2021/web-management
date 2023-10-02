package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;


    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Emp> p = (Page<Emp>)empMapper.page(name, gender, begin, end);
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(ArrayList<Integer> arrayList) {
        empMapper.delete(arrayList);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp select(Integer id) {
        return empMapper.select(id);
    }

    @Override
    public void revise(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.revise(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }
}
