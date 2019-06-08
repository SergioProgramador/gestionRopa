 
 package com.finalproject.backend.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.finalproject.backend.entity.Empleados;
import com.finalproject.backend.service.IEmpleadosService;

@Component
public class InfoAddToken implements TokenEnhancer{

	@Autowired
	private IEmpleadosService iEmpleadosService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Empleados empleado = iEmpleadosService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info adicional", "Prueba de autenticacion ".concat(authentication.getName()));
		info.put("nombre", empleado.getNombre());
		info.put("apellido", empleado.getApellidos());
		info.put("email", empleado.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
	

}
