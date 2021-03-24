package com.github.service.impl;

import com.github.service.HelloService;
import org.apache.dubbo.common.URL;

public class HumanHelloService implements HelloService {

    @Override
    public String sayHello() {
        return "hello 你好";
    }

    @Override
    public String sayHello(URL url) {
        return "hello url";
    }
}
