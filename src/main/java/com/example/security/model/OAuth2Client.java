package com.example.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * summary.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2019-07-03
 */
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OAuth2Client {
    private String clientId;
    private String clientSecret;
    private int accessTokenValiditySeconds;

}
