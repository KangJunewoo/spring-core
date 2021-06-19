package com.medium.junewookang.core;

import com.medium.junewookang.core.member.Grade;
import com.medium.junewookang.core.member.Member;
import com.medium.junewookang.core.member.MemberService;
import com.medium.junewookang.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) { // 일종의 테스트 코드
//        AppConfig appConfig = new AppConfig(); // 설정파일을 불러와서
//        MemberService memberService = appConfig.memberService(); // 멤버서비스 객체를 만든다음

        // AppConfig에 있는 환경설정 정보를 가지고 알아서 관리해줘라~
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 그 중 멤버서비스를 꺼내겠다.

        Member member = new Member(1L, "memberA", Grade.VIP); // 멤버가입
        memberService.join(member);

        Member findMember = memberService.findMember(1L); // 멤버조회
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
