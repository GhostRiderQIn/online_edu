package com.qin.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-05
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getTopHotTeacher();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> page);
}
