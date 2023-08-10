package cz.tallavla.vouchermaker.security;

import cz.tallavla.vouchermaker.exception.VoucherMakerAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private String jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {
		String userName = authentication.getName();

		Date currentDate = new Date();

		Long dateLong = currentDate.getTime() + Long.parseLong(jwtExpirationDate);

		Date expireDate = new Date(dateLong);

		String token = Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(key())
				.compact();

		return token;
	}

	private Key key(){
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret)
		);
	}

	// get userName form Jwt token
	public String getUserName(String token) {

		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();

		String userName = claims.getSubject();

		return userName;
	}

	// validate Jwt token
	public boolean validateToken(String token) {

		try {
			Jwts.parserBuilder()
					.setSigningKey(key())
					.build()
					.parse(token);
			return true;
		} catch (MalformedJwtException ex) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new VoucherMakerAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		}
	}
}
