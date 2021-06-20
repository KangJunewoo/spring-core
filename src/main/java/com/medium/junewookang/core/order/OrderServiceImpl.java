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

    // 요런 식으로 필드 주입도 가능. 다만 외부변경이 불가능하므로 권장되진 않음.
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 요런 식으로 수정자 주입도 가능.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    // 생성자 주입을 하므로 setter 쓰면 안됨. 그래서 final 선언해놓은것도 있음.
    // 생성자가 딱 한개면 Autowired 안붙여도 되긴 함.
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
