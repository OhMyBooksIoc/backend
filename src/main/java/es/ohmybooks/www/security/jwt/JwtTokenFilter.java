package es.ohmybooks.www.security.jwt;

import es.ohmybooks.www.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Clase que se ejecuta por cada petición, comprueba que sea valido el token
 * Utiliza el provider para validarlo
 * Si es valido permite acceso al recurso y si no lanza una excepción
 * 
 * @author Group3
 * @version 1.0
 */
public class JwtTokenFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	/**
	 * Método que se lanza siempre que se realice una petición al sever para
	 * realizar las comprobaciones
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = getToken(request);

			if (token != null && jwtProvider.validateToken(token)) {

				String userName = jwtProvider.getUserNameFromToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
		} catch (Exception e) {
			logger.error("Fail en el método doFilter " + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * Método para obtener el token sin Bearer + el espacio
	 * 
	 * @param request
	 * @return token
	 */
	private String getToken(HttpServletRequest request) {

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer"))
			return header.replace("Bearer ", "");
		return null;

	}
}
