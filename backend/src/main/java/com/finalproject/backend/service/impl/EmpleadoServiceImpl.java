package com.finalproject.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.finalproject.backend.service.IEmpleadosService;

@Service("empleadoService")
public class EmpleadoServiceImpl implements IEmpleadosService, UserDetailsService{ //IMPLEMENTAMOS LA INTERFAZ DE SPRING SECURITY PARA TRABAJAR CON JPA

	private Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
	
	@Autowired
	@Qualifier("empleadosRepository")
	private EmpleadosRepository empleadosRepository; 
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleados empleados = empleadosRepository.findByUsername(username);
		
		if(empleados == null) {
			logger.error("No existe el empleado: '"+ username +"' en la base de datos.");
			throw new UsernameNotFoundException("Error. No existe el ususario '"+username+"'.");
		}
		
		List<GrantedAuthority> roles = empleados.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
		
		return new User(empleados.getUsername(), empleados.getPassword(), empleados.getEnabled(), true, true, true, roles);
	}

	
	@Override
	@Transactional(readOnly=true)
	public Empleados findByUsername(String username) {
		return empleadosRepository.findByUsername(username);
	}

}
