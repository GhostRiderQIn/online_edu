package com.qin.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 14:01
 **/
@Data
public class CourseQuery {
    private String title;

    private String status;

    private String begin;

    private String end;

}
