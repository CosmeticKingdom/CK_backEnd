package com.ck.backend.config;

import com.ck.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtBlacklist jwtBlacklist;

    @Bean
    public UserDetailsService userDetailsService() {
        return loginId -> {
            logger.info("Attempting to load user by loginId: {}", loginId);
            return userRepository.findByLoginId(loginId)
                    .map(user -> {
                        logger.info("User found: {}", user.getLoginId());
                        Collection<? extends GrantedAuthority> authorities = Arrays.stream(user.getRole().split(","))
                                .map(role -> {
                                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
                                    logger.info("Assigning authority for {}: {}", user.getLoginId(), grantedAuthority);
                                    return grantedAuthority;
                                })
                                .collect(Collectors.toList());
                        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), authorities);
                    })
                    .orElseThrow(() -> {
                        logger.warn("User not found with loginId: {}", loginId);
                        return new UsernameNotFoundException("User not found with loginId: " + loginId);
                    });
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (API 서버의 경우 일반적으로 비활성화)
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(unauthorizedEntryPoint()) // 인증되지 않은 사용자 접근 시 처리
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안함
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**").permitAll() // 회원가입 및 로그인 경로는 모든 사용자에게 허용
                .requestMatchers(HttpMethod.GET, "/massages/**").permitAll() // 마사지 조회는 모든 사용자에게 허용
                .requestMatchers("/reservations/**").authenticated() // 예약 관련 경로는 인증 필요
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        };
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService(), jwtBlacklist);
    }
}
