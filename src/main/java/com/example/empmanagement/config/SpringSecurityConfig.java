package com.example.empmanagement.config;

import com.example.empmanagement.security.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.anonymous;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    JwtTokenFilter jwtTokenFilter;
    private final CorsFilter corsFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(corsFilter, SessionManagementFilter.class);
//        httpSecurity.csrf(csrf->csrf.ignoringRequestMatchers("api/employees/**","api/todos/**"));
        httpSecurity.csrf(csrf->csrf.disable());
//        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.anonymous(anonymous->anonymous.disable());
        httpSecurity.authorizeHttpRequests(requests ->
                        requests.requestMatchers("api/auth/**").permitAll()
                                .requestMatchers("swagger-ui/**", "/resources/**", "/static/**", "v3/api-docs/**").permitAll() //Swagger API
                                .anyRequest().authenticated());
        httpSecurity.sessionManagement(session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
