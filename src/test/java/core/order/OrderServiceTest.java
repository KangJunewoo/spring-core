package core.order;

import core.AppConfig;
import core.member.Grade;
import core.member.Member;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {


    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    public void createOrder() throws Exception {
        // given
        // 깨알상식 : primitive long의 경우 null이 안들어감.
        // 단위테스트 만드는게 정말 중요하다 ! (SpringBootTest 없이 순수 자바 코드로 ㅇㅇ)
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);



        // when


        // then
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
