package com.market.couponservice.external.client;

import com.market.couponservice.config.MeliProperties;
import com.market.couponservice.exception.AuthenticationException;
import com.market.couponservice.external.client.interfaces.AuthService;
import com.market.couponservice.external.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final MeliProperties meliProperties;
    private static final String TOKEN_URL = "https://api.mercadolibre.com/oauth/token";

    @Override
    public TokenResponse authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        System.out.println( meliProperties.getClientId());
        System.out.println( meliProperties.getClientSecret());
        System.out.println( meliProperties.getCode());
        System.out.println( meliProperties.getRedirectUri());
        System.out.println( meliProperties.getCodeVerifier());

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", meliProperties.getClientId());
        params.put("client_secret", meliProperties.getClientSecret());
        params.put("code", meliProperties.getCode());
        params.put("redirect_uri", meliProperties.getRedirectUri());
        params.put("code_verifier", meliProperties.getCodeVerifier());

        HttpEntity<String> request = new HttpEntity<>(buildFormData(params), headers);

        ResponseEntity<TokenResponse> response;
        try {
            response = restTemplate.exchange(
                    TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    TokenResponse.class
            );
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException();
        }

        return response.getBody();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "refresh_token");
        params.put("client_id", meliProperties.getClientId());
        params.put("client_secret", meliProperties.getClientSecret());
        params.put("refresh_token", refreshToken);

        HttpEntity<String> request = new HttpEntity<>(buildFormData(params), headers);
        ResponseEntity<TokenResponse> response;

        try {
            response = restTemplate.exchange(
                    TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    TokenResponse.class
            );
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException();
        }


        return response.getBody();
    }

    private String buildFormData(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        params.forEach((key, value) -> builder.append(key).append('=').append(value).append('&'));
        // Remove last "&"
        if (builder.length() > 0) builder.setLength(builder.length() - 1);
        return builder.toString();
    }
}
