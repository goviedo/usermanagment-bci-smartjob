package cl.goviedo.usermanagment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import cl.goviedo.usermanagment.services.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
	
	private final UserService userDetailsService;

    public ProjectSecurityConfig(UserService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;

    }
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests().anyRequest().permitAll();
        http.authorizeRequests(requests -> requests
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions().disable());
        http.formLogin();
        http.httpBasic();
        return http.build();
    }
    
    
    
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder());
      provider.setUserDetailsService(this.userDetailsService);
      return provider;
    }
    
    /*@Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }*/
}