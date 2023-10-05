package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理Controller
 */

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询所以部门数据");
        return Result.success(deptService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.delete(id);
        log.info("删除id为"+id +"的部门");
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("添加部门为{}",dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("搜索部门id为{}",id);
        return Result.success(deptService.select(id));
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("更新员工");
        return Result.success();
    }
}
