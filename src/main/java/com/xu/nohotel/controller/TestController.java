package com.xu.nohotel.controller;

import com.xu.nohotel.domain.Login;
import com.xu.nohotel.service.Impl.LoginServiceImpl;
import com.xu.nohotel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/test/update")
    public void updata(HttpServletRequest request) {
        Login login = new Login();
        login.setId(1);
        login.setCode("1222");
        boolean update = loginService.update(login);
        System.out.println(update);
    }
}
