package com.jaehyun.chatwebsocket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    public enum Type {
        ENTER, COMM
    }

    @Enumerated(value = EnumType.STRING)
    private Type type;

    private String roomId;
    private String sender;
    private String message;
}
