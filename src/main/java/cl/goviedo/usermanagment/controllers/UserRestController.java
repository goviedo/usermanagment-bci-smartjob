package cl.goviedo.usermanagment.controllers;


import javax.validation.Valid;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.goviedo.usermanagment.entities.UserEntity;
import cl.goviedo.usermanagment.services.JwtUtil;
import cl.goviedo.usermanagment.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping(path = "user",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
@Api(value = "API Usuarios", description = "API para la gestion de usuarios")
public class UserRestController {
    UserService us;
    JwtUtil jwtUtil;
    final AuthenticationProvider authenticationManager;
    
    public UserRestController(UserService us, JwtUtil jwtUtil, AuthenticationProvider am) {
        this.us = us;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = am;   
    }

	@CrossOrigin
	@PostMapping(path = "/sign-up")
    @ApiOperation(value = "Crea un nuevo usuario", response = ResponseEntity.class)
	public ResponseEntity<Object> signUp(@Valid @RequestBody UserEntity user) throws Exception {

        UserEntity userToken = us.signUp(user);

        authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), userToken.getPassword()));	
		String token = jwtUtil.createToken(userToken);		
		us.setToken(userToken, token);

		return ResponseEntity.ok(userToken);
	}
}
