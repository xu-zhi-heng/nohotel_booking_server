package com.xu.nohotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.Room;
import com.xu.nohotel.service.RoomService;
import com.xu.nohotel.service.RoomTypeService;
import com.xu.nohotel.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @RequestMapping("/getRoomDelByType")
    public Object getRoomDelByType(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String typeName = request.getParameter("typeName").trim();
        int roomTypeId = roomTypeService.getRoomTypeId(typeName);
        List<Room> roomByType = roomService.getRoomByType(roomTypeId);
        if(roomByType != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("roomByType",roomByType);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }

    @RequestMapping("/getRoomDelById")
    public Object getRoomDelById(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        Room roomDelById = roomService.getRoomDelById(Integer.parseInt(id));
        if(roomDelById != null) {
            String roomTypeName = roomTypeService.getRoomTypeName(roomDelById.getRoomTypeId());
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("typeName",roomTypeName);
            jsonObject.put("roomDelById",roomDelById);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }

    @RequestMapping("/getRoomDelByRoomNum")
    public Object getRoomDelByRoomNum(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String roomNum = request.getParameter("roomNum").trim();
        Room roomDelByRoomNum = roomService.getRoomDelByRoomNum(roomNum);
        if(roomDelByRoomNum != null) {
            String roomTypeName = roomTypeService.getRoomTypeName(roomDelByRoomNum.getRoomTypeId());
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("typeName",roomTypeName);
            jsonObject.put("roomDelByRoomNum",roomDelByRoomNum);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }
}
