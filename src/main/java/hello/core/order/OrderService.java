package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;

public interface OrderService {
  Order createOrder(Long memberId, String itemName, int itemPrice);
  Grade grade();
  Member member();


}

