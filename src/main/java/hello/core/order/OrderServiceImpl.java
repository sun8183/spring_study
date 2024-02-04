package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    //@Qualifier("mainDisountPolicy") - 문자이기 때문에 컴파일 오류를 잡기 힘듬
    //@MainDiscountPolicy
    //private final DiscountPolicy discountPolicy;

    /* 생성자 주입 */
    /*
    lombok라이브러리로 @RequiredArgsConstructor 어노테이션으로 생성자가 자동으로 만들어 진다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //int discountPrice = discountPolicy.discount(member, itemPrice); /* 확장성을 고려한 Member 전달 */

        int discountPrice = 0;
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 Test 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
