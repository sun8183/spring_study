package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration /* 스프링 컨테이너는 @Configuration 어노테이션이 붙은 AppConfig 파일을 설정정보로 사용 */
public class AppConfig {    /* Bean 생성자 주입 */
    /* 다형성에 의해서 어떤 객체가 들어올지는 AppConfig 클래스에서 결정된다. */

    @Bean   /* @Bean 어노테이션을 시작 후 모두 호출 한다. */
    public MemberRepository memberRepository(){
        System.out.println("call memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
    @Bean
    public MemberService memberService(){
        System.out.println("call memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }
}

/*
* 1. BeanFactory
*  - 스프링 최상위 컨테이너
*  - 스프링 빈을 관리하고 조회하는 역할
*
* 2. ApplicationContext - BeanFactory 상속
*  - BeanFactory의 기능을 모두 상속받아서 제공한다.
*  - 부가기능
*  1. 메시지소스를 활용한 국제화 기능
*  2. 환경변수
*  3. 애플리케이션 이벤트
*  4. 편리한 리소스 조회
* */