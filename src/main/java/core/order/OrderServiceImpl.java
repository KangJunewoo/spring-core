package core.order;

import core.discount.DiscountPolicy;
import core.discount.FixDiscountPolicy;
import core.member.Member;
import core.member.MemberRepository;
import core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 와리가리 가능.(Fix vs Rate)
    /*
    여기서 문제점이 드러남.
    역할과 구현은 충실하게 분리함.
    다형성 활용했고, 인터페이스와 구현객체 분리했음.
    그럼 SOLID도 모두 준수했다? 그건 아님.
    DIP 위반 : OrderServiceImpl은 DiscountPolicy에만 의존해야 하는데 FixDiscountPolicy에도 의존하고 있다.
    OCP 위반 : 정책 변경 시 오더서비스 코드를 변경해야한다.

    아니 코드를 안고치고 작성하는게 가능하다고?
    그러면 그냥 구체클래스 의존관계를 떨궈내면 되겠지.
    private DiscountPolicy discountPolicy;
    이렇게 선언하면 되겠지 ㅇㅇ... 근데 당연히 널포인터익셉션 뜨겠지.
    누군가가 discountpolicy에 몰래 구체적인 정보를 주입해줘야함.

    그래서 아래와 같이 수정해줄 수 있음.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
