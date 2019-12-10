package com.springboot.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @ClassName AdminApplication
 * @Author こうれいひ
 * @date 2019/12/10 10:15
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAdminServer  //admin 服务端注解
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);

//        //启动服务端
//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start(new InetSocketAddress("127.0.0.1", 9090));
    }
}
