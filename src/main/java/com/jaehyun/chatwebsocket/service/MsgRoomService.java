package com.jaehyun.chatwebsocket.service;

import com.jaehyun.chatwebsocket.model.MsgRoom;
import com.jaehyun.chatwebsocket.repository.MsgRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MsgRoomService {

    private final MsgRoomRepository msgRoomRepository;

    public List<MsgRoom> findAllRoom() {
        List<MsgRoom> all = msgRoomRepository.findAll();
        Collections.reverse(all);
        return all;
    }

    public MsgRoom getRoomByRoomId(String roomId) {
        return msgRoomRepository.findByRoomId(roomId);
    }

    public MsgRoom createMsgRoom(String name) {
        MsgRoom room = MsgRoom.builder().roomId(name).build();
        System.out.println(room.toString());
        return msgRoomRepository.save(room);
    }
}
