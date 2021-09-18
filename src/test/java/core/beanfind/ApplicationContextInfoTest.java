package core.beanfind;

import core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    public void findAllBean() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName :
                beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }

    }

    @Test
    public void findApplicationBean() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName :
                beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // ROLE_APPLICATIN : 내가 등록한거
            // ROLE_INFRASTRUCTURE : 스프링이 등록한거
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }

    }
}
