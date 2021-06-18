package com.medium.junewookang.core;

import com.medium.junewookang.core.member.Grade;
import com.medium.junewookang.core.member.Member;
import com.medium.junewookang.core.member.MemberService;
import com.medium.junewookang.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) { // 일종의 테스트 코드
        AppConfig appConfig = new AppConfig(); // 설정파일을 불러와서
        MemberService memberService = appConfig.memberService(); // 멤버서비스 객체를 만든다음
        Member member = new Member(1L, "memberA", Grade.VIP); // 멤버가입
        memberService.join(member);

        Member findMember = memberService.findMember(1L); // 멤버조회
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
