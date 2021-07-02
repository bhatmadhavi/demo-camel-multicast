package com.example.camel.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserStreamingProfile {
  private UserProfile userId;
  private String streamingId;
}
