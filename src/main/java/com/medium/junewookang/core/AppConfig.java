package com.medium.junewookang.core;

import com.medium.junewookang.core.discount.DiscountPolicy;
import com.medium.junewookang.core.discount.FixDiscountPolicy;
import com.medium.junewookang.core.discount.RateDiscountPolicy;
import com.medium.junewookang.core.member.MemberRepository;
import com.medium.junewookang.core.member.MemberService;
import com.medium.junewookang.core.member.MemberServiceImpl;
import com.medium.junewookang.core.member.MemoryMemberRepository;
import com.medium.junewookang.core.order.OrderService;
import com.medium.junewookang.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 아래와 같이 디버깅 출력을 해보면, 진짜 딱 한번씩만 만들어지는 것을 알 수 있다.
    // 멤버서비스, 오더서비스 모두 멤버리포지토리를 호출하는데 말이다!

    @Bean
    public MemberService memberService() {
        System.out.println("memberService 생성자 호출");
        return new MemberServiceImpl(memberRepository());
    } // 서비스에 리포지토리 주입

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("memberRepository 생성!");
        return new MemoryMemberRepository();
    } // jdbc, jpa, memory 등등에서 memory 채택 및 주입

    @Bean
    public OrderService orderService() {
        System.out.println("orderService 생성!");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    } // 서비스에 리포지토리와 할인정책 주입

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    } // 다양한 할인정책 중 정률할인정책 적용
}