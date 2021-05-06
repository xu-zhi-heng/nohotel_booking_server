package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.RoomInfoDao;
import com.xu.nohotel.domain.RoomInfo;
import com.xu.nohotel.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    @Autowired
    private RoomInfoDao roomInfoDao;
    @Override
    public RoomInfo getRoomInfoById(Integer id) {
        return roomInfoDao.getRoomInfoById(id);
    }

    @Override
    public RoomInfo getRoomInfoByRoomNum(String roomNum) {
        return roomInfoDao.getRoomInfoByRoomNum(roomNum);
    }
}
