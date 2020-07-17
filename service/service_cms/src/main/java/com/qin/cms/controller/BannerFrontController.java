package com.qin.cms.controller;

import com.qin.cms.entity.CrmBanner;
import com.qin.cms.service.CrmBannerService;
import com.qin.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-16 12:53
 **/
@CrossOrigin
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;


    @GetMapping("/getAllBanner")
    public Result getAllBanner(){

        List<CrmBanner> list = bannerService.selectAllBanner();
        return Result.ok().data("list",list);
    }

}
