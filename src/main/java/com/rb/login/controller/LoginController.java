package com.rb.login.controller;

import com.rb.login.constant.OauthProperty;
import com.rb.login.model.entity.LoginRecord;
import com.rb.login.service.UserService;
import com.rb.login.util.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    private final OkHttpClientUtil okHttpClientUtil = new OkHttpClientUtil();

    @PostMapping
    public String login() {
        return "hello word";
    }

    @GetMapping("/info")
    @ResponseStatus(value = HttpStatus.OK)
    public List<LoginRecord> findAllResources() {
        return userService.findAllLoginInfo();
    }

    /**
     * github回调授权码code，
     * 拼接获取access_token
     * 用token令牌去获取用户信息
     * @return
     */
    @GetMapping("/redirect")
    public ModelAndView oauth2(@NotEmpty String code) {
        log.info("授权码code：{}", code);
        //回到login主页
        String redirectHome = "http://localhost:8351/main.html";
        try {
            //获取token的url
            String tokenURL = OauthProperty.token_url + "?" + OauthProperty.clientId + "&"
                    + OauthProperty.clientSecret + "&"
                    + "code=" + code;
            //获取token返回结果
            String result = okHttpClientUtil.httpGet(tokenURL);
            log.info("token请求结果：{}", result);
            //以&符号切分原始token结果，获取第一段真正的token令牌
            String accessToken = null;
            Pattern p = Pattern.compile("=(\\w+)&");
            Matcher m = p.matcher(result);
            while (m.find()) {
                accessToken = m.group(1);
                log.info("令牌token：{}", m.group(1));
                break;
            }
            //利用获取到的token令牌去github获取用户信息
            String userInfoUrl = OauthProperty.user_url + "?access_token=" + accessToken;
            String userResult = okHttpClientUtil.httpGet(userInfoUrl);
            System.out.println(userResult);
            log.info("用户信息：{}", userResult);
            redirectHome += "?tokenId=" + accessToken;
            System.out.println();
        } catch (Exception e) {
            log.error("授权回调异常={}", e);
        }
        return new ModelAndView(new RedirectView(redirectHome));
    }
}
