package com.itheima.service.impl;

<<<<<<< HEAD
=======
import com.itheima.anno.Log;
>>>>>>> 376ab2e (0.0)
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)
<<<<<<< HEAD
=======
    @Log
>>>>>>> 376ab2e (0.0)
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
        empMapper.deleteDept(id);
    }

    @Override
<<<<<<< HEAD
=======
    @Log
>>>>>>> 376ab2e (0.0)
    public void add(Dept dept) {
        deptMapper.add(dept.getName());
    }

    @Override
    public Dept select(Integer id) {
        return deptMapper.select(id);
    }

    @Override
<<<<<<< HEAD
=======
    @Log
>>>>>>> 376ab2e (0.0)
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
