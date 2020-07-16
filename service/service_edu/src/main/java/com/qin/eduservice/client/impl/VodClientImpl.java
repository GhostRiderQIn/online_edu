package com.qin.eduservice.client.impl;

import com.qin.commonutils.Result;
import com.qin.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-15 17:05
 **/
@Component
public class VodClientImpl implements VodClient {
    @Override
    public Result removeAliVideo(String id) {
        return Result.error().message("删除视频失败了");
    }

    @Override
    public Result deleteBatch(List<String> videoList) {
        return Result.error().message("批量删除视频失败");
    }
}
