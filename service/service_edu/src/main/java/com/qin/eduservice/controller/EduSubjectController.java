package com.qin.eduservice.controller;


import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduSubject;
import com.qin.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(description = "可能")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传的文件excel

    /**
     * 上传excel，读取并插入数据库
     * @param file 上传的excel
     * @return
     */
    @PostMapping("/addSubject")
    @ApiOperation(value = "上传课程分类excel，两列，第一列一级分类，第二列二级分类")
    public Result addSubject(MultipartFile file){

        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }

}

