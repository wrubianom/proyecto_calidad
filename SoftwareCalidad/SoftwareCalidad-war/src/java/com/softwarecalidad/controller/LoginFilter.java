/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sebastian Vega
 */
@WebFilter(filterName = "filter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsuarioBean usuario = (UsuarioBean) ((HttpServletRequest) request).getSession().getAttribute("usuarioBean");
        String path = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length());
        if ((usuario == null && path != null && path.length() == 1)) {
            System.out.println("lllllllllllllllll");
            ((HttpServletResponse) response).sendRedirect("/SoftwareCalidad-war/faces/pages/index.xhtml");
        } else {
            if (path.startsWith("/pages/") && usuario.getUsuario() != null) {
                System.out.println("--------- "+usuario.getUsuario().getNombre());
                chain.doFilter(request, response);
            } else if (path.startsWith("/faces/pages/")) {
                System.out.println("ppppppppppppppppp");
                chain.doFilter(request, response);
            } else if (path.length() == 1 && usuario != null) {
                System.out.println("qwwwwwwwwwwwwwww");
                chain.doFilter(request, response);
            } else if (path.contains("javax.faces.resource")) {
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh");
                chain.doFilter(request, response);
            } else {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
                ((HttpServletResponse) response).sendRedirect("/SoftwareCalidad-war/faces/pages/unauthorized.xhtml");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
