package com.jaehyun.chatwebsocket.repository;

import com.jaehyun.chatwebsocket.model.MsgRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MsgRoomRepository extends JpaRepository<MsgRoom, Long> {
    MsgRoom findByRoomId(String roomId);
}
