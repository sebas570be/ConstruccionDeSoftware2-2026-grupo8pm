package app.bank.infrastructure.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/users/**").hasRole("INTERNAL_ANALYST")
                    .requestMatchers("/clients/**").hasAnyRole("TELLER", "COMMERCIAL_EMPLOYEE", "INTERNAL_ANALYST")
                    .requestMatchers("/accounts/**").hasAnyRole("TELLER", "COMMERCIAL_EMPLOYEE", "INTERNAL_ANALYST")
                    .requestMatchers(HttpMethod.POST, "/loans/**").hasAnyRole("CLIENT_PERSON", "CLIENT_COMPANY", "COMMERCIAL_EMPLOYEE")
                    .requestMatchers(HttpMethod.PUT, "/loans/**").hasRole("INTERNAL_ANALYST")
                    .requestMatchers(HttpMethod.GET, "/loans/**").hasAnyRole("CLIENT_PERSON", "CLIENT_COMPANY", "INTERNAL_ANALYST", "COMMERCIAL_EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/transfers/**").hasAnyRole("CLIENT_PERSON", "COMPANY_EMPLOYEE")
                    .requestMatchers(HttpMethod.PUT, "/transfers/**").hasRole("COMPANY_SUPERVISOR")
                    .requestMatchers(HttpMethod.GET, "/transfers/**").hasAnyRole("CLIENT_PERSON", "COMPANY_EMPLOYEE", "COMPANY_SUPERVISOR", "INTERNAL_ANALYST")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("{\"status\":401,\"message\":\"No autenticado: se requiere un token válido\",\"errors\":null}");
                    })
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("{\"status\":403,\"message\":\"Acceso denegado: no tiene permisos para este recurso\",\"errors\":null}");
                    })
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}