package com.longfor.gaia.gfs.demo.dragonletter.controller;

import com.google.common.collect.Lists;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.web.dragonletter.domain.DLMessageSendReq;
import com.longfor.gaia.gfs.web.dragonletter.domain.DLTextMessage;
import com.longfor.gaia.gfs.web.dragonletter.domain.MessageType;
import com.longfor.gaia.gfs.web.dragonletter.service.DragonLetterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Resource
    private DragonLetterService dragonLetterService;

    @GetMapping
    public BaseResponse sendMessage() {
        dragonLetterService.sendMessage(mockReq());
        return new BaseResponse();
    }

    private DLMessageSendReq mockReq() {
        DLMessageSendReq mock = new DLMessageSendReq();
        mock.setReceivers(Lists.newArrayList("shanhonghao"));
        mock.setSender("wangfajin");
        DLTextMessage content = new DLTextMessage();
        content.setContent("it is a demo");
        mock.setContent(content);
        mock.setMessageType(MessageType.TEXT);
        return mock;
    }
}
