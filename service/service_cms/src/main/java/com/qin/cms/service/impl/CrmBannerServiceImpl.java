package com.qin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.cms.entity.CrmBanner;
import com.qin.cms.mapper.CrmBannerMapper;
import com.qin.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author qinda
 * @since 2020-07-16
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(key = "'selectIndexList'", value = "banner")
    public List<CrmBanner> selectAllBanner() {

        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        wrapper.last("limit 2");

        return baseMapper.selectList(wrapper);
    }
}
