package com.qin.eduservice.controller;

import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.service.EduTeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-11 00:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private EduTeacherService service;
    @Test
    public void test(){
        EduTeacher teacher = new EduTeacher();
        teacher.setId("1");
        service.save(teacher);
    }
}