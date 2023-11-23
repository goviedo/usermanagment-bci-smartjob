package cl.goviedo.usermanagment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.goviedo.usermanagment.entities.UserEntity;
import cl.goviedo.usermanagment.repositories.PhoneEntityRepository;
import cl.goviedo.usermanagment.repositories.UserEntityRepository;

@Service
public class UserService implements UserDetailsService {

	private UserEntityRepository ur;
	private PhoneEntityRepository pr;

	public UserService(UserEntityRepository ur, PhoneEntityRepository pr) {
		this.ur = ur;
		this.pr = pr;

	}

	public UserEntity signUp(UserEntity user) throws Exception {
		UserEntity u = null;
		pr.saveAll(user.getPhones());
		try {
			u = ur.save(user);
		} catch (DataIntegrityViolationException e) {
			throw e;
		} catch (Exception ex) {
			throw ex;
		}
		return u;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = ur.findUserByEmail(email);
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).roles(roles.toArray(new String[0])).build();
		return userDetails;
	}

}
