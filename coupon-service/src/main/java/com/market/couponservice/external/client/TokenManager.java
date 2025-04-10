package com.market.couponservice.external.client;

import com.market.couponservice.external.client.interfaces.AuthService;
import com.market.couponservice.external.client.interfaces.TokenManagerService;
import com.market.couponservice.external.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenManager implements TokenManagerService {

    private final AuthService authService;
    @Value("${meli.access-token}")
    private String accessToken;
    @Value("${meli.refresh-token}")
    private String refreshToken;
    private long tokenExpirationTime = 0;

    @Override
    public synchronized String getAccessToken() {
        if (accessToken == null ) {
            TokenResponse response = authService.authenticate();
            log.info("Generate access token: {}", response);
            setTokens(response);
        }
        if ( System.currentTimeMillis() >= tokenExpirationTime) {
            TokenResponse response = authService.refreshToken(refreshToken);
            log.info("Generate refresh token: {}", response);
            setTokens(response);
        }
        return accessToken;
    }


    private void setTokens(TokenResponse tokenData) {
        this.accessToken = tokenData.getAccessToken();
        this.refreshToken = tokenData.getRefreshToken();
        this.tokenExpirationTime = System.currentTimeMillis() + (tokenData.getExpiresIn() - 60) * 1000L;
        log.info("Token set Expiration time: {}", tokenExpirationTime);
    }
}
