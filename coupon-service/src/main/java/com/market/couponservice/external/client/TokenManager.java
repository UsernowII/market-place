package com.market.couponservice.external.client;

import com.market.couponservice.external.client.interfaces.AuthService;
import com.market.couponservice.external.client.interfaces.TokenManagerService;
import com.market.couponservice.external.response.TokenResponse;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenManager implements TokenManagerService {

    private final AuthService authService;
    private String accessToken = "APP_USR-3755041179926330-040915-5ece9b375c6ebd874999b97dd54fcc41-32729035";
    private String refreshToken = "TG-67f6cb548905050001d12fda-32729035";
    private long tokenExpirationTime;

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
        log.info("Token set Expiration time: " + tokenExpirationTime);
    }
}
