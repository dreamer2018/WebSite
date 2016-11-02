package org.xiyoulinux.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class CharsetAllEncodingFilter
 */

@WebFilter(filterName = "CharsetAllEncodingFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharsetAllEncodingFilter implements Filter {

    /**
     * Default constructor.
     */
    private String encoding = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }
}
