package com.ams.dev.api.spring.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //activa y configura el authentication cofiguration
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //permite proteger las rutas del pryecto
       SecurityFilterChain filterChain = http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig -> sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //que tipo de sesión se va tener
                .authenticationProvider(authenticationProvider) //estartaegia de autenticación que se va utlizar
                .authorizeHttpRequests(authReqConfig -> {
                   authReqConfig.requestMatchers(HttpMethod.POST,"/customers/save").permitAll();
                   authReqConfig.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();
                   authReqConfig.anyRequest().authenticated();
                })//configura la rutas publica y privadas
                .build();//crear un defaults security chain

        return filterChain;
    }

}
