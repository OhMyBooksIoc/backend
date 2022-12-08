package es.ohmybooks.www.security.jwt;

import es.ohmybooks.www.security.entity.UserMain;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Clase que genera el token y valida que este bien formado y no este expirado
 * 
 * @author Group3
 * @version 1.0
 */
@Component
public class JwtProvider {

	// Implementa un logger para ver que metodo da error en caso de fallo
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	// Valores que hay en el application.properties
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	/**
	 * Metodo que genera el token del user
	 * 
	 * @param authentication
	 * @return token
	 */
	public String generateToken(Authentication authentication) {
		UserMain userMain = (UserMain) authentication.getPrincipal();
		return Jwts.builder().setSubject(userMain.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * Metodo que recupera el nombre del usuario a través del token
	 * 
	 * @param token
	 * @return userName
	 */
	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject(); // subject --> Nombre del user
	}

	/**
	 * Metodo que valida el token del user
	 * 
	 * @param token
	 * @return true si el token és correcto y false si el token está mal formado, no
	 *         está soportado, ha expirado, está vacío o hay un fallo con la firma
	 */
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("Token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("Token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("Token vacio");
		} catch (SignatureException e) {
			logger.error("Fallo con la firma");
		}
		return false;
	}

}
