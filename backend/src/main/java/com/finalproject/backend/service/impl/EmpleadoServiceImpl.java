package com.finalproject.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalproject.backend.entity.Empleados;
import com.finalproject.backend.repository.EmpleadosRepository;

@Service("empleadoService")
public class EmpleadoServiceImpl implements UserDetailsService{ //IMPLEMENTAMOS LA INTERFAZ DE SPRING SECURITY PARA TRABAJAR CON JPA

	@Autowired
	@Qualifier("empleadosRepository")
	private EmpleadosRepository empleadosRepository; 
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleados empleados = empleadosRepository.findByUsername(username);
		
		List<GrantedAuthority> roles = empleados.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
		
		return new User(empleados, empleados.getPassword(), empleados.getEnabled(), true, true, true, roles);
	}

}
