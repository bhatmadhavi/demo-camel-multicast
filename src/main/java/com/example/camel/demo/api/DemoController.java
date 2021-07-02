package com.example.camel.demo.api;

import com.example.camel.demo.entity.NewUserRequest;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @Autowired
  private ProducerTemplate newUserRouteProducer;

  @PostMapping("/new-user")
  public ResponseEntity<String> greeting(@RequestBody NewUserRequest newRequest) {
    newUserRouteProducer.sendBody("direct:newUserRoute", newRequest);
    return ResponseEntity.accepted()
        .body("User added successfully!");
  }
}
