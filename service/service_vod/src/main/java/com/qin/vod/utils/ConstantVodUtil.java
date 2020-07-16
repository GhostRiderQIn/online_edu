package com.qin.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-14 17:02
 **/
@Component
public class ConstantVodUtil implements InitializingBean {


    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;


    public static String KEY_ID;
    public static String KEY_SECRET_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyid;
        KEY_SECRET_ID = keysecret;
    }
}
