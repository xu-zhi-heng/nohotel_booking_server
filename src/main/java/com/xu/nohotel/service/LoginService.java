package com.xu.nohotel.service;

import com.xu.nohotel.domain.Login;

public interface LoginService {
    public boolean update(Login login);
    public boolean insert(Login login);
    public Login selectByPhoneNum(String phoneNum);
}
