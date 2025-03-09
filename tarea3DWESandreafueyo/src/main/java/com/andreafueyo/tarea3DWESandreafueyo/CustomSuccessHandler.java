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
			response.sendRedirect("/menuadmin"); // Redirige a menú de admin
			System.out.print("rol admin");
			maincontroller.setMenuLogin("menuadmin");
		} else if ("ROLE_PERSONAL".equals(role)) {
			response.sendRedirect("/menupersonal"); // Redirige a menú de personal
			System.out.print("rol personal");
			maincontroller.setMenuLogin("menupersonal");
		} else if ("ROLE_CLIENTE".equals(role)){
			response.sendRedirect("/menucliente"); // Redirige a menú de personal
			System.out.print("rol cliente"
					+ "32");
			maincontroller.setMenuLogin("menucliente");
		}else {
			response.sendRedirect("/iniciarsesion"); // Redirige a una página por defecto
		}
	}
}

