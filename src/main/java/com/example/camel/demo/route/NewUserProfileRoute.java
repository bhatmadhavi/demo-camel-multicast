package com.example.camel.demo.route;

import com.example.camel.demo.entity.NewUserRequest;
import com.example.camel.demo.entity.UserProfile;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NewUserProfileRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:newUserRoute")
        .log("entering new user profile route body: ${body}")
        .process(this::processNewProfile)
        .log("new profile created with userId :['${body.userId}']")
        .multicast()
        .parallelProcessing()
        .to("direct:streamingProfileRoute", "direct:shoppingProfileRoute")
        .log("-----original route body unchanged: ${body}")
        .end();
  }

  private void processNewProfile(Exchange inExchange) {

    NewUserRequest request = inExchange.getIn().getBody(NewUserRequest.class);
    UserProfile profile = UserProfile.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .userId(UUID.randomUUID().toString())
        .build();
    inExchange.getIn().setBody(profile);
  }
}
