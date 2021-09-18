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

/*
여기서 AppConfig는 IoC컨테이너 혹은 DI컨테이너의 역할을 한다고 볼 수 있다.
의존관계를 주입해주기 떄문. (pdf 보면 좀 더 자세히 나와있음.)
 */
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
