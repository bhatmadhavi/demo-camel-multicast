package com.example.camel.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserProfile {

  private String firstName;
  private String lastName;
  private String email;
  private String userId;
}
