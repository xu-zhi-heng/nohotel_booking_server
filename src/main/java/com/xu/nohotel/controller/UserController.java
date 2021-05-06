package com.xu.nohotel.controller;
import com.alibaba.fastjson.JSONObject;
import com.xu.nohotel.domain.Collect;
import com.xu.nohotel.domain.Login;
import com.xu.nohotel.domain.User;
import com.xu.nohotel.service.LoginService;
import com.xu.nohotel.service.UserService;
import com.xu.nohotel.utils.RandomUserName;
import com.xu.nohotel.utils.TimeDifferent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xu.nohotel.utils.Consts;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @RequestMapping("/loginByPhone")
    public Object loginByPhone(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String phoneNum = request.getParameter("phoneNum");
        String code = request.getParameter("code");
        Login login = loginService.selectByPhoneNum(phoneNum);
        if(login.getCode().equals(code)) {
            if(TimeDifferent.LessThanFive(new Date(),login.getTime())) {
                User user = userService.loadingByPhone(phoneNum);
                long time = user.getBirth().getTime();//得到的时间获取到对应的毫秒类型
                java.sql.Date resultDate = new java.sql.Date(time);//转成对应的时间类型
                user.setBirth(resultDate);
                if(user != null) {
                    jsonObject.put(Consts.CODE,1);
                    jsonObject.put(Consts.MSG,"登陆成功");
                    jsonObject.put("userMsg",user);
                }
            }else {
                jsonObject.put(Consts.CODE,2);
                jsonObject.put(Consts.MSG,"验证码不正确");
            }
        }else {
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"验证码不正确");
        }
        return jsonObject;
    }
    @RequestMapping("/sendCode")
    public Object sendCode(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String phoneNum = request.getParameter("phoneNum");
        int i = userService.sendSmsCode(phoneNum);
        if(i == -1) {
            System.out.println("用户不存在");
            jsonObject.put(Consts.CODE,-1);
            jsonObject.put(Consts.MSG,"用户不存在");
        }else if(i == 0){
            System.out.println("失败");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"发送失败");
        }else {
            System.out.println("成功");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"发送成功");
        }
        return jsonObject;
    }
    @RequestMapping("/loginByAccount")
    public Object loginByAccount(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.loginByAccount(username,password);
        long time = user.getBirth().getTime();//得到的时间获取到对应的毫秒类型
        java.sql.Date resultDate = new java.sql.Date(time);//转成对应的时间类型
        user.setBirth(resultDate);
        System.out.println(user);
        if(user != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            jsonObject.put("userMsg",user);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"登陆失败");
            return jsonObject;
        }
    }
    @RequestMapping("/loadingByPhone")
    public Object loadingByPhone(HttpServletRequest request) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        String phoneNum = request.getParameter("phoneNum");
        User user = userService.loadingByPhone(phoneNum);
        if(user != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String format = dateFormat.format(user.getBirth());
            user.setBirth(dateFormat.parse(format));
            System.out.println(user);
            jsonObject.put(Consts.CODE,1);
            jsonObject.put("userMsg",user);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
    }
    @RequestMapping("/loadingByAccount")
    public Object loadingByAccount(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        User user = userService.loadingByAccount(username);
        if(user != null) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put("userMsg",user);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
    }

    @RequestMapping("/update")
    public Object update(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex= request.getParameter("sex");
        String avator = request.getParameter("avator");
        String birth = request.getParameter("birth");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        System.out.println(birth);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(new Byte(sex));
        user.setAvator(avator);
        user.setBirth(birthDate);
        user.setEmail(email);
        user.setLocation(location);
        user.setPhoneNum(phoneNum);
        System.out.println(user);

        boolean update = userService.update(user);
        if(update) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            User user1 = userService.loadingByAccount(username);
            jsonObject.put("userMsg",user1);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        }
    }

    @RequestMapping("/insert")
    public Object insert(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String password = request.getParameter("password");
        String phoneNum = request.getParameter("phoneNum").trim();
        String code = request.getParameter("code").trim();

        Login login = loginService.selectByPhoneNum(phoneNum);
        if(login.getCode().equals(code)) {
            if(TimeDifferent.LessThanFive(new Date(),login.getTime())) {
                User user = new User();
                user.setPassword(password);
                user.setUsername(RandomUserName.getStringRandom(11));
                user.setPhoneNum(phoneNum);
                boolean insert = userService.insert(user);
                if(insert) {
                    jsonObject.put(Consts.CODE,1);
                    jsonObject.put(Consts.MSG,"注册成功");
                    jsonObject.put("userMsg",user);
                    return jsonObject;
                }else {
                    jsonObject.put(Consts.CODE,0);
                    jsonObject.put(Consts.MSG,"注册失败");
                    return jsonObject;
                }
            }else {
                jsonObject.put(Consts.CODE,2);
                jsonObject.put(Consts.MSG,"验证码不正确");
            }
        }else {
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"验证码不正确");
        }
        return jsonObject;
    }
}
