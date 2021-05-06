package com.xu.nohotel.service;

import com.xu.nohotel.domain.RoomInfo;

public interface RoomInfoService {
    public RoomInfo getRoomInfoById(Integer id);
    public RoomInfo getRoomInfoByRoomNum(String roomNum);
}
