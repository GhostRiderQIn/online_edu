package com.qin.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-10 22:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{
    private Integer code;

    private String msg;
}
