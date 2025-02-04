package com.example.smartBar.smartBar.config;

import com.example.smartBar.smartBar.security.JwtAuthenticationEntryPoint;
import com.example.smartBar.smartBar.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
//
    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().permitAll() // Permit all requests temporarily
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/api/auth/**").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/api/menu-items", "/api/menu-items/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET,"/api/payment-methods").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/api/admin-area/register-admin").permitAll();
                    authorize.requestMatchers(HttpMethod.GET,"/api/orders/get-all-order-numbers").permitAll();
                    authorize.requestMatchers(
                            "/v2/api-docs",             // Swagger API documentation
                            "/swagger-resources/**",    // Swagger resources
                            "/swagger-ui/**",           // Swagger UI
                            "/webjars/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}

//   .requestMatchers("/swagger-ui/**",
//           "/swagger-resources/*",
//           "/v3/api-docs/**")