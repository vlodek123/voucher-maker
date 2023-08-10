package cz.tallavla.vouchermaker.spring.config;

import cz.tallavla.vouchermaker.security.JwtAuthenticationEntryPoint;
import cz.tallavla.vouchermaker.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private JwtAuthenticationFilter  jwtAuthenticationFilter;

	public SecurityConfig(UserDetailsService userDetailsService,
						  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
						  JwtAuthenticationFilter  jwtAuthenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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
								authorize.antMatchers(HttpMethod.GET, "/actuator/**", "/vouchermaker/**").permitAll()
										//.antMatchers(HttpMethod.GET, "/vouchemaker/**").permitAll()
										//	.regexMatchers(HttpMethod.GET, "/actuator/*").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/login").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/signin").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/register").permitAll()
										.regexMatchers(HttpMethod.POST, "/vouchermaker/auth/signup").permitAll()
										.anyRequest().authenticated()
				).exceptionHandling(exception -> exception
						.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				).sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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