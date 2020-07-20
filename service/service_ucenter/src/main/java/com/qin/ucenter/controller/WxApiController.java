package com.qin.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.qin.commonutils.JwtUtils;
import com.qin.ucenter.entity.Member;
import com.qin.ucenter.service.MemberService;
import com.qin.ucenter.utils.ConstantWxUtils;
import com.qin.ucenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-19 22:47
 **/
@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/callback")
    public String callback(String code,String state){

        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code
        );

        try {
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            Gson gson = new Gson();
            HashMap map = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) map.get("access_token");
            String openid = (String) map.get("openid");


            Member member = memberService.getOpenIdMember(openid);
            //第一次扫码
            if (member == null){
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseAccessTokenUrl,
                        access_token,
                        openid);

                String userInfo = HttpClientUtils.get(userInfoUrl);

                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimageurl = (String) userInfoMap.get("headimageurl");

                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimageurl);
                memberService.save(member);
            }
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());


            return "redirect:http://localhost:3000?token"+jwtToken;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "redirect:http://localhost:3000";
    }

    @RequestMapping("/login")
    public String getWxCode(){
        //请求微信地址
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        System.out.println(ConstantWxUtils.WX_OPEN_REDIRECT_URL);
        String redirect_url = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirect_url = URLEncoder.encode(redirect_url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirect_url,
                "qin"
        );

        return "redirect:"+url;
    }
}
