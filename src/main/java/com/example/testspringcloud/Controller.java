package com.example.testspringcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class Controller {

  private static final Logger logger = LoggerFactory.getLogger(Controller.class);
  private final DiscoveryClient discoveryClient;
//  private final WebClient.Builder loadBalancedWebClientBuilder;

//  public Controller(WebClient.Builder loadBalancedWebClientBuilder) {
//    this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
//  }


  public Controller(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  @GetMapping("check")
  public String readiness() {
    return "Hi";
  }

  @GetMapping("api")
  public String hi() {
//    String block = loadBalancedWebClientBuilder.build().get().uri("http://nginx:8080")
//        .retrieve().bodyToMono(String.class)
//        .map(response -> String.format("response: %s", response))
//        .block();
//    logger.info("response: {}", block);
    List<ServiceInstance> nginx = discoveryClient.getInstances("nginx");
    for (ServiceInstance instance : nginx) {
      String block = WebClient.builder()
          .build()
          .get()
          .uri("http://" + instance.getHost() + ":8080")
          .retrieve().bodyToMono(String.class)
          .map(response -> String.format("response %s", response))
          .block();
      logger.info("response from instance[{}]", instance.getHost());
    }
    logger.info("found {} instances", nginx.size());

    return "this is the api";
  }
}
