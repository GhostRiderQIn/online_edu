package com.qin.vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 15:52
 **/
public class testttt {
    public static void main(String[] args) throws ClientException {
        String accessKeyId = "LTAI4GH9kF246mS7S4QWKVPb";
        String accessKeySecret = "PRnmLort59Epsyd8VSSfU2sPmA8FoA";
        String title = "uploadSDK";
        String fileName = "D:\\BaiduNetdiskDownload\\在线教育--谷粒学院\\项目资料\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
    public static void getPlayAuth() throws ClientException {
        //根据视频id获取凭证
        DefaultAcsClient client = InitObj.initVodClient("LTAI4GH9kF246mS7S4QWKVPb","PRnmLort59Epsyd8VSSfU2sPmA8FoA");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();



        request.setVideoId("21c2cab11f0443e5b08910aeb27eefdc");


        response = client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());

    }

    public static void getPlayUrl() throws ClientException {
        //1 根据视频id获得地址

        //创建初始化对象
        DefaultAcsClient client = InitObj.initVodClient("LTAI4GH9kF246mS7S4QWKVPb","PRnmLort59Epsyd8VSSfU2sPmA8FoA");
        //创建获取地址的req res
        GetPlayInfoRequest request = new GetPlayInfoRequest();

        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向req中设置视频id
        request.setVideoId("21c2cab11f0443e5b08910aeb27eefdc");
        //方法调用
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
