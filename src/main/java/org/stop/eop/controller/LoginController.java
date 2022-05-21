package org.stop.eop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.stop.eop.entity.dto.LoginDTO;
import org.stop.eop.entity.dto.RegisterDTO;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.service.ManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@Slf4j
@SuppressWarnings("rawtypes")
public class LoginController {
    private final ManageService manageService;

    LoginController(ManageService manageService) {
        this.manageService = manageService;
    }

    @PostMapping("login")
    Result login(@RequestBody @Valid LoginDTO loginDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute("user"))) {
            return Result.loginSuccess("您已经登录了,请勿重复登录");
        }
        if (manageService.login(loginDTO)) {
            session.setAttribute("user", loginDTO.getPhone());
            return Result.loginSuccess();
        }
        return Result.loginFail("登录失败 请检查");
    }

    @PostMapping("register")
    Result register(@RequestBody @Valid RegisterDTO registerDTO) {
        System.out.println(registerDTO.getName());
        return manageService.register(registerDTO) ? Result.ok("注册成功") : Result.error("注册失败,用户已存在");
    }


    @GetMapping("logout")
    Result logout(HttpServletRequest request) {
        //失效session
        request.getSession().invalidate();
        return Result.noAuth();
    }

}
