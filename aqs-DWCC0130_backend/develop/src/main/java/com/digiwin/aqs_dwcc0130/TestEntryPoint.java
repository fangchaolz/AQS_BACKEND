package com.digiwin.aqs_dwcc0130;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoAutoConfiguration.class})
public class TestEntryPoint {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TestEntryPoint.class);
        springApplication.addListeners(new ApplicationPidFileWriter("TestEntryPoint.pid"));

        springApplication.setMainApplicationClass(TestEntryPoint.class);
        springApplication.run(args);
        System.out.println("启动。。");
    }
}
