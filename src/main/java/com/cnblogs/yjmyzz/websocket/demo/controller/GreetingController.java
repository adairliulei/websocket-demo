package com.cnblogs.yjmyzz.websocket.demo.controller;

import com.cnblogs.yjmyzz.websocket.demo.consts.GlobalConsts;
import com.cnblogs.yjmyzz.websocket.demo.model.ClientMessage;
import com.cnblogs.yjmyzz.websocket.demo.model.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.support.SendToMethodReturnValueHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author junmingyang
 * @date 2018/9/24 7:03 PM
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(GlobalConsts.HELLO_MAPPING)
    public void greeting(Principal principal, ClientMessage message) throws Exception {

        // 模拟延时，以便测试客户端是否在异步工作
        Thread.sleep(1000);
        System.out.println(principal.getName());
        this.simpMessagingTemplate.convertAndSendToUser(principal.getName(), GlobalConsts.QUEUE, "hello");
    }
}
