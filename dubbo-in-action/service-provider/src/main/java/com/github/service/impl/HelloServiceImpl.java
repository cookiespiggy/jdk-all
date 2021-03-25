package com.github.service.impl;

import com.github.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name, int timeToWait) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello:" + name;
    }
}
