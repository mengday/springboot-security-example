package com.example.security;

import com.example.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.example.security.authentication.social.OpenIdAuthenticationSecurityConfig;
import com.example.security.authentication.social.SocialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * summary.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2019-06-26
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .formLogin()
                    .loginPage("/authentication/form")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/authentication/form", "/authentication/mobile", "/authentication/openId", "/code/sms", "/oauth/authorize", "/social/signUp").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
}
