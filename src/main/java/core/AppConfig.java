package core;

import core.discount.DiscountPolicy;
import core.discount.FixDiscountPolicy;
import core.discount.RateDiscountPolicy;
import core.member.MemberRepository;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import core.member.MemoryMemberRepository;
import core.order.OrderService;
import core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
여기서 AppConfig는 IoC컨테이너 혹은 DI컨테이너의 역할을 한다고 볼 수 있다.
의존관계를 주입해주기 떄문. (pdf 보면 좀 더 자세히 나와있음.)

사실 @Configuration을 안붙여도 동작은 하는데, 이렇게 되는 경우 cglib 기술이 들어가지 않고 생짜 클래스가 나옴.
즉, 싱글톤이 깨짐. 동작은 해도 효율이 안나겠지.
 */
@Configuration
public class AppConfig {

    /*
    memberService
    memberRepository
    memberRepository
    orderService
    memberRepository

    총 다섯번이 호출되어야 할텐데

    memberService
    memberRepository
    orderService

    총 세 번만 호출됨.
    그럼 스프링은 어떻게 기를 쓰고 자바문법을 거슬러서 싱글톤으로 유지해주는 걸까?

    결론 : @Configuration == cglib 적용기술


    뒤에서 배울거긴 한데
    @Autowired
    MemberRepository memberRepository;
    하면 스프링 빈에서 꺼내와라! 하는 거니 해결되긴 함. 권장되지 않을 뿐.
     */

    @Bean
    public MemberService memberService() {
        // 디버깅용
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 디버깅용
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        // 디버깅용
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
