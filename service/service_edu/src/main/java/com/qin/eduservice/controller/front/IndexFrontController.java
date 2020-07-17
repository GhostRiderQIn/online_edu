package com.qin.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.service.EduCourseService;
import com.qin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-16 14:59
 **/

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;
    // 查询前8课程，前4讲师
    @GetMapping("/index")
    public Result index(){

        List<EduCourse> courses = courseService.getTopHotCourse();
        List<EduTeacher> teachers = teacherService.getTopHotTeacher();
        return Result.ok().data("eduList",courses).data("teacherList",teachers);
    }
}
