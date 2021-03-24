package com.github;

import com.github.service.HelloService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
/*
Dubbo中的Adaptive功能，主要解决的问题是如何动态的选择具体的扩展点。
通过getAdaptiveExtension 统一对指定接口对应的所有扩展点进行封装，通过URL的方式对扩展点来进行动态选择。
(dubbo中所有的注册信息都是通过URL的形式进行处理的。)这里同样采用相同的方式进行实现。
 */
public class DubboAdaptiveMain {
    public static void main(String[] args) {
        // 获取扩展加载器
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        HelloService adaptiveExtension = extensionLoader.getAdaptiveExtension();
        // URL url = URL.valueOf("test://localhost/hello?hello.service=human");
        URL url = URL.valueOf("mytest://mylocalhost/myhello");
        // 如果URL没有提供该参数，则该方法会使用默认在 SPI 注解中声明的实现。
        String msg = adaptiveExtension.sayHello(url);
        System.out.println(msg);
    }
}
