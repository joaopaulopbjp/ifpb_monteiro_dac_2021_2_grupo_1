package com.example.livraria.security.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.livraria.model.Usuario;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${livraria.jwtSecret}")
	private String jwtSecret;

	@Value("${livraria.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		Usuario userPrincipal = (Usuario) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getEmail()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Assinatura JWT inv�lida: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Token JWT inv�lido: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Token JWT expirou: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("Token JWT n�o � suportado: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Claims JWT vazio: {}", e.getMessage());
		}

		return false;
	}
}
