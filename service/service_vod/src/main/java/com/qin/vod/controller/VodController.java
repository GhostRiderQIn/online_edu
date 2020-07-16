package com.qin.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.qin.commonutils.Result;
import com.qin.servicebase.exception.MyException;
import com.qin.vod.service.VodService;
import com.qin.vod.utils.ConstantVodUtil;
import com.qin.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 16:53
 **/
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频
     * @param file
     * @return
     */
    @PostMapping("/uploadAliVideo")
    public Result uploadAliVideo(MultipartFile file){
        String videoId = vodService.uploadVideoToAli(file);
        return Result.ok().data("videoId",videoId);
    }

    /**
     * 删除阿里云视频
     * @param id
     * @return
     */
    @DeleteMapping("/removeAliVideo/{id}")
    public Result removeAliVideo(@PathVariable String id){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtil.KEY_ID, ConstantVodUtil.KEY_SECRET_ID);

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);


            client.getAcsResponse(request);

        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyException(20001,"视频删除失败");
        }
        return Result.ok();
    }


    @DeleteMapping("/delete-batch")
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList){

        vodService.removeBatchAliVideo(videoList);
        return Result.ok();
    }
}
