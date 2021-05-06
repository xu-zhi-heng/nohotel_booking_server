package com.xu.nohotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.RoomInfo;
import com.xu.nohotel.service.RoomInfoService;
import com.xu.nohotel.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/roomInfo")
public class RoomInfoController {
    @Autowired
    private RoomInfoService roomInfoService;
    @RequestMapping("/getRoomInfoById")
    public Object getRoomInfoById(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        RoomInfo roomInfoById = roomInfoService.getRoomInfoById(Integer.parseInt(id));
        if(roomInfoById != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("roomInfoById",roomInfoById);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }
    @RequestMapping("/getRoomInfoByRoomNum")
    public Object getRoomInfoByRoomNum(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String roomNum = request.getParameter("roomNum").trim();
        RoomInfo roomInfoByRoomNum = roomInfoService.getRoomInfoByRoomNum(roomNum);
        if(roomInfoByRoomNum != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("roomInfoByRoomNum",roomInfoByRoomNum);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }
}
