package com.jaehyun.chatwebsocket.controller;

import com.jaehyun.chatwebsocket.model.MsgRoom;
import com.jaehyun.chatwebsocket.service.MsgRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comm")
public class MsgRoomController {
    private final MsgRoomService msgRoomService;

    @GetMapping("/room")
    public String room() {
        return "/chat/room";
    }

    @ResponseBody
    @GetMapping("/rooms")
    public List<MsgRoom> rooms() {
        List<MsgRoom> msgRooms = msgRoomService.findAllRoom();
        System.out.println(msgRooms);
        return msgRooms;
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomEnter(Model model,
                            @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @ResponseBody
    @GetMapping("/room/{roomId}")
    public MsgRoom roomInfo(@PathVariable String roomId) {
        return msgRoomService.getRoomByRoomId(roomId);
    }

    @PostMapping("/room")
    @ResponseBody
    public MsgRoom createRoom(@RequestParam String name) {
        return msgRoomService.createMsgRoom(name);
    }
}
