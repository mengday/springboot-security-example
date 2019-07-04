package com.example.security.authentication.social;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class SocialUserInfo {
	private String providerId;
	
	private String providerUserId;
	
	private String nickname;
	
	private String headimg;
}
