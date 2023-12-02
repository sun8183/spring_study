package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String [] args){
        //AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();
        /* 인터페이스 변수에 의존하고 있어 구현체 변경시에 코드 수정이 유연하다. */

        /*
        * MemberServiceImpl memberService = new MemberServiceImpl();
        *
        * 구현체 변수에 의존하고 있어 구현체 변경시 코드 수정이 필요하다.
        * */

        /*Bean 을 관리하는 context 객체 - 스프링 컨테이너 ( AppConfig : 환경설정 )
        * 1. 스프링 컨테이너가 생성되면 스프링 빈 저장소도 같이 생성되어 구성정보는 AppConfig 파일을 활용
        * 2. 빈 이름은 메서드 이름으로 사용하며 직접 부여할 수도 있다. (Bean 이름은 충돌 X)
        * */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member); /* 회원가입 Test */
        Member member1 = memberService.findMember(1L);

        System.out.println("member :" + member.getName());
        System.out.println("member1 :" + member1.getName());

    }
}
