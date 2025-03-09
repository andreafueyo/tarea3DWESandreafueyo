package com.andreafueyo.tarea3DWESandreafueyo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;

@Configuration
public class SecurityConfig {

    private final AuthenticationSuccessHandler customSuccessHandler;
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/verplantas", "/registrarcliente","/iniciarsesion", "/register", "/css/**", "/js/**", "/imagenes/**", "/static/**").permitAll() 
                .requestMatchers("/menuadmin/**").hasRole("ADMIN")
                .requestMatchers("/menupersonal/**").hasRole("PERSONAL")
                .requestMatchers("/menucliente/**").hasRole("CLIENTE")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/iniciarsesion") 
                .loginProcessingUrl("/login") // URL que procesa el formulario
                .successHandler(customSuccessHandler) // Usar el handler personalizado
                .failureUrl("/iniciarsesion?error=true") // Mensaje de error
                .permitAll() 
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/iniciarsesion?logout")  
                .invalidateHttpSession(true)
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1) 
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/iniciarsesion?sessionExpired=true")
            );

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder()) // Aquí le estás diciendo que use BCrypt para comparar
                .and()
                .build();
    }
}
