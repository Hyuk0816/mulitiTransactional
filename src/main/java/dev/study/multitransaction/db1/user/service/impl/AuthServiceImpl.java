package dev.study.multitransaction.db1.user.service.impl;

import dev.study.multitransaction.db1.user.constants.AuthConstants;
import dev.study.multitransaction.db1.user.model.dto.LoginDto;
import dev.study.multitransaction.db1.user.repository.UserRepository;
import dev.study.multitransaction.db1.user.service.AuthService;
import dev.study.multitransaction.db1.user.userDetails.CustomUserDetails;
import dev.study.multitransaction.util.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final long maxAgeForCookie = 7 * 24 * 60 * 60;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final CustomUserDetails customUserDetails;

    @Override
    @Transactional
    public void login(LoginDto loginDto, HttpServletResponse response) {

        userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(AuthConstants.MESSAGE_404));

        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.createAccessToken(authentication);
            String refreshToken = tokenProvider.createRefreshToken(authentication);

            response.addHeader("Authorization", "Bearer " + accessToken);

            tokenProvider.createRefreshTokenCookie(response, "refreshToken", refreshToken, maxAgeForCookie);

        }catch(BadCredentialsException e){
            throw new BadCredentialsException(AuthConstants.MESSAGE_401);
        }
    }



}
