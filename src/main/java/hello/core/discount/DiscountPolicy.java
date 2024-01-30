package hello.core.discount;

import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public interface DiscountPolicy {

    /* 할인 금액 RETURN */
    int discount(Member member, int price);

}
