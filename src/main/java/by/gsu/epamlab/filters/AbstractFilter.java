package by.gsu.epamlab.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 25.06.2016.
 */
public abstract class AbstractFilter implements Filter {
    public void destroy() {
    }



    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest)req,(HttpServletResponse)resp, chain);
    }
    public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException;




    public void init(FilterConfig config) throws ServletException {

    }

}
