package com.qin.eduservice.service;

import com.qin.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 读取上传的excel，插入课程分类信息
     * @param file
     * @param subjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllSubject();
}
