package com.funtl.hello.spring.cloud.web.admin.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//服务提供者
@FeignClient(value = "hello-spring-cloud-service-admin")
public interface AdminService {

    @GetMapping("/hi")
    public String sayHi(@RequestParam("message") String message);
}
