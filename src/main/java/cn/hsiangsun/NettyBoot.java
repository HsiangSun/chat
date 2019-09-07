package cn.hsiangsun;

import cn.hsiangsun.netty.WebSocketServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //判断spring boot 服务是否已经启动
        if (event.getApplicationContext().getParent() == null){
            WebSocketServer.getInstance().start();
        }
    }
}
