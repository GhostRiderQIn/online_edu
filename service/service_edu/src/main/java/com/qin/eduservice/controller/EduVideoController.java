package com.qin.eduservice.controller;


import com.qin.commonutils.Result;
import com.qin.eduservice.entity.EduVideo;
import com.qin.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 增加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);

        return Result.ok();
    }
    /**
     * 根据id获得小节
     * @param id
     * @return
     */
    @GetMapping("/getVideo/{id}")
    public Result addVideo(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);

        return Result.ok().data("video",video);
    }


    /**
     * 修改小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    /**
     * 删除小节
     * TODO 删除小节时要删除视频
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteVideo(@PathVariable String id){
        eduVideoService.removeById(id);
        return Result.ok();
    }


}

