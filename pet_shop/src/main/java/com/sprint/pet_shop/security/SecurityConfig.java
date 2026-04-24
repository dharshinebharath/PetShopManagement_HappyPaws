package com.sprint.pet_shop.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/api/v1/me").authenticated()
                        .requestMatchers("/api/v1/pets/**", "/api/v1/categories/**").hasRole("PET_ADMIN")
                        .requestMatchers("/api/v1/grooming-services/**", "/api/v1/vaccinations/**").hasRole("MEDICAL")
                        .requestMatchers("/api/v1/customers/**", "/api/v1/transactions/**", "/api/v1/addresses/**")
                        .hasRole("CUSTOMER_ADMIN")
                        .requestMatchers("/api/v1/suppliers/**", "/api/v1/food/**").hasRole("INVENTORY_ADMIN")
                        .requestMatchers("/api/v1/employees/**").hasRole("HR_ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
