package core.beanfind;

import core.discount.DiscountPolicy;
import core.discount.FixDiscountPolicy;
import core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void 부모_타입으로_조회_시_자식이_둘_이상_있으면_중복_오류_발생() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }


    @Test
    void 부모_타입으로_조회_시_자식이_둘_이상_있으면_빈_이름_지정하면_됨() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    void 특정_하위_타입으로_조회() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }



    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }

    @Test
    public void 부모_타입으로_모두_조회하기() throws Exception {
        // given
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key :
                beansOfType.keySet()) {
            // 실무에선 빼야하는거 알지?
            System.out.println("key = " + key + "value = " + beansOfType.get(key));

        }

        // when


        // then


    }
    @Test
    public void 부모_타입으로_모두_조회하기_오브젝트() throws Exception {
        // given
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key :
                beansOfType.keySet()) {
            // 실무에선 빼야하는거 알지?
            System.out.println("key = " + key + "value = " + beansOfType.get(key));

        }

        // when


        // then


    }

}
