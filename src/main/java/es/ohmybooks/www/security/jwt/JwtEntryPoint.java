package es.ohmybooks.www.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Clase que comprueba la autorización del user
 * 
 * @author Group3
 * @version 1.0
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

	// Implementa un logger para ver qué metodo da error en caso de fallo
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	/**
	 * Comprueba si existe un token y si no devuelve un 401 no autorizado
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Fallo el metodo commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado");
	}
}
