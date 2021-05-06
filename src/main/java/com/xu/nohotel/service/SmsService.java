package com.xu.nohotel.service;

import org.springframework.stereotype.Service;

@Service
public interface SmsService {
//    发送验证码
    public Boolean send(String tel, String signName, String param);

//    验证验证码

}
