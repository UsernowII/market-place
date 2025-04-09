package com.market.couponservice.external.client.interfaces;

import com.market.couponservice.external.response.TokenResponse;

public interface AuthService {

    TokenResponse authenticate();
    TokenResponse refreshToken(String refreshToken);
}
