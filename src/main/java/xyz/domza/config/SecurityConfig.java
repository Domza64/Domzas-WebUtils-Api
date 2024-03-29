package xyz.domza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // TODO - Separate sec config for converter, webspace, qrGen...
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());

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
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/domza/submitGuestBookComment", "/domza/admin/**")) // TODO - decide what to do with csrf for /updateDiaryArticle
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/domza/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/converter/getVideo", "/domza/guestBookComments", "/domza/submitGuestBookComment", "/domza/diaryArticles", "/qrGen/getQRCode").permitAll());
        return http.build();
    }
}
