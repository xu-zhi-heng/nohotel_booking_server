package com.xu.nohotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.Collect;
import com.xu.nohotel.service.CollectService;
import com.xu.nohotel.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @RequestMapping("/insert")
    public Object insert(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();
        String roomId = request.getParameter("roomId").trim();
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setRoomId(Integer.parseInt(roomId));
        Collect like = collectService.isLike(collect);
        if(like != null) {
            return null;
        }
        boolean insert = collectService.insert(collect);
        if(insert) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏失败");
            return jsonObject;
        }
    }

    @RequestMapping("/select")
    public Object select(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();
        Collect collect = new Collect();
        List<Collect> byUserId = collectService.findByUserId(Integer.parseInt(userId));
        if(byUserId != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            jsonObject.put("collects",byUserId);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }

    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();
        String roomId = request.getParameter("roomId").trim();
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setRoomId(Integer.parseInt(roomId));
        boolean delete = collectService.delete(collect);
        if(delete) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"取消收藏成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"失败");
            return jsonObject;
        }
    }

    @RequestMapping("/isLike")
    public Object isLike(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();
        String roomId = request.getParameter("roomId").trim();
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setRoomId(Integer.parseInt(roomId));
        Collect like = collectService.isLike(collect);
        if(like != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"喜欢");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"不喜欢");
            return jsonObject;
        }
    }
}
