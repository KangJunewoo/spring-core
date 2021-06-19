package com.medium.junewookang.core.singleton;

import com.medium.junewookang.core.AppConfig;
import com.medium.junewookang.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberservice != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    } // 요청마다 객체를 생성하면 효율적이지 않음. TPS 50000인 배민을 생각해보자.
    // 따라서 멤버서비스 객체를 한 개만 생성하고 이를 공유하면 어떨가?

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // 이미 생성해놓은 걸 가져다 쓰는거~
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 둘은 똑같은 레퍼런스를 가진다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

//        new SingletonService(); // 아름다운 컴파일 오류를 띄워줌.
    } // 스프링 컨테이너를 쓰면, 알아서 싱글톤으로 만들어준다.

    @Test
    @DisplayName("응 싱글톤 직접 안짜고 스프링 컨테이너 어떻게 할건지 볼거야~")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberservice != memberService2
        assertThat(memberService1).isSameAs(memberService2);

    } // 요청마다 객체를 생성하면 효율적이지 않음. TPS 50000인 배민을 생각해보자.
}
