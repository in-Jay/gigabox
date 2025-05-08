package com.example.gigabox.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserSecurityService userSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // csrfTokenRepository 설정  // XSRF-TOKEN으로 설정
                        .ignoringRequestMatchers("/", "/reservation/**", "/user/login", "/user/signup", "/user/**", "/checkpass/**", "/cart/**", "/check/**", "/send/**", "/faq/**", "/qna/**", "/theater.html**", "/support/home", "/check/email", "/user/reset_pass", "/send/password", "/send/id", "/store", "/store/detail/**", "/support/support_list", "/event")
                ) // csrf 설정 종료
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/store", "/movie/**", "/user/login", "/user/signup", "/css/**", "/js/**", "/img/**", "/event").permitAll() // 공개 URL
                        .requestMatchers("/theater.html", "/theater/**", "/user/username_check", "/checkpass/**").permitAll() // 공개 URL
                        .requestMatchers("/support/home", "/faq/**", "/qna/**", "/check/email", "/user/reset_pass", "/send/password", "/send/id", "/support/support_list").permitAll()
                        .requestMatchers("/mypage/**", "/user/modify", "/reservation/**", "/store/detail/**", "/cart/**").authenticated() // 인증이 필요한 URL
                        .anyRequest().authenticated() // 나머지 모든 URL 인증 필요
                ) // authorizeHttpRequests 설정 종료
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .failureUrl("/user/login?error=true")
                        .defaultSuccessUrl("/", true)
                        .successHandler(new CustomAuthenticationSuccessHandler())
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/user/login")
                        .invalidateHttpSession(true)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/user/login") // 세션이 만료되었을 경우 로그인 페이지로 리디렉션
                        .maximumSessions(1)
                        .expiredUrl("/user/login") // 세션이 만료된 경우 로그인 페이지로 리디렉션
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 요청에 대해 CORS 허용
                        .allowedOrigins("http://localhost:8080") // 허용할 출처를 지정 (CORS 요청을 허용할 도메인)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드 지정
                        .allowedHeaders("Content-Type", "X-CSRF-TOKEN", "Authorization"); // 허용할 헤더 지정
            }
        };
    }
}
