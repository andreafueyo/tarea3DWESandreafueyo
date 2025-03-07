//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//
//        String redirectUrl = authentication.getAuthorities().stream()
//                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN")) ? "/menuadmin" : "/menupersonal";
//
//        response.sendRedirect(redirectUrl);
//    }
//}
//
