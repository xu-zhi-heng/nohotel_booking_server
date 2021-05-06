package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.UserDao;
import com.xu.nohotel.domain.Login;
import com.xu.nohotel.domain.User;
import com.xu.nohotel.service.LoginService;
import com.xu.nohotel.service.SmsService;
import com.xu.nohotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SmsService smsService;
    @Autowired
    private LoginService loginService;
    @Override
    public User loginByPhone(String phoneNum) {
        return userDao.loginByPhone(phoneNum);
    }

    @Override
    public User loginByAccount(String username,String passwrod) {
        return userDao.loginByAccount(username,passwrod);
    }

    @Override
    public User loadingByPhone(String phoneNum) {
        return userDao.loadingByPhone(phoneNum);
    }

    @Override
    public User loadingByAccount(String username) {
        return userDao.loadingByAccount(username);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user) > 0;
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean updateFaceToken(User user) {
        return userDao.updateFaceToken(user) > 0;
    }

    @Override
    public int sendSmsCode(String phoneNum) {
        User user = userDao.loadingByPhone(phoneNum);
        if(user == null) {
            return -1;
        }else {
            Login oldLogin = loginService.selectByPhoneNum(phoneNum);
            if(oldLogin != null) {
                String code = "" + (int) (1000 + Math.random() * 8999);
                Boolean isSend = smsService.send(phoneNum, "", "{\"code\":\"" + code + "\"}");
                if (isSend) {
                    System.out.println("更新");
                    Login login = new Login();
                    login.setCode(code);
                    login.setPhoneNum(phoneNum);
                    boolean update = loginService.update(login);
                    if(update) {
                        return 1;
                    }else {
                        return 0;
                    }
                }else {
                    return 0;
                }
            }else {
                String code = "" + (int) (1000 + Math.random() * 8999);
                Boolean isSend = smsService.send(phoneNum, "", "{\"code\":\"" + code + "\"}");
                if(isSend) {
                    System.out.println("添加");
                    Login login = new Login();
                    login.setCode(code);
                    login.setPhoneNum(phoneNum);
                    boolean insert = loginService.insert(login);
                    if(insert) {
                        return 1;
                    }else {
                        return 0;
                    }
                }else {
                    return 0;
                }
            }
        }
    }
}
