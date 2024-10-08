package com.example.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.model.MyUserDetails;
import com.example.security.model.User;
import com.example.security.model.UserRepository;

@Service
public class MyUserDeatilsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * public MyUserDeatilsService(UserRepository userRepository) {
	 * this.userRepository = userRepository; }
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// return new MyUserDetails(username);
		System.out.println("loadUserByUsername username :: " + username);
		Optional<User> user = userRepository.findByUsername(username);
		
		System.out.println("loadUserByUsername user :: " + user);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		return new MyUserDetails(user.get());

	}

}
