package com.springboot.admin.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConsumerController
 * @Author Administrator
 * @date 2019/10/8 17:36
 */
@RestController
@RequestMapping("/user")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 实例化RestTemplate
     *
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }



    /**
     * http://localhost:8001/user/gotoUser
     * Rest服务端使用RestTemplate发起http请求,然后得到数据返回给前端----gotoUser是为了区分getUser怕小伙伴晕头
     *
     * 使用RestTemplate时报错java.lang.IllegalStateException: No instances available for 127.0.0.1
     *
     * 1：不要使用ip+port的方式访问，取而代之的是应用名
     * 2：这种方式发送的请求都会被ribbon拦截，ribbon从eureka注册中心获取服务列表，然后采用均衡策略进行访问
     * @param id
     * @return
     */
    @GetMapping(value = "/gotoUser")
    public Map<String, Object> getUser(@RequestParam Integer id) {
        Map<String, Object> data = new HashMap<>();
        /**
         * 小伙伴发现没有，地址居然是http://service-provider
         * 居然不是http://127.0.0.1:8082/
         * 因为他向注册中心注册了服务，服务名称service-provider,我们访问service-provider即可
         */
        data = restTemplate.getForObject("http://SpringBoot-AdminServer/user/info/getUser?id=" + id, Map.class);
        return data;
    }


}
