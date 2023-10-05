package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpServiceImpl;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    EmpServiceImpl empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
<<<<<<< HEAD
        Emp emp1 = empService.login(emp);
        Map<String,Object> map = new HashMap<>();
        map.put(emp.getUsername(),emp.getPassword());

        return emp1 == null ? Result.error("NOT_LOGIN") : Result.success(JwtUtils.generateJwt(map));
=======
        Emp e = empService.login(emp);
        Map<String,Object> map = new HashMap<>();
        if(e!=null){
            map.put("id",e.getId());
            map.put("name",e.getName());
            map.put("username",e.getUsername());
            return Result.success(JwtUtils.generateJwt(map));
        }
        return Result.error("NOT_LOGIN");
>>>>>>> 376ab2e (0.0)
    }
}
