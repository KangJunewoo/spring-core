package core.beanfind;

import core.AppConfig;
import core.discount.DiscountPolicy;
import core.member.MemberRepository;
import core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    public void 타입으로_조회시_같은_타입이_둘_이상_있으면_중복_오류_발생() throws Exception {
        // given
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
        // when


        // then


    }
    @Test
    public void 타입으로_조회시_같은_타입이_둘_이상_있으면_빈_이름을_지정하면_됨() throws Exception {
        // given
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        // when


        // then


    }

    @Test
    public void 특정_타입_모두_조회() throws Exception {
        // given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key :
                beansOfType.keySet()) {
            System.out.println("key=" + key + " value = " + beansOfType.get(key));

        }
        System.out.println("beansofType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
        // when


        // then


    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }

}
