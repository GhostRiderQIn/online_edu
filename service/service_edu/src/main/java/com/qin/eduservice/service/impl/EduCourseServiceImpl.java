package com.qin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.EduCourseDescription;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.entity.vo.CoursePublishVo;
import com.qin.eduservice.mapper.EduCourseMapper;
import com.qin.eduservice.service.EduChapterService;
import com.qin.eduservice.service.EduCourseDescriptionService;
import com.qin.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.eduservice.service.EduVideoService;
import com.qin.servicebase.exception.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduVideoService videoService;


    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        // 添加课程表
        int insert = baseMapper.insert(eduCourse); // 返回影响行数 >0 成功 <=0 失败

        if (insert<=0){
            throw new MyException(20001,"添加课程失败");
        }

        String id = eduCourse.getId();

        // 添加课程描述表
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(id);
        courseDescriptionService.save(courseDescription);

        return id;
    }

    /**
     * 查询课程全部信息
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {


        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription eduDes = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduDes.getDescription());

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     */
    @Override
    @Transactional
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int i = baseMapper.updateById(eduCourse);
        if (i<=0){
            throw new MyException(20001,"修改课程失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        courseDescriptionService.updateById(eduCourseDescription);



    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    @Transactional
    public void removeCourse(String courseId) {
        //1 删除小节
        videoService.removeVideoByCourseId(courseId);

        //2 删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 删除描述
        courseDescriptionService.removeById(courseId);

        //4 删除课程
        int res = baseMapper.deleteById(courseId);
        if (res == 0){
            throw new MyException(20001,"删除失败");
        }
    }
}
