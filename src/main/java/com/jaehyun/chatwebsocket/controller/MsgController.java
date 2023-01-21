package com.jaehyun.chatwebsocket.controller;

import com.jaehyun.chatwebsocket.model.Message;
import com.jaehyun.chatwebsocket.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MsgController {

    private final SimpMessageSendingOperations sendingOperations;
    private final MessageRepository messageRepository;

    @MessageMapping("/comm/message")
    public void message(Message message) {
        messageRepository.save(message);
        if (Message.Type.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+ "이 입장했습니다.");
        }
        sendingOperations.convertAndSend("/sub/comm/room/" + message.getRoomId(), message);
    }
}
