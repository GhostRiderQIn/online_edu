package com.qin.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-06 21:38
 **/
@Data
public class TeacherQuery{

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间" , example = "2020-07-06 10:27:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间" , example = "2020-07-06 10:27:10")
    private String end;
}
