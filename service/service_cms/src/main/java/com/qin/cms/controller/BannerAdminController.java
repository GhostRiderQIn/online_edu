package com.qin.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.cms.entity.CrmBanner;
import com.qin.cms.service.CrmBannerService;
import com.qin.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author qinda
 * @since 2020-07-16
 */
@RestController
@RequestMapping("/educms/banner")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    /**
     * 分页查询
     * @return
     */
    @GetMapping("/pageBanner/{current}/{limit}")
    public Result pageBanner(@PathVariable("current") Long current,
                             @PathVariable("limit") Long limit)
    {

        Page<CrmBanner> page = new Page<>(current,limit);
        bannerService.page(page, null);
        System.out.println(page.getTotal());

        return Result.ok().data("items",page.getRecords()).data("total",page.getTotal());

    }

    /**
     * 根据id查询banner
     * @param id
     * @return
     */
    @GetMapping("/getBanner/{id}")
    public Result getBanner(@PathVariable String id){
        CrmBanner banner = bannerService.getById(id);
        return Result.ok().data("item",banner);
    }


    /**
     * 添加banner
     * @param banner
     * @return
     */
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return Result.ok();
    }


    /**
     * 修改banner
     * @param banner
     * @return
     */
    @PutMapping("/updateBanner")
    public Result updateBanner(@RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return Result.ok();
    }

    /**
     * 删除banner
     * @param id
     * @return
     */
    @DeleteMapping("/deleteBanner/{id}")
    public Result deleteBanner(@PathVariable String id){
        bannerService.removeById(id);
        return Result.ok();
    }
}

