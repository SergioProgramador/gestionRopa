package com.finalproject.backend.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalproject.backend.entity.Empleados;
import com.finalproject.backend.entity.Roles;
import com.finalproject.backend.repository.EmpleadosRepository;
import com.finalproject.backend.repository.RolesRepository;
import com.finalproject.backend.service.IEmpleadosService;

@Service("empleadoService")
public class EmpleadoServiceImpl implements IEmpleadosService, UserDetailsService{ //IMPLEMENTAMOS LA INTERFAZ DE SPRING SECURITY PARA TRABAJAR CON JPA

	private Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
	
	@Autowired
	@Qualifier("empleadosRepository")
	private EmpleadosRepository empleadosRepository; 
	
	@Autowired
	@Qualifier("rolesRepository")
	private RolesRepository rolesRepository;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleados empleados = empleadosRepository.findByUsername(username);
		
		if(empleados == null) {
			logger.error("No existe el empleado: '"+ username +"' en la base de datos.");
			throw new UsernameNotFoundException("Error. No existe el ususario '"+username+"'.");
		}
		
		List<GrantedAuthority> authorities = empleados.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
		
		return new User(empleados.getUsername(), empleados.getPassword(), empleados.getEnabled(), true, true, true, authorities);
	}

	
	@Override
	@Transactional(readOnly=true)
	public Empleados findByUsername(String username) {
		return empleadosRepository.findByUsername(username);
	}
	
	public Empleados addEmpleado(Empleados empleado) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		Date date = new Date();
		empleado.setPassword(pe.encode(empleado.getPassword()));
		empleado.setEnabled(true);
		empleado.setCreate_time(date);
		Roles roles = rolesRepository.findByRole("ROLE_USER");
		List<Roles> listRoles = new ArrayList<>();
		listRoles.add(roles);
		empleado.setRoles(listRoles);
		Empleados nuevoEmpleado = empleadosRepository.save(empleado);
		return nuevoEmpleado;			
	}
	
	public Iterable<Empleados> listAllEmpleados() {
		Iterable<Empleados> empleados= empleadosRepository.findAll();	
		return empleados;
	}

	public void removeEmpleados(String username) {
		Empleados empleados = empleadosRepository.findByUsername(username);
		if(null != empleados){
			empleadosRepository.delete(empleados);
		}
		
	}

}
