package com.xu.nohotel.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xu.nohotel.service.SmsService;
import org.springframework.stereotype.Service;


@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public Boolean send(String tel, String signName, String param) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GGM3uxssxUeRgFDAQG8", "IdiduJ7p7AsMzRyCJquempptgz4l5u");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "SuperXXA");
        request.putQueryParameter("TemplateCode", "SMS_193230032");
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();

            JSONObject jsonObject = JSONObject.parseObject(data);
            Object message = jsonObject.get("Message");
            Object code = jsonObject.get("Code");

            if (message.equals(code)){
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
