package com.qin.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.service.EduCourseService;
import com.qin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-20 15:00
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;
    /**
     * 得到前端页面讲师
     * @param current
     * @param limit
     * @return
     */
    @PostMapping("/getTeacherFrontList/{current}/{limit}")
    public Result getTeacherFrontList(@PathVariable Long current,@PathVariable Long limit){

        Page<EduTeacher> page = new Page<>(current,limit);

       Map<String,Object> map = teacherService.getTeacherFrontList(page);


        return Result.ok().data(map);
    }


    /**
     * 讲师详情
     * @param id
     * @return
     */
    @GetMapping("/getTeacherFrontInfo/{id}")
    public Result getTeacherFrontInfo(@PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = courseService.list(wrapper);


        return Result.ok().data("teacher",teacher).data("courseList",courseList);
    }


}
