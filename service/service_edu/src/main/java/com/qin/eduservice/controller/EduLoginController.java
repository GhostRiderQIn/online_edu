package com.qin.eduservice.controller;

import com.qin.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-08 17:31
 **/
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
@Api(description = "后台用户登录")
public class EduLoginController {


    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login(){

        return Result.ok().data("token","admin");
    }


    /**
     * 获取登录用户的信息
     * @return
     */
    @GetMapping("/info")
    public Result info(){

        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","http://b-ssl.duitang.com/uploads/item/201603/09/20160309181604_KmtFL.jpeg");
    }
}
