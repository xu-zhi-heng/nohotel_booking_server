package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.LoginDao;
import com.xu.nohotel.domain.Login;
import com.xu.nohotel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public boolean update(Login login) {
        return loginDao.update(login) > 0;
    }

    @Override
    public boolean insert(Login login) {
        return loginDao.insert(login) > 0;
    }

    @Override
    public Login selectByPhoneNum(String phoneNum) {
        return loginDao.selectByPhoneNum(phoneNum);
    }
}
