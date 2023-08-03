package cz.tallavla.vouchermaker.service.impl;

import cz.tallavla.vouchermaker.exception.VoucherMakerAPIException;
import cz.tallavla.vouchermaker.model.repository.Role;
import cz.tallavla.vouchermaker.model.repository.User;
import cz.tallavla.vouchermaker.model.service.LoginDTO;
import cz.tallavla.vouchermaker.model.service.RegisterDTO;
import cz.tallavla.vouchermaker.repository.RoleRepository;
import cz.tallavla.vouchermaker.repository.UserRepository;
import cz.tallavla.vouchermaker.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String login(LoginDTO loginDTO) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getUserNameOrEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "User Logged-in successfully!";
	}

	@Override
	public String register(RegisterDTO registerDTO) {
		// add check for username in db
		if (userRepository.existsByUserName(registerDTO.getUserName())) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "User already exists");
		}

		if (userRepository.existsByEmail(registerDTO.getEmail())) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		User user = new User();
		user.setName(registerDTO.getName());
		user.setUserName(registerDTO.getUserName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);

		return "User registered successfully!";
	}
}
