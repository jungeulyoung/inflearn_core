package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
  @Test
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    int userAPrice =  statefulService1.order("userA", 10000);
    int userBPrice = statefulService2.order("userB", 20000);

    //TreadA
    System.out.println("A 의 돈"+  userAPrice);
    System.out.println("B 의 돈" + userBPrice);

//    Assertions.assertThat(userAPrice).isEqualTo(userBPrice);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService()  {
      return new StatefulService();
    }

  }

}