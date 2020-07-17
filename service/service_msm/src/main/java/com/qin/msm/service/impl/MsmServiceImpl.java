package com.qin.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.qin.msm.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-17 11:51
 **/
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GH9kF246mS7S4QWKVPb", "PRnmLort59Epsyd8VSSfU2sPmA8FoA");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");


        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我的烛光在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_196617313");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));


        try{
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
