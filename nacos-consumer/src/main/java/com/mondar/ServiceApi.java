package com.mondar;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 供外部调用的API
 * </p>
 *
 * @author limondar
 * @since 2019/11/14
 */

@FeignClient(name = "nacos-provider")
public interface ServiceApi {

    @GetMapping("hello")
    String hello(@RequestParam(required = false, defaultValue = "world") String name);

    @GetMapping("/services")
    List<String> getServices();
}
