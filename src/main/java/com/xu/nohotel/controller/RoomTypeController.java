package com.xu.nohotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.RoomType;
import com.xu.nohotel.service.RoomTypeService;
import com.xu.nohotel.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.SoundbankResource;
import java.util.List;

@RestController
@RequestMapping("/roomType")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;
    @RequestMapping("/getAllRoomType")
    public Object getAllRoomType() {
        JSONObject jsonObject = new JSONObject();
        List<RoomType> allRoomType = roomTypeService.getAllRoomType();
        if(allRoomType != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("allRoomType",allRoomType);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }
}
