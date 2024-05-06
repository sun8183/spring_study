package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach /* 테스트 시작 전에 실행된다. ( 의존성 주입 )*/
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    public void joinTest(){
        Member member = new Member(1L, "memberTest", Grade.BASIC);
        memberService.join(member);

        Member member1 = memberService.findMember(1L);

        //assertEquals(member, member1);

        Assertions.assertThat(member).isEqualTo(member1);   /* Junit 테스트 객체 비교 */
    }
}
