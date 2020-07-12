package com.qin.eduservice.service.impl;

import com.qin.eduservice.entity.EduCourse;
import com.qin.eduservice.entity.EduCourseDescription;
import com.qin.eduservice.entity.vo.CourseInfoVo;
import com.qin.eduservice.mapper.EduCourseMapper;
import com.qin.eduservice.service.EduCourseDescriptionService;
import com.qin.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.servicebase.exception.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
