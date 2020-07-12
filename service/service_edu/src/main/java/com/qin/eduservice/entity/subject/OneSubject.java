package com.qin.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: online_edu_parent
 * @description: 课程一级
 * @author: qinda
 * @create: 2020-07-11 22:39
 **/
@Data
public class OneSubject {

    private String id;

    private String title;

    // 多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
