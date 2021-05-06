package com.xu.nohotel.service;

import com.xu.nohotel.domain.Room;
import java.util.List;

public interface RoomService {
    public List<Room> getRoomByType(Integer type);
    public Room getRoomDelById(Integer id);
    public Room getRoomDelByRoomNum(String roomNum);
}
