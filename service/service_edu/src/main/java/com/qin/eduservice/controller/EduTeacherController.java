package com.qin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduTeacher;
import com.qin.eduservice.entity.vo.TeacherQuery;
import com.qin.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-05
 */

@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    private EduTeacherService teacherService;



    /**
     * 查询所有讲师
     * @return 返回讲师 List
     */
    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("/findAll")
    public Result findAllTeacher(){
        List<EduTeacher> teachers = teacherService.list(null);

        return Result.ok().data("items",teachers);
    }



    /**
     * 逻辑删除讲师
     * @param id 讲师 id
     * @return 是否成功
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }


    /**
     * 分页讲师查询
     * @param current 当前页
     * @param size 页大小
     * @return Result
     */
    @ApiOperation(value = "讲师分页查询")
    @GetMapping("/pageTeacher/{current}/{size}")
    public Result pageListTeacher(@PathVariable("current") Long current,@PathVariable("size") Long size){

        Page<EduTeacher> pageTeacher = new Page<>(current,size);

        teacherService.page(pageTeacher,null);

        return getResultByPage(pageTeacher);
    }


    /**
     * 讲师条件组合分页查询
     * @param current 当前页
     * @param size 页大小
     * @param teacherQuery 组合条件
     * @return 返回数据
     */
    @ApiOperation(value = "讲师条件组合分页查询")
    @PostMapping("/pageTeacherCondition/{current}/{size}")
    public Result pageTeacherCondition(@PathVariable("current") Long current, @PathVariable("size") Long size,
                                       @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher = new Page<>(current, size);

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        //时间排序
        wrapper.orderByDesc("gmt_create");
        teacherService.page(pageTeacher,wrapper);
        // 等待封装
        return getResultByPage(pageTeacher);
    }

    private Result getResultByPage(Page<EduTeacher> page){
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return Result.ok().data(map);
    }


    /**
     * 添加讲师
     * @param eduTeacher 讲师
     * @return 返回是否成功
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return Result.ok();
        }
        return Result.error();
    }


    /**
     * 根据id查询讲师
     * @param id 讲师id
     * @return
     */
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@PathVariable("id") String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }


    /**
     * 根据id修改讲师
     * @param eduTeacher 讲师
     * @return
     */
    @ApiOperation(value = "根据id修改讲师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return Result.ok();
        }
        return Result.error();
    }

}

