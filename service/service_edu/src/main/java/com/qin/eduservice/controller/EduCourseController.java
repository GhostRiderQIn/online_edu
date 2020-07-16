package com.qin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.entity.vo.CoursePublishVo;
import com.qin.eduservice.entity.vo.CourseQuery;
import com.qin.eduservice.entity.vo.TeacherQuery;
import com.qin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加课程
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }

    /**
     * 查询课程（条件带分页）
     * @return
     */
    @PostMapping("/{current}/{size}")
    public Result getCourse(@PathVariable("current") Long current, @PathVariable("size") Long size,
                            @RequestBody(required = false) CourseQuery courseQuery){

        Page<EduCourse> page = new Page<>(current,size);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        eduCourseService.page(page, wrapper);

        List<EduCourse> records = page.getRecords();
        long total = page.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("rows",records);
        map.put("total",total);

        return Result.ok().data(map);
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


    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);

        return Result.ok();
    }

    /**
     * 得到最终确认课程发布信息
     * @param id
     * @return
     */
    @GetMapping("/getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(id);
        return Result.ok().data("publishCourseInfo",coursePublishVo);
    }

    /**
     * 最终发布课程
     * @param id
     * @return
     */
    @PostMapping("/publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Result.ok();
    }
}

