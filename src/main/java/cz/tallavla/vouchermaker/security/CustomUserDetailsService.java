package cz.tallavla.vouchermaker.security;

import cz.tallavla.vouchermaker.model.repository.User;
import cz.tallavla.vouchermaker.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
				.orElseThrow(() ->
						new UsernameNotFoundException("User not found with userName or email: " + userNameOrEmail));

		Set<GrantedAuthority> authorities = user
				.getRoles()
				.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}


}
