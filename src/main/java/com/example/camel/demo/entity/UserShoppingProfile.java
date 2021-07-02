package com.example.camel.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserShoppingProfile {
  private UserProfile userId;
  private String shoppingId;
}
