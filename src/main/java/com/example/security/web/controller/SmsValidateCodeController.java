package com.example.security.web.controller;

import com.example.security.model.SmsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * summary.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2019-05-17
 */
@RestController
public class SmsValidateCodeController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/code/sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SmsCode createCode(@RequestHeader("deviceId") String deviceId, String mobile) {
        SmsCode smsCode = createSmsCode();
        System.out.println("验证码发送成功：" + smsCode);

        String key = "code:sms:"+ deviceId;
        stringRedisTemplate.opsForValue().set(key, smsCode.getCode());

        return smsCode;
    }

    private SmsCode createSmsCode() {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return new SmsCode(code, 30000);
    }
}
