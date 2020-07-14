package com.qin.eduservice.controller;


import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduChapter;
import com.qin.eduservice.entity.chapter.ChapterVo;
import com.qin.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService service;


    /**
     * 根据课程id得到全部章节
     * @param courseId
     * @return
     */
    @GetMapping("/getChapterVideo/{courseId}")
    private Result getChapterVideo(@PathVariable String courseId){

        List<ChapterVo> chapterVideoByCourseId = service.getChapterVideoByCourseId(courseId);

        return Result.ok().data("list",chapterVideoByCourseId);
    }

    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        service.save(eduChapter);
        return Result.ok();
    }

    /**
     * 根据id查询章节
     * @param chapterId
     * @return
     */
    @GetMapping("/getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = service.getById(chapterId);
        return Result.ok().data("chapter",eduChapter);
    }

    /**
     * 根据id修改章节
     * @param eduChapter
     * @return
     */
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        service.updateById(eduChapter);
        return Result.ok();
    }

    /**
     * 删除章节，如果章节中有小节，不能删除
     * @param chapterId
     * @return
     */
    @DeleteMapping("/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean f = service.deleteChapter(chapterId);
        if (f){
            return Result.ok();
        }
        return Result.error();
    }
}

