package br.usjt.arqsw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.usjt.arqsw.entity.Usuario;

public class AuthorizatedInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception{

		String uri = request.getRequestURI();
		
		if(uri.contains("js") || uri.contains("css") || uri.contains("html")
				|| uri.contains("jpg") || uri.contains("index") || uri.contains("rest")){
			return true;
		}
		
		HttpSession session = request.getSession();
		Usuario logado = (Usuario)session.getAttribute(Usuario.LOGADO);
		
		if(logado != null || uri.endsWith("loginForm") 
				|| uri.endsWith("fazer_login")){
			return true;
		}
		response.sendRedirect("loginForm");
		
		return false;
	}
}
