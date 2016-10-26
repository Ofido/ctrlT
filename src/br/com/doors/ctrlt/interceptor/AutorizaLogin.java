package br.com.doors.ctrlt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizaLogin extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("Login") ||uri.endsWith("efetuaLogin") || uri.contains("resources") || uri.contains("CadastrandoAluno") || uri.contains("CadastrandoProfessor")){
			return true;
		}
		if(request.getSession().getAttribute("usuarioLogado") != null){
			return true;
		}
		response.sendRedirect("views/Login.jsp");
		return false;
	}
}
