package com.jaehyun.chatwebsocket.repository;

import com.jaehyun.chatwebsocket.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
