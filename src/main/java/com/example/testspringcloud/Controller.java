package com.example.testspringcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("check")
  public String readiness() {
    return "Hi";
  }
  @GetMapping("api")
  public String hi() {
    return "this is the api";
  }
}
