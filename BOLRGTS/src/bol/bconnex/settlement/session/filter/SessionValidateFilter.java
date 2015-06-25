package bol.bconnex.settlement.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionValidateFilter implements Filter {
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        if (session==null) 
            response.sendRedirect("/BOLRGTS/logon.jsp");
        chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

}
