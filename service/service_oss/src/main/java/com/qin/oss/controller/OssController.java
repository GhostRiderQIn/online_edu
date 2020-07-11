package com.qin.oss.controller;

import com.qin.commonutils.Result;
import com.qin.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-10 14:41
 **/

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public Result uploadOssFile(MultipartFile file){

        String url = ossService.uploadFileAvatar(file);

        return Result.ok().data("url",url);
    }
}
