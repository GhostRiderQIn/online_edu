package com.qin.eduservice.client;

import com.qin.commonutils.Result;
import com.qin.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAliVideo/{id}")
    public Result removeAliVideo(@PathVariable("id") String id);


    @DeleteMapping("/eduvod/video/delete-batch")
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList);
}
