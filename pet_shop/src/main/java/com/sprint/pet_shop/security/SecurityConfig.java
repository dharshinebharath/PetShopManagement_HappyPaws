package com.sprint.pet_shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

// Core security configuration for the application.
// Sets up in-memory users for different roles (e.g., PET_ADMIN, MEDICAL, HR_ADMIN), 
// configures CORS for the frontend, and maps specific endpoints to required roles.

@Configuration
// Enables Spring Security's web security features
@EnableWebSecurity
public class SecurityConfig {

    // Configures password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //  Defines the in-memory user database for different roles
    @Bean
    public UserDetailsService userDetailsService() {

        PasswordEncoder encoder = passwordEncoder();

        UserDetails petAdmin = User.builder()
                .username("Mahakarpagam")
                .password(encoder.encode("Maha123"))
                .roles("PET_ADMIN")
                .build();

        UserDetails medical = User.builder()
                .username("Dharshine")
                .password(encoder.encode("Dharsh123"))
                .roles("MEDICAL")
                .build();

        UserDetails customerAdmin = User.builder()
                .username("Revathi")
                .password(encoder.encode("Reva123"))
                .roles("CUSTOMER_ADMIN")
                .build();

        UserDetails inventory = User.builder()
                .username("Shirlly")
                .password(encoder.encode("Shirl123"))
                .roles("INVENTORY_ADMIN")
                .build();

        UserDetails hr = User.builder()
                .username("Priyadharshini")
                .password(encoder.encode("Priya123"))
                .roles("HR_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                petAdmin, medical, customerAdmin, inventory, hr);
    }

    //  Configures Cross-Origin Resource Sharing (CORS) to allow the frontend to connect
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    //  Configures the main security filter chain to manage requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                                        .requestMatchers("/api/v1/pets/*/grooming-services").hasRole("MEDICAL")
                                        .requestMatchers("/api/v1/pets/*/grooming-services/**").hasRole("MEDICAL")
                                        .requestMatchers("/api/v1/pets/*/vaccinations").hasRole("MEDICAL")
                                        .requestMatchers("/api/v1/pets/*/vaccinations/**").hasRole("MEDICAL")
                                        .requestMatchers("/api/v1/pets/*/food").hasRole("INVENTORY_ADMIN")
                                        .requestMatchers("/api/v1/pets/*/food/**").hasRole("INVENTORY_ADMIN")
                                        .requestMatchers("/api/v1/pets/*/suppliers").hasRole("INVENTORY_ADMIN")
                                        .requestMatchers("/api/v1/pets/*/suppliers/**").hasRole("INVENTORY_ADMIN")
                                        .requestMatchers("/api/v1/employees/**").hasRole("HR_ADMIN")
                                        .requestMatchers("/api/v1/pets/**").hasAnyRole("HR_ADMIN", "PET_ADMIN")
                                        .requestMatchers("/api/v1/pets/*/employees").hasRole("HR_ADMIN")
                        .requestMatchers("/api/v1/pets/**").hasRole("PET_ADMIN")
                        .requestMatchers("/api/v1/categories/**").hasRole("PET_ADMIN")
                        .requestMatchers("/api/v1/grooming-services/**").hasRole("MEDICAL")
                        .requestMatchers("/api/v1/vaccinations/**").hasRole("MEDICAL")
                        .requestMatchers("/api/v1/customers/**").hasRole("CUSTOMER_ADMIN")
                        .requestMatchers("/api/v1/transactions/**").hasRole("CUSTOMER_ADMIN")
                        .requestMatchers("/api/v1/addresses/**").hasRole("CUSTOMER_ADMIN")
                        .requestMatchers("/api/v1/food/**").hasRole("INVENTORY_ADMIN")
                        .requestMatchers("/api/v1/suppliers/**").hasRole("INVENTORY_ADMIN")
                        .requestMatchers("/api/v1/employees/**").hasRole("HR_ADMIN")

                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

