package com.palvair.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadByUsername");
		//final User user = userRepo.findByUsername(username);
		//if (user == null) {
		//	throw new UsernameNotFoundException("user not found");
		//}
		//detailsChecker.check(user);
		User user = new User();
		user.setPassword("1234");
		user.setUsername("widdy");

		return user;
	}
}
