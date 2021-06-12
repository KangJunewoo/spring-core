package com.medium.junewookang.core.discount;

import com.medium.junewookang.core.member.Grade;
import com.medium.junewookang.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // Assertions같은 경우 static import하는게 좋음.
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인")
    void vip_o(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);


        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);


    }

    @Test // 실패테스트도 꼭 만들어봐야 한다
    @DisplayName("VIP가 아니면 할인 X")
    void vip_x(){
        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);


        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}