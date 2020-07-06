package com.qin.eduservice.controller;


import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-05
 */

@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {


    @Autowired
    private EduTeacherService teacherService;



    /**
     * 查询所有讲师
     * @return 返回讲师 List
     */
    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher(){
        return teacherService.list(null);
    }



    /**
     * 逻辑删除讲师
     * @param id 讲师 id
     * @return 是否成功
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id){
        return teacherService.removeById(id);
    }

}

