package com.qin.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.eduservice.entity.EduSubject;
import com.qin.eduservice.entity.excel.SubjectData;
import com.qin.eduservice.service.EduSubjectService;
import com.qin.servicebase.exception.MyException;

import java.util.Date;

/**
 * @program: online_edu_parent
 * @description: Excel监听器
 * @author: qinda
 * @create: 2020-07-10 21:57
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService subjectService;

    public SubjectExcelListener() {}

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new MyException(20001,"文件数据为空");
        }

        // 一行一行读取，第一个值为一级分类，第二个为二级分类
        // 一级是否存在
        EduSubject eduOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (eduOneSubject == null){
            eduOneSubject = new EduSubject();
            eduOneSubject.setParentId("0");
            eduOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(eduOneSubject);
        }

        // 二级是否存在
        String pid = eduOneSubject.getId();
        EduSubject eduTwoSubject= this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if (eduTwoSubject == null){
            eduTwoSubject = new EduSubject();
            eduTwoSubject.setParentId(pid);
            eduTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(eduTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    public EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = subjectService.getOne(wrapper);

        return one;

    }

    //判断二级分类不能重复添加
    public EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = subjectService.getOne(wrapper);

        return two;

    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
