package com.jaehyun.chatwebsocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaehyun.chatwebsocket.model.Message;
import com.jaehyun.chatwebsocket.model.MsgRoom;
import com.jaehyun.chatwebsocket.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final MsgService msgService;
    private final ObjectMapper objectMapper;

    /**
     * 웹 소켓 클라이언트로 부터 메세지를 전달받아 Message객체로 변환
     * 전달받은 Message 객체에 담긴 roomId로 발송 대상 채팅방을 조회
     * 해당 채팅방에 입장해 있는 모든 클라이언트(Set<WebSocketSession>)에게 타입에 맞는 메세지를 전송
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload={}", payload);

        Message msg = objectMapper.readValue(payload, Message.class);
        MsgRoom room = msgService.findById(msg.getRoomId());
        room.handleActions(session, msg, msgService);
    }
}
