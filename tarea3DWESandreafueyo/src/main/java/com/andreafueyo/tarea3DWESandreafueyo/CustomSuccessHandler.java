package com.andreafueyo.tarea3DWESandreafueyo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.andreafueyo.tarea3DWESandreafueyo.control.MainController;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	@Lazy
	private MainController maincontroller;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Obtener el rol del usuario
		String role = authentication.getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst()
				.orElse("");

		// Redirigir según el rol
		if ("ROLE_ADMIN".equals(role)) {
			response.sendRedirect("/menuadmin"); // redirige a menú de admin
			maincontroller.setMenuLogin("menuadmin");
		} else if ("ROLE_PERSONAL".equals(role)) {
			response.sendRedirect("/menupersonal"); 
			maincontroller.setMenuLogin("menupersonal");
		} else if ("ROLE_CLIENTE".equals(role)){
			response.sendRedirect("/menucliente"); 
			maincontroller.setMenuLogin("menucliente");
		}else {
			response.sendRedirect("/iniciarsesion");
		}
	}
}

