package com.qin.eduservice.service;

import com.qin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.entity.vo.CoursePublishVo;

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
}
