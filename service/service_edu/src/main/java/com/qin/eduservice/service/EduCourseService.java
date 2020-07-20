package com.qin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.eduservice.entity.frontvo.CourseFrontQueryVo;
import com.qin.eduservice.entity.frontvo.CourseWebVo;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String courseId);

    List<EduCourse> getTopHotCourse();

    Map<String, Object> getCourseFrontInfo(Page<EduCourse> page, CourseFrontQueryVo courseQueryVo);

    CourseWebVo getBaseCourseInfo(String id);
}
