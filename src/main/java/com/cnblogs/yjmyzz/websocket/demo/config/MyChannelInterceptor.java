package com.cnblogs.yjmyzz.websocket.demo.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * 监听连接
 */
public class MyChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        /*if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Authentication user = ... ; // access authentication header(s)
            accessor.setUser(user);
        }*/
        //判断客户端的连接状态
        switch (accessor.getCommand()) {
            case CONNECT:
                MyPrincipal mp = (MyPrincipal)accessor.getHeader("simpUser");
                System.out.println("上线："+mp.getName());
                break;
            case DISCONNECT:
                System.out.println("下线");
                break;
            case SUBSCRIBE:
                System.out.println("订阅");
                break;
            case SEND:
                System.out.println("发送");
                break;
            case UNSUBSCRIBE:
                System.out.println("取消订阅");
                break;
            default:
                break;
        }
        return message;
    }
}