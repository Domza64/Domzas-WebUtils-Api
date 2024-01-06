package xyz.domza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    // TODO - Implement JWT tokens or something similar for auth

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // TODO - Separate sec config for converter, webspace, qrGen...
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("*")); // TODO - don't allow all origins? - https://converter.domza.xyz
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    config.setExposedHeaders(Collections.singletonList("X-Filename"));
                    return config;
                }))
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/domza/submitGuestBookComment", "/domza/updateDiaryArticle", "/domza/deleteArticle/**")) // TODO - decide what to do with csrf for /updateDiaryArticle
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/domza/updateDiaryArticle", "/domza/deleteArticle/**").hasRole("ADMIN"))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/converter/getVideo", "/domza/guestBookComments", "/domza/submitGuestBookComment", "/domza/diaryArticles", "/qrGen/getQRCode").permitAll());
        return http.build();
    }
}
