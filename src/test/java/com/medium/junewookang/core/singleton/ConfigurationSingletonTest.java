package com.medium.junewookang.core.singleton;

import com.medium.junewookang.core.AppConfig;
import com.medium.junewookang.core.member.MemberRepository;
import com.medium.junewookang.core.member.MemberServiceImpl;
import com.medium.junewookang.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        // AppConfig에서 설정을 불러와서
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 멤버서비스, 오더서비스, 그 두개가 생성될때 또 생성된 멤버리포지토리를 싹 불러왔는데
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 셋 다 같은 인스턴스를 참조하고 있음. 즉 스프링이 알아서 싱글톤으로 관리해주고 있음을 의미.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
    }
}
