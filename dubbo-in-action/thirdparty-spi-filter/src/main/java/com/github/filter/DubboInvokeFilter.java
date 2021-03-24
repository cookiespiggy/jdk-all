package com.github.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

// 使用 org.apache.dubbo.common.extension.Activate 注解进行对类进行注册
// 通过group 可以指定生产端 消费端
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class DubboInvokeFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();
        try {
            // 执行方法
            return invoker.invoke(invocation);
        } finally {
            System.out.println("invoke time:" + (System.currentTimeMillis() - startTime) + "毫秒");
        }
    }
}
