package com.example.testspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestSpringCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestSpringCloudApplication.class, args);
  }
}
