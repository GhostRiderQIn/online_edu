package com.qin.ucenter.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.qin.commonutils.JwtUtils;
import com.qin.commonutils.Result;
import com.qin.ucenter.entity.Member;
import com.qin.ucenter.entity.vo.RegisterVo;
import com.qin.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author qinda
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 登录
     * @param member
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Member member){
        String token = memberService.login(member);
        return Result.ok().data("token",token);
    }


    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterVo registerVo){

        memberService.register(registerVo);
        return Result.ok();

    }

    /**
     * 根据token得到用户数据
     */
    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(id);

        return Result.ok().data("userInfo",member);
    }
}

