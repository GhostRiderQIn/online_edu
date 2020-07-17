package com.qin.eduservice.service;

import com.qin.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
