package com.medium.junewookang.core.order;

import com.medium.junewookang.core.discount.DiscountPolicy;
import com.medium.junewookang.core.discount.FixDiscountPolicy;
import com.medium.junewookang.core.discount.RateDiscountPolicy;
import com.medium.junewookang.core.member.Member;
import com.medium.junewookang.core.member.MemberRepository;
import com.medium.junewookang.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // SRP가 잘 지켜진 사례. 하지만 OCP와 DIP는 위반한다고 한다.
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 갈아 끼우기
    // private DiscountPolicy discountPolicy; // 이렇게 해야 DIP와 OCP를 잘 지킨다. 하지만 nullpointerexception 나겠지?
    // 해결방안 : 누군가가 DiscountPolicy의 구현 객체를 주입해줘야 함.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
