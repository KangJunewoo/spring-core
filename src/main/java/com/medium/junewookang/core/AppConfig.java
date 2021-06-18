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

public class AppConfig {
    // 어떤 정책을 반영할건지는 여기서 결정함.
    // 이걸 memberService, orderService에 대한 DI라고 함. 의존관계 주입 혹은 의존성 주입.
    // 할인정책이 변경되어도, 구성영역(여기)만 교체하지 사용영역은 전혀 손댈 필요 없음.

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    } // 서비스에 리포지토리 주입

    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    } // jdbc, jpa, memory 등등에서 memory 채택 및 주입

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    } // 서비스에 리포지토리와 할인정책 주입

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    } // 다양한 할인정책 중 정률할인정책 적용
}