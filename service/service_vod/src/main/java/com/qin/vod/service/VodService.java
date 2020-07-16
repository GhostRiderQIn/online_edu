package com.qin.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoToAli(MultipartFile file);

    void removeBatchAliVideo(List<String> videoList);
}
