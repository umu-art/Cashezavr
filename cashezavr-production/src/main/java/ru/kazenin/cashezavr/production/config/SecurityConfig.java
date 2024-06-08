package ru.kazenin.cashezavr.production.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

//    @Bean
//    public AuthenticationProvider authProvider(UserDetailsServiceImpl userDetailsService,
//                                               PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return authProvider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http/*,
                                                   AuthenticationProvider authenticationProvider*/) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/cashezavr/core/**").authenticated()
                        .requestMatchers("/cashezavr/register").permitAll()
                        .requestMatchers("/cashezavr/try").authenticated()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/actuator").permitAll()
                        .anyRequest().denyAll())
//                .authenticationProvider(authenticationProvider)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
