package com.mondar;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderApplication {

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 读取配置中心的配置，如果没有，则为 “Hello %s !”
     */
    @Value("${hello:Hello %s !}")
    String helloTemplate;

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }

    @SentinelResource
    @GetMapping("hello")
    public String hello(@RequestParam(required = false, defaultValue = "world") String name) {
        return String.format(helloTemplate, name);
    }

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
