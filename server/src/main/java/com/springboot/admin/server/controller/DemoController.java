package com.springboot.admin.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName DemoController
 * @Author Administrator
 * @date 2019/10/8 17:25
 */
@RestController
@RequestMapping("/user/info")
public class DemoController {

    /**
     * 假如这个客户端要提供一个getUser的方法
     *
     * @return
     */
    @GetMapping(value = "/getUser")
    public Map<String, Object> getUser(@RequestParam Integer id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("userName", "admin");
        data.put("from", "provider-A");
        return data;
    }
}
