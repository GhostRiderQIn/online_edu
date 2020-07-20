package com.qin.ucenter.service;

import com.qin.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author qinda
 * @since 2020-07-17
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Member getOpenIdMember(String openid);
}
