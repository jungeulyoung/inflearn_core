package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
  MemberService memberService;
  OrderService orderService;
  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  @BeforeEach  //테스트 돌기전에 미리 돌아 간다 .
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  void createOrder() {
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);

    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

  @Test
  void fieldInjectionTest() {
    OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);
    orderService.setMemberRepository(new MemoryMemberRepository());
    orderService.setDiscountPolicy(new FixDiscountPolicy());
    orderService.createOrder(1L, "itemA", 10000);

  }
}
