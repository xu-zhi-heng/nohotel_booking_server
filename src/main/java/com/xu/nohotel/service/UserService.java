package com.xu.nohotel.service;

import com.xu.nohotel.domain.User;

public interface UserService {
    public User loginByPhone(String phoneNum);
    public User loginByAccount(String username,String password);
    public User loadingByPhone(String phoneNum);
    public User loadingByAccount(String username);
    public boolean update(User user);
    public boolean insert(User user);
    int sendSmsCode(String phoneNum);
    public boolean updateFaceToken(User user);
}
