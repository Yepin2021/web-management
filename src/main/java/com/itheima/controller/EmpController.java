package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询员工");
        return Result.success(empService.page(name, gender, begin, end, page, pageSize));
    }

    @DeleteMapping("/{ids}")
    public Result deleteMapping(@PathVariable ArrayList<Integer> ids) {
        empService.delete(ids);
        log.info("批量删除员工");
        String S = "1111";
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工");
        empService.add(emp);
        return Result.success();
    }

    @PutMapping
    public Result revise(@RequestBody Emp emp) {
        log.info("修改员工");
        empService.revise(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("根据id查询员工");
        return Result.success(empService.select(id));
    }


}
