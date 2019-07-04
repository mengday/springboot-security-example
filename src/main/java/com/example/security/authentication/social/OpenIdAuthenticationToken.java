package com.example.security.authentication.social;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	/** 身份 */
	private final Object principal;
	/** 服务提供商 */
	private String providerId;


	public OpenIdAuthenticationToken(Object openId, String providerId) {
		super(null);
		this.principal = openId;
		this.providerId = providerId;
		setAuthenticated(false);
	}


	public OpenIdAuthenticationToken(Object openId, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = openId;
		super.setAuthenticated(true);
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	public String getProviderId() {
		return this.providerId;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

	@Override
	public Object getCredentials() {
		return null;
	}
	
}
