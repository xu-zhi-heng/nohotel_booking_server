package com.xu.nohotel.dao;

import com.xu.nohotel.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao {
    public List<Room> getRoomByType(Integer type);
    public Room getRoomDelById(Integer id);
    public Room getRoomDelByRoomNum(String roomNum);
}
