package com.qin.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 11:03
 **/
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
