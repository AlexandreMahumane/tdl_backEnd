package com.mahumane.todolist.service;

import java.time.Instant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import com.mahumane.todolist.dto.LoginResponse;
import com.mahumane.todolist.dto.UsersDto;


@Service
public class TokenJwtService {

    @Autowired
    private JwtEncoder jwtEncoder;


    public LoginResponse generateToken(UsersDto request){
        var now = Instant.now();
        var expiry = 360L;

        // String scope = authentication.getAuthorities().stream().map(
        //     GrantedAuthority::getAuthority)
        //     .collect(Collectors.joining(" "));
        
        var claims = JwtClaimsSet.builder()
                        .issuer("user")
                        .subject(request.username())
                        // .claim("scope", scope)
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(expiry))
                        .build();
        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(token, expiry);
    }
}
