package com.example.camel.demo.route;

import com.example.camel.demo.entity.UserProfile;
import com.example.camel.demo.entity.UserStreamingProfile;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StreamingProfileRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:streamingProfileRoute")
        .log("entering stream profile route for user: ${body.email}")
        .process(this::processStreamingProfile)
        .log("stream profile created with userId :['${body.streamingId}']")
        .end();
  }

  private void processStreamingProfile(Exchange inExchange) {

    UserProfile userProfile = inExchange.getIn().getBody(UserProfile.class);
    UserStreamingProfile streamProfile = UserStreamingProfile.builder()
        .userId(userProfile)
        .streamingId(UUID.randomUUID().toString())
        .build();
    inExchange.getIn().setBody(streamProfile);
  }
}
