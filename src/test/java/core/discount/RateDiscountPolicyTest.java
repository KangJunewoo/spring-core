package core.discount;

import core.member.Grade;
import core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    public void vip_o() throws Exception {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);

    }

    // 실패 테스트도 꼭! 만들어봐야 한다.
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되어선 안된다")
    public void vip_x() throws Exception {
        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);


        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);

    }

}