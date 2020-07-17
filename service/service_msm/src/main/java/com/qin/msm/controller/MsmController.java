package com.qin.msm.controller;

import com.qin.commonutils.Result;
import com.qin.msm.service.MsmService;
import com.qin.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-17 11:50
 **/
@RestController
@CrossOrigin
@RequestMapping("/edumsm/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 发送验证码
     * @param phone 手机号
     * @return
     */
    @GetMapping("/send/{phone}")
    public Result sendSms(@PathVariable String phone){
        //1、从redis中获取
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code))
            return Result.ok();

        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);

        boolean isSend = msmService.send(param,phone);
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }
        return Result.error().message("短信发送失败");
    }
}
