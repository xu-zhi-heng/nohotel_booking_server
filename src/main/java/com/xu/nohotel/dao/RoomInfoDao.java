package com.xu.nohotel.dao;

import com.xu.nohotel.domain.RoomInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomInfoDao {
    public RoomInfo getRoomInfoById(Integer id);
    public RoomInfo getRoomInfoByRoomNum(String roomNum);
}
