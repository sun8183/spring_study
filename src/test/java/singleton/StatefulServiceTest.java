package singleton;

import hello.core.AppConfig;
import hello.core.beanFind.ApplicationContextExtendsFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // A사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // B사용자 20000원 주문
        statefulService1.order("userB", 20000);

        // A사용자 10000원 주문
        statefulService2.order("userA", 10000);

        // B사용자 20000원 주문
        statefulService2.order("userB", 30000);

        int price = statefulService1.getPrice();
        System.out.println(price);

        // 싱글톤 사용, price 공유되는 필드에 값을 변경하는 것이 문제
        // 생성된 객체를 공유해서 사용하기 때문에 Stateless 로 설계해야 한다.
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(30000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefunService(){
            return new StatefulService();
        }
    }

}