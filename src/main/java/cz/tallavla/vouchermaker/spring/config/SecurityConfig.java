package cz.tallavla.vouchermaker.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeHttpRequests((authorize) ->
//						authorize.anyRequest().authenticated()
								authorize.regexMatchers(HttpMethod.GET, "/vouchemaker/*").permitAll() //TODO: nefunguje povoleni bez autentikace
										.regexMatchers(HttpMethod.GET, "/actuator/*").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/login").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/signin").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/register").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/signup").permitAll()
										.anyRequest().authenticated()
				).httpBasic(Customizer.withDefaults());


		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails vlada = User.builder()
//				.username("vlada")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER")
//				.build();
//
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(vlada, admin);
//	}

}
