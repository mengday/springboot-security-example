package com.example.security;

import com.example.security.authentication.social.AppSignupUtils;
import com.example.security.authentication.social.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * summary.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2019-05-25
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignupUtils appSignUpUtils;


    @PostMapping("/user/regist")
    public void regist(User user, HttpServletRequest request) {
        // 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        String userId = user.getUsername();
        // 浏览器注册用providerSignInUtils
		// providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        // app注册用AppSignUpUtils
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    @GetMapping(value = "/user/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String me(Authentication auth, HttpServletRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = header.split("bearer ")[1];
        Claims claims = Jwts.parser().setSigningKey("myJwtKey".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String foo = (String) claims.get("foo");

        System.out.println(foo);

        return objectMapper.writeValueAsString(auth);
    }
}
