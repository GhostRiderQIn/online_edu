package com.qin.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.chapter.ChapterVo;
import com.qin.eduservice.entity.frontvo.CourseFrontQueryVo;
import com.qin.eduservice.entity.frontvo.CourseWebVo;
import com.qin.eduservice.service.EduChapterService;
import com.qin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-20 16:58
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;
    /**
     * 获得课程列表
     */
    @PostMapping("/getCourseInfo/{current}/{limit}")
    public Result getCourseInfo(@PathVariable Long current,@PathVariable Long limit,
                                @RequestBody CourseFrontQueryVo courseQueryVo){

        Page<EduCourse> page = new Page<>(current,limit);
        Map<String,Object> map = courseService.getCourseFrontInfo(page,courseQueryVo);

        return Result.ok().data(map);

    }

    /**
     * 根据课程id获得课程详细信息
     * @param id
     * @return
     */
    @GetMapping("/getCourseFrontDetailInfo/{id}")
    public Result getCourseFrontDetailInfo(@PathVariable String id){
        //查询课程信息
        CourseWebVo courseVo = courseService.getBaseCourseInfo(id);


        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(id);

        System.out.println(courseVo);
        return Result.ok().data("courseVo",courseVo).data("chapterVideoList",chapterVideoList);
    }
}
