package cl.goviedo.usermanagment;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;

import cl.goviedo.usermanagment.entities.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals; 

import cl.goviedo.usermanagment.entities.PhoneEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UsermanagmentApplicationTestsRestTemplate {

	@Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
    

    @DisplayName("TEST-APP: Verificando Creacion de Usuario Correcto HTTP STATUS 200 ACCEPTED")
	@Test
	public void creation_of_a_user_and_status_200() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("Gonzalo").password("Cogoter$").email("goviedo@gmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
	        assertEquals(200, result.getStatusCodeValue());
	}

	@DisplayName("TEST-APP: Usuario Existe. 409 CONFLICT")
	@Test
	public void usuario_ya_existe() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("Gonzalo").password("Cogoter$").email("goviedo@gmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
	        assertEquals(409, result.getStatusCodeValue());
	}
    
    @DisplayName("TEST-APP: Email incorrecto BAD REQUEST")
	@Test
	public void emailIncorrecto() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("Gonzalo").password("Cogoter$").email("goviedogmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assertEquals(400, result.getStatusCodeValue());       
	}
    
    @DisplayName("TEST-APP: Password incorrecto BAD REQUEST")
	@Test
	public void passwordIncorrecto() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("Gonzalo").password("hola").email("goviedo@gmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assertEquals(400, result.getStatusCodeValue());       
	}

	@DisplayName("TEST-APP: Token al Crear un Usuario. 200 OK")
	@Test
	public void token() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("Andrea").password("Andreapass$").email("andrea@gmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assert(result.getBody().toString().contains("token"));       
	}

	@DisplayName("TEST-APP: Correo goviedo@com.edu es valido. 200 OK")
	@Test
	public void correoEdu() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("ValidaCorreoEdu").password("CorreoEdu$").email("goviedo@com.edu")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assertEquals(200, result.getStatusCodeValue());       
	}

	@DisplayName("TEST-APP: Correo g.oviedo.lambert@gmail.com no esta permitido 400 BAD REQUEST")
	@Test
	public void correoGmailConPuntos() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("OtroCorreoGmailClasico").password("Correo4Edu$").email("g.oviedo.lambert@gmail.com")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assertEquals(400, result.getStatusCodeValue());       
	}

	@DisplayName("TEST-APP: Correo francisca@ubb.cl debiera ser correcto 200 OK")
	@Test
	public void correoDominioUbb() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("CorreoDominioUbb").password("Colgator$").email("francisca@ubb.cl")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
			System.out.println(result.getBody().toString());
	        assertEquals(200, result.getStatusCodeValue());       
	}

	@DisplayName("TEST-APP: Correo incorrecto@hola debiera ser incorrecto 400 BAD Request")
	@Test
	public void correoIncorrecto() throws Exception {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/user/sign-up/";
	        URI uri = new URI(baseUrl);
	        PhoneEntity fono = PhoneEntity.builder().cityCode(45).contryCode("CL").number(56963723603L).build();
			List<PhoneEntity> fonos = new ArrayList<PhoneEntity>();
			fonos.add(fono);
			UserEntity usuario = UserEntity.builder().name("CorreoIncorrecto").password("Correo4Edu$").email("incorrecto@hola")
					.phones(fonos).build();      
	        HttpHeaders headers = new HttpHeaders();	 
	        HttpEntity<UserEntity> request = new HttpEntity<>(usuario, headers);         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

	        assertEquals(400, result.getStatusCodeValue());       
	}
}
