//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/session")
//public class SessionController {
//
//    @GetMapping("/info")
//    public String obtenerInformacionDeSesion(HttpSession session, @AuthenticationPrincipal User user) {
//        return "Usuario autenticado: " + user.getUsername() + " | ID de Sesión: " + session.getId();
//    }
//}
//
