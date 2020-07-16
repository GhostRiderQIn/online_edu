package com.qin.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.qin.servicebase.exception.MyException;
import com.qin.vod.service.VodService;
import com.qin.vod.utils.ConstantVodUtil;
import com.qin.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 16:54
 **/
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoToAli(MultipartFile file) {

        try {
            // 原始名称
            String fileName = file.getOriginalFilename();

            //上传之后显示的名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            InputStream inputStream = null;

            inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtil.KEY_ID, ConstantVodUtil.KEY_SECRET_ID, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            return response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void removeBatchAliVideo(List<String> videoList) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtil.KEY_ID, ConstantVodUtil.KEY_SECRET_ID);

            DeleteVideoRequest request = new DeleteVideoRequest();
            for (Object o : videoList) {

            }

            String videoIds = StringUtils.join(videoList.toArray(), ",");
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);

        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyException(20001,"视频删除失败");
        }
    }
}
