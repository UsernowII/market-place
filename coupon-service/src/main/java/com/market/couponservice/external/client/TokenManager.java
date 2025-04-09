package com.market.couponservice.external.client;

import com.market.couponservice.external.client.interfaces.AuthService;
import com.market.couponservice.external.client.interfaces.TokenManagerService;
import com.market.couponservice.external.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenManager implements TokenManagerService {

    private final AuthService authService;

    private String accessToken = "APP_USR-3755041179926330-040912-ae07034b5137cbcc4d169e44e4a6c883-32729035";
    private String refreshToken = "TG-67f6a5e961ee06000129d518-32729035";
    private long tokenExpirationTime = 10000;

    @Override
    public synchronized String getAccessToken() {
        if (accessToken == null ) {
            TokenResponse response = authService.authenticate();
            setTokens(response);
        }
        if ( System.currentTimeMillis() >= tokenExpirationTime) {
            TokenResponse response = authService.refreshToken(refreshToken);
            setTokens(response);
        }
        return accessToken;
    }


    private void setTokens(TokenResponse tokenData) {
        this.accessToken = tokenData.getAccessToken();
        this.refreshToken = tokenData.getRefreshToken();
        this.tokenExpirationTime = System.currentTimeMillis() + (tokenData.getExpiresIn() - 60) * 1000L;
    }
}
