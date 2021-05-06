package com.xu.nohotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.Order;
import com.xu.nohotel.domain.Room;
import com.xu.nohotel.service.OrderService;
import com.xu.nohotel.service.RoomService;
import com.xu.nohotel.service.RoomTypeService;
import com.xu.nohotel.utils.Consts;
import com.xu.nohotel.utils.RandomUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;
    @RequestMapping("/insert")
    public Object insert(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();   // 用户的id
        String daysNum = request.getParameter("daysNum").trim(); // 总天数
        String roomType = request.getParameter("roomType");      // 房间的类型--房间类型名称
        String roomNum = request.getParameter("roomNum");        // 房间号
        String totalPrice = request.getParameter("totalPrice");  // 总价格
        String state = request.getParameter("state");            // 状态
        String note = request.getParameter("note");              // 备注
        String faceToken = request.getParameter("faceToken").trim();
        String title = RandomUserName.getStringRandom(10) + daysNum + userId;   // 生成订单号


        Order order = new Order();
        order.setUserId(Integer.parseInt(userId));
        order.setDaysNum(Integer.parseInt(daysNum));
        order.setRoomNum(roomNum);
        order.setNote(note);
        order.setFaceToken(faceToken);
        order.setTitle(title);
        order.setState(Integer.parseInt(state));
        order.setTotalPrice(Double.parseDouble(totalPrice));
        order.setRoomId(roomTypeService.getRoomTypeId(roomType));

        System.out.println(order.toString());

        boolean insert = orderService.insert(order);

        if(insert) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"预订成功");
            Order orderByTitle = orderService.findOrderByTitle(title);
            jsonObject.put("order",orderByTitle);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"预订失败");
            return jsonObject;
        }
    }

    @RequestMapping("/getAllOrderByUserId")
    public Object getAllOrderByUserId(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId").trim();
        List<Order> allOrderByUserId = orderService.findAllOrderByUserId(Integer.parseInt(userId));
        if(allOrderByUserId != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"查询成功");
            List<Room> rooms = new ArrayList<>();
            for (Order order : allOrderByUserId) {
                Room roomDelByRoomNum = roomService.getRoomDelByRoomNum(order.getRoomNum());
                rooms.add(roomDelByRoomNum);
            }
            jsonObject.put("rooms",rooms);
            jsonObject.put("allOrders",allOrderByUserId);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
    }
}
