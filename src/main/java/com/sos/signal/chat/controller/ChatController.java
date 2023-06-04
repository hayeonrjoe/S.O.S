package com.sos.signal.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.sos.signal.chat.vo.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ChatController {

    List<Room> roomList = new ArrayList<Room>();
    static int roomNumber = 0;

    @RequestMapping("/chat")
    public String chat() {
        return "chat/chat";
    }

    /**
     * 방 페이지
     * @return
     */
    @RequestMapping("/room")
    public String room() {
       return "chat/room";
    }

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @RequestMapping("/createRoom")
    public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params){
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")) {
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;
    }

    /**
     * 방 정보가져오기
     * @param params
     * @return
     */
    @RequestMapping("/getRoom")
    public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    /**
     * 채팅방
     * @return
     */
    @RequestMapping("/moveChating")
    public String chating(@RequestParam HashMap<Object, Object> params, Model model) {
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if(new_list != null && new_list.size() > 0) {
            model.addAttribute("roomName", params.get("roomName"));
            model.addAttribute("roomNumber", params.get("roomNumber"));
            return "chat/chat";
        } else {
            return "chat/room";
        }
    }
}