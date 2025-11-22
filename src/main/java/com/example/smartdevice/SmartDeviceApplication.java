package com.example.smartdevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartDeviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartDeviceApplication.class, args);
    }
}
