package com.mondar;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @Autowired
    ServiceApi serviceApi;

    /**
     * RestTemplate 也可以添加 @LoadBalanced 注解增加 Ribbon 负载均衡功能
     * @param builder
     * @return
     */
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @SentinelResource
    @GetMapping("hello")
    String hello(@RequestParam(required = false, defaultValue = "world") String name) {
        return serviceApi.hello(name);
    }

    @SentinelResource
    @GetMapping("/services")
    List<String> getServices() {
        return serviceApi.getServices();
    }

}
