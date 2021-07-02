package com.example.camel.demo.route;

import com.example.camel.demo.entity.UserProfile;
import com.example.camel.demo.entity.UserShoppingProfile;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShoppingProfileRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:shoppingProfileRoute")
        .log("entering Shopping profile route for user: ${body.email}")
        .process(this::processShoppingProfile)
        .log("Shopping profile created with userId :['${body.shoppingId}']")
        .end();
  }

  private void processShoppingProfile(Exchange inExchange) {

    UserProfile userProfile = inExchange.getIn().getBody(UserProfile.class);
    UserShoppingProfile shoppingProfile = UserShoppingProfile.builder()
        .userId(userProfile)
        .shoppingId(UUID.randomUUID().toString())
        .build();
    inExchange.getIn().setBody(shoppingProfile);
  }
}
