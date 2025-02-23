package io.aljavap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("myuser")
//                .password(passwordEncoder().encode("mypassword")) // Use password encoder
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//            .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    // This method defines a password encoder bean
//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll() // Allow all requests without authentication
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection for simplicity (optional)
        return http.build();
    }

    @Bean
    RestTemplate restTemplate() {
	    return new RestTemplate();
	}       
}