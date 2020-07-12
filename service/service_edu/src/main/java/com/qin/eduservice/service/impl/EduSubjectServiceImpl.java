package com.qin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.eduservice.entity.EduSubject;
import com.qin.eduservice.entity.excel.SubjectData;
import com.qin.eduservice.entity.subject.OneSubject;
import com.qin.eduservice.entity.subject.TwoSubject;
import com.qin.eduservice.listener.SubjectExcelListener;
import com.qin.eduservice.mapper.EduSubjectMapper;
import com.qin.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {

        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<EduSubject> eduOneSubjects = baseMapper.selectList(wrapper);


        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> eduTwoSubjects = baseMapper.selectList(wrapperTwo);

        List<OneSubject> returnList = new ArrayList<>();
        //一级分类
        for (EduSubject s : eduOneSubjects) {

            OneSubject subject = new OneSubject();

            BeanUtils.copyProperties(s,subject);


            List<TwoSubject> twoSubjects = new ArrayList<>();
            for (EduSubject t : eduTwoSubjects) {
                if (t.getParentId().equals(s.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(t,twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }

            subject.setChildren(twoSubjects);
            returnList.add(subject);
        }

        return returnList;
    }
}
