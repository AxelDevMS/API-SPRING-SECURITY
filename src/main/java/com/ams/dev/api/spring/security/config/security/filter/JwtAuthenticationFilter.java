package com.ams.dev.api.spring.security.config.security.filter;

import com.ams.dev.api.spring.security.exception.ObjectNotFoundException;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import com.ams.dev.api.spring.security.service.UserService;
import com.ams.dev.api.spring.security.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. obetener encabezado HTTP Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //2. Obtener el token desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        //3. Obtener el subject/username desde el token, esta acción va validar el token,el formato del token, firma y fecha de expiración
        String username = jwtService.extractUsername(jwt);

        //4. Setear objecto authentication dentro de security context holder
        UserEntity user = this.userService.findByUsername(username)
                .orElseThrow(()-> new ObjectNotFoundException("User not found, Username: "+ username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username,null,user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);


        //5. ejecutar el resto de filtros
        filterChain.doFilter(request,response);
    }
}
