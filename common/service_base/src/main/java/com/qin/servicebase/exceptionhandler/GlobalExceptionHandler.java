package com.qin.servicebase.exceptionhandler;

import com.qin.commonutils.Result;
import com.qin.servicebase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: online_edu_parent
 * @description: 异常处理类
 * @author: qinda
 * @create: 2020-07-06 22:44
 **/

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回数据
    public Result error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message("全局异常");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
