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

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    } // 서비스에 리포지토리 주입

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    } // jdbc, jpa, memory 등등에서 memory 채택 및 주입

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    } // 서비스에 리포지토리와 할인정책 주입

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    } // 다양한 할인정책 중 정률할인정책 적용
}