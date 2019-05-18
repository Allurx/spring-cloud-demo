package com.zyc.user;

import com.zyc.core.constant.GlobalConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zyc
 */
@ComponentScan(GlobalConstant.BASE_PACKAGE)
@EnableFeignClients(GlobalConstant.FEIGN_PACKAGE)
@SpringCloudApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
