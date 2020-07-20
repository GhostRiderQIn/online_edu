package com.qin.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.commonutils.JwtUtils;
import com.qin.commonutils.MD5;
import com.qin.servicebase.exception.MyException;
import com.qin.ucenter.entity.Member;
import com.qin.ucenter.entity.vo.RegisterVo;
import com.qin.ucenter.mapper.MemberMapper;
import com.qin.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author qinda
 * @since 2020-07-17
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
            throw new MyException(20001,"登录失败");

        //判断手机号
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);

        if (mobileMember == null)
            throw new MyException(20001,"手机号不存在");

        if (!MD5.encrypt(password).equals(mobileMember.getPassword()))
            throw new MyException(20001,"密码错误");

        if (mobileMember.getIsDisabled())
            throw new MyException(20001,"账号被封禁");

        //登录成功
        String token = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());

        return token;
    }

    /**
     * 注册
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code)|| StringUtils.isEmpty(mobile)||
            StringUtils.isEmpty(nickname)|| StringUtils.isEmpty(password)
        )
            throw new MyException(20001,"注册失败");

        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (redisCode == null || !redisCode.equals(code))
            throw new MyException(20001,"验证码错误");

        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0)
            throw new MyException(20001,"手机号已存在");


        //注册
        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("https://edu-qin.oss-cn-beijing.aliyuncs.com/avatar.jpg");
        baseMapper.insert(member);


    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);

        return baseMapper.selectOne(wrapper);
    }
}
