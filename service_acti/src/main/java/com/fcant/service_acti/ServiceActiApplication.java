package com.fcant.service_acti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication(exclude={
        org.activiti.spring.boot.SecurityAutoConfiguration.class
})
@ComponentScan("com.fcant")
public class ServiceActiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActiApplication.class, args);
    }

}
