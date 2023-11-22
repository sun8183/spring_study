package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class memberApp {

    public static void main(String [] args){
        MemberService memberService = new MemberServiceImpl();
        /* 인터페이스 변수에 의존하고 있어 구현체 변경시에 코드 수정이 유연하다. */

        /*
        * MemberServiceImpl memberService = new MemberServiceImpl();
        *
        * 구현체 변수에 의존하고 있어 구현체 변경시 코드 수정이 필요하다.
        * */

        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member); /* 회원가입 Test */
        Member member1 = memberService.findMember(1L);

        System.out.println("member :" + member.getName());
        System.out.println("member1 :" + member1.getName());

    }
}
