package com.medium.junewookang.core.beanfind;

import com.medium.junewookang.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// junit 5부터는 public 안해도 된다고 함.
class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        // 옵션 커맨드 v 꿀단축키
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // iter 탭도 꿀
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        // 옵션 커맨드 v 꿀단축키
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // iter 탭도 꿀
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION : 직접 등록한거
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 알아서 등록한거
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

}
