package com.qin.eduservice.controller;


import com.qin.commonutils.Result;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }


    /**
     * 根据课程id得到课程信息
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }


    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);

        return Result.ok();
    }
}

