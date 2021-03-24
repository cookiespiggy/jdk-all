package com.github.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RouterVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        //router.get("/ping").handler(PingHandler.create());
        router.route().handler(BodyHandler.create());
        //自定义的代理handler
        router.route().handler(ProxyHandler.create());
        //router.route().failureHandler(GlobalErrorHandler.create(getLogPluginHandler()));
        HttpServerOptions options = new HttpServerOptions();
        //增加配置初始长度，规避可能存在的uri过长被服务器拒绝的问题
        options.setMaxInitialLineLength(4000);
        vertx.createHttpServer(options).requestHandler(router).listen(3000, http -> {
            if (http.succeeded()) {
                //log.info("HTTP server started on port:" + appConfig.getPort());
            } else {
                //log.error("HTTP server start error!", http.cause());
            }
        });
    }

    public interface ProxyHandler extends Handler<RoutingContext> {
        static ProxyHandler create() {
            //return new ProxyHandlerImpl();
            return null;
        }
    }
}
