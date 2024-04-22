package com.monsatorm.demo;

import com.monsatorm.demo.controller.RequestController;
import com.monsatorm.demo.service.impl.OrderDetailServiceImpl;
import com.monsatorm.demo.service.impl.OrderServiceImpl;
import com.monsatorm.demo.service.impl.SessionServiceImpl;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

    }
}
