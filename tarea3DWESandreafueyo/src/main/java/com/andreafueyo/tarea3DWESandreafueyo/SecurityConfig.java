//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.session.HttpSessionEventPublisher;
//
//@Configuration
//public class SecurityConfig {
//
//    private final AuthenticationSuccessHandler customSuccessHandler;
//
//    public SecurityConfig(AuthenticationSuccessHandler customSuccessHandler) {
//        this.customSuccessHandler = customSuccessHandler;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/verplantas", "/iniciarsesion", "/register", "/css/**", "/js/**", "/imagenes/**", "/static/**").permitAll() 
//                .requestMatchers("/menuadmin/**").hasRole("ADMIN")
//                .requestMatchers("/menupersonal/**").hasRole("PERSONAL")
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/iniciarsesion") 
//                .permitAll() 
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/iniciarsesion?logout")  
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//            )
//            .sessionManagement(session -> session
//                .maximumSessions(1) 
//                .maxSessionsPreventsLogin(false)
//            );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }
//}
