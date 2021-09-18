package core.singleton;

import core.AppConfig;
import core.member.MemberRepository;
import core.member.MemberServiceImpl;
import core.order.OrderService;
import core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    public void configurationTest() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 원랜 이렇게 구체 타입으로 꺼내면 안좋음..^^
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 세 개 모두 같다!!! 분명히 세 번의 new가 여러번 쓰였음에도 불구하고.
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        /*
        당연히 내가 만든 core.AppConfig가 나와야할텐데
        cglib 뭐시기가 나옴.
        이건 사실 스프링이 바이트코드를 조작해(cglib 기술) 다른 클래스를 만들어버린 거임.
        AppConfig를 상속하는 무언가. 코드는 다음과 비슷해서 싱글톤을 유지시킴.
        if(이미 스프링 빈에 등록되어있다면){
            return 스프링 컨테이너에 있는거;
        } else{
            기존로직
            스프링 컨테이너에 등록
            return 등록된거;
        }

        상속하니까 getBean했을때 AppConfig으로 받을 수 있는거고.

         */
        System.out.println("bean.getClass = " + bean.getClass());
    }

}
