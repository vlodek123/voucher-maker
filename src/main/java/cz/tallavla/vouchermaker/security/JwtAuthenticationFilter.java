package cz.tallavla.vouchermaker.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;

	private UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		// get Jwt token from http request

		String token = getTokenFormRequest(request);
		System.out.println("TOKEN: " + token);
		// validate token

		UserDetails userDetails = null;
		
		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

			// get userNAme from token

			String userName = jwtTokenProvider.getUserName(token);

			/*UserDetails*/ userDetails = userDetailsService.loadUserByUsername(userName);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
			);

			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		System.out.println("Request: " + request);
		System.out.println("SecurityContextHolder: " + SecurityContextHolder.getContext());
		System.out.println("UserDetails: " + userDetails.getUsername());

		// entry point to application, before is authorization
		filterChain.doFilter(request, response);  //TODO: throws org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.IllegalArgumentException: source cannot be null
		// here can be some post logic
	}

	private String getTokenFormRequest(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
			return null;
	}
}
