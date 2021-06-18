package com.medium.junewookang.core;

import com.medium.junewookang.core.discount.DiscountPolicy;
import com.medium.junewookang.core.discount.FixDiscountPolicy;
import com.medium.junewookang.core.member.MemberRepository;
import com.medium.junewookang.core.member.MemberService;
import com.medium.junewookang.core.member.MemberServiceImpl;
import com.medium.junewookang.core.member.MemoryMemberRepository;
import com.medium.junewookang.core.order.OrderService;
import com.medium.junewookang.core.order.OrderServiceImpl;

public class AppConfig {
    // 어떤 정책을 반영할건지는 여기서 결정함.
    // 이걸 memberService 혹은 orderService에 대한 DI라고 함. 의존관계 주입 혹은 의존성 주입.

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}