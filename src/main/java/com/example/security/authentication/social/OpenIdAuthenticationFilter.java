package com.example.security.authentication.social;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
		private boolean postOnly = true;


		public OpenIdAuthenticationFilter(String loginProcessUrlOpenId) {
			super(new AntPathRequestMatcher(loginProcessUrlOpenId, "POST"));
		}


		@Override
		public Authentication attemptAuthentication(HttpServletRequest request,
                                                    HttpServletResponse response) throws AuthenticationException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
			}

			// 封装OpenIdAuthenticationToken
			String openId = obtainOpenId(request);
			String providerId = obtainProviderId(request);
			OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);
			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			// 开始认证
			return this.getAuthenticationManager().authenticate(authRequest);
		}


		protected String obtainOpenId(HttpServletRequest request) {
			String openId = request.getParameter("openId");
			if (openId == null) {
				openId = "";
			}

			openId = openId.trim();
			return openId;
		}
		protected String obtainProviderId(HttpServletRequest request) {
			String providerId = request.getParameter("providerId");
			providerId = providerId.trim();
			return providerId;
		}


		protected void setDetails(HttpServletRequest request,
                                  OpenIdAuthenticationToken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}


		public void setPostOnly(boolean postOnly) {
			this.postOnly = postOnly;
		}
}
