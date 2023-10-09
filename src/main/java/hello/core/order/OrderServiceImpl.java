package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

  private MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;


  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository,  DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }


  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  @Override
  public Grade grade() {
    return null;
  }

  @Override
  public Member member() {
    return null;
  }

  public void setMemberRepository(MemoryMemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void setDiscountPolicy(FixDiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }

  public FixDiscountPolicy getDiscountPolicy() {
    return (FixDiscountPolicy) discountPolicy;
  }
}
