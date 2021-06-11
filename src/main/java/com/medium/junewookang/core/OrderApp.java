package com.medium.junewookang.core;

import com.medium.junewookang.core.member.Grade;
import com.medium.junewookang.core.member.Member;
import com.medium.junewookang.core.member.MemberService;
import com.medium.junewookang.core.member.MemberServiceImpl;
import com.medium.junewookang.core.order.Order;
import com.medium.junewookang.core.order.OrderService;
import com.medium.junewookang.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) { // 이러한 메인메서드보다는, junit으로 함수 만들어서 하는게 바람직.
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
