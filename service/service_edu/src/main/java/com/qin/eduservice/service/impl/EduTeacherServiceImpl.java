package com.qin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.mapper.EduTeacherMapper;
import com.qin.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-05
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Cacheable(key = "'selectHotTeacherList'",value = "HotTeacher")
    @Override
    public List<EduTeacher> getTopHotTeacher() {
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");

        return baseMapper.selectList(teacherWrapper);
    }
}
