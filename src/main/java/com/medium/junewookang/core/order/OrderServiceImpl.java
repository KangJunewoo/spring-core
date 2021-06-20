package com.medium.junewookang.core.order;

import com.medium.junewookang.core.discount.DiscountPolicy;
import com.medium.junewookang.core.member.Member;
import com.medium.junewookang.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 인터페이스에만 의존하는, 구현체를 전혀 모르는 상태. DIP를 잘 지킨다고 볼 수 있음. 어떤 policy가 들어올지 알 바냐~~ 상태.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
