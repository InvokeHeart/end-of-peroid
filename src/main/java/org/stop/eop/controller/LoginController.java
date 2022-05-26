package org.stop.eop.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.stop.eop.entity.dto.LoginDTO;
import org.stop.eop.entity.dto.RegisterDTO;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.service.ManageService;
import org.stop.eop.util.SessionContextUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
        String sessionId = request.getHeader("sessionId");
        if (!StringUtils.hasText(sessionId)) {
            return Result.noAuth();
        }
        SessionContextUtils instance = SessionContextUtils.getInstance();
        HttpSession session = instance.getSession(sessionId);
        log.info("login===>{}", session.getId());
        if (Objects.nonNull(session.getAttribute("user"))) {
            return Result.loginSuccess("您已经登录了,请勿重复登录");
        }

        Object code = session.getAttribute("code");
        if (Objects.nonNull(code)) {
            String scode = (String) code;
            if (!scode.equals(loginDTO.getCode())) return Result.loginFail("验证码错误");
        } else {
            return Result.loginFail("请先获取验证码");
        }

        if (manageService.login(loginDTO)) {
            session.removeAttribute("code");
            session.setAttribute("user", loginDTO.getPhone());
            return Result.loginSuccess();
        }
        return Result.loginFail("登录失败 请检查用户名密码");
    }

    @PostMapping("register")
    Result register(@RequestBody @Valid RegisterDTO registerDTO) {
        System.out.println(registerDTO.getName());
        return manageService.register(registerDTO) ? Result.ok("注册成功") : Result.error("注册失败,用户已存在");
    }

    @GetMapping("captcha")
    Result<HashMap> captcha(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // response.setContentType("image/jpeg");
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 50, 4, 150);
        //将code存入session
        HttpSession session = request.getSession();
        log.info("captcha--->{}", session.getId());
        session.setAttribute("code", captcha.getCode());
        System.out.println(captcha.getCode());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        captcha.write(stream);
        String encode = Base64.encode(stream.toByteArray());
        HashMap<String, String> map = new HashMap<>();
        map.put("code", "data:image/png;base64," + encode);
        map.put("sessionId", session.getId());
        return Result.ok(map);
    }

    @GetMapping("logout")
    Result logout(HttpServletRequest request) {
        String sessionId = request.getHeader("sessionId");
        SessionContextUtils instance = SessionContextUtils.getInstance();
        HttpSession session = instance.getSession(sessionId);
        //失效session
        // request.getSession().invalidate();
        session.invalidate();
        return Result.noAuth();
    }

}
