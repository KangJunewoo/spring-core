package core.singleton;

import core.AppConfig;
import core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    // 이거의 문제점을 보고 스프링과 비교해볼 것.
    @Test
    public void 스프링_없는_순수한_di_컨테이너() throws Exception {
        /*
        웹어플리케이션의 특징 : 요청이 겁내 많다.
        배민은 5만 TPS를 가지고 있음.
        근데 이런식으로 서로 다른 객체를 마구마구 JVM에 올린다? 좋지 않음.
        TPS 100이어도 초당 100개 객체가 생성되고 소멸할텐데.
        이를 해결할 방법은 멤버서비스 객체를 딱 하나만 생성해서 공유하면 됨.
         */
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

        // when


        // then


    }

    @Test
    public void 싱글톤_패턴을_적용한_객체_사용() throws Exception {
        // given
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // isSameAs는 ==, isEqualTo는 equals 메소드로 비교.
        assertThat(singletonService1).isSameAs(singletonService2);

        // when


        // then


    }

    @Test
    public void 스프링_컨테이너와_싱글톤() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);


    }
}
