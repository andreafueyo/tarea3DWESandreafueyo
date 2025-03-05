//package com.andreafueyo.tarea3DWESandreafueyo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.andreafueyo.tarea3DWESandreafueyo.modelo.Credenciales;
//import com.andreafueyo.tarea3DWESandreafueyo.servicios.ServiciosCredenciales;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	ServiciosCredenciales crServ;
//
//    public CustomUserDetailsService(ServiciosCredenciales crServ) {
//        this.crServ = crServ;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	Credenciales credencial = crServ.findByUsuario(username);
//        if (credencial == null) {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//
//        
//
//        return User.builder()
//                .username(credencial.getUsuario())
//                .password(credencial.getPassword())
//                .roles(credencial.getRol())
//                .build();
//    }
//}
//
