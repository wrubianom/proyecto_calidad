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
        LoginBean usuario = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("usuarioBean");
        String path = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length());
        if ((usuario == null && path != null && path.length() == 1)) {
            ((HttpServletResponse) response).sendRedirect("/Aprov_Dominios/faces/pages/publicas/login.xhtml");
        } else {
            if (path.startsWith("/admin/") && (usuario.getCurrentUsuario() != null && usuario.getCurrentUsuario().getTipo().equals("U"))) {
                chain.doFilter(request, response);
            } else if (path.startsWith("/faces/pages/publicas/")) {
                chain.doFilter(request, response);
            } else if (path.length() == 1 && usuario != null) {
                chain.doFilter(request, response);
            } else if (path.contains("javax.faces.resource")) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/Aprov_Dominios/faces/pages/publicas/unauthorized.xhtml");
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

/*@WebFilter(filterName = "filter", urlPatterns = "/faces/pages/privadas/*")
 public class LoginFilter implements Filter {

 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 UsuarioBean usuario = (UsuarioBean) ((HttpServletRequest) request).getSession().getAttribute("usuarioBean");
 if (usuario.getUsuario() == null && !usuario.isIsLoggin()) {
 //String contextPath = ((HttpServletRequest) request).getContextPath();
 ((HttpServletResponse) response).sendRedirect("/Aprov_Dominios/faces/pages/publicas/login.xhtml");
 } else {
 String path = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length());
 if (path.startsWith("/faces/pages/privadas/administrador/") && (usuario.getUsuario().getIdPerfil().getId() == 1 || usuario.getUsuario().getIdPerfil().getId() == 2)) {
 chain.doFilter(request, response);
 } else if (path.startsWith("/faces/pages/privadas/registro/") && usuario.getUsuario().getIdPerfil().getId() == 3) {
 chain.doFilter(request, response);
 } else if (path.startsWith("/faces/pages/privadas/consulta/") && usuario.getUsuario().getIdPerfil().getId() == 4) {
 chain.doFilter(request, response);
 } else if (path.startsWith("/faces/pages/publicas/")) {
 chain.doFilter(request, response);
 } else {
 ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
 }
 }
 }

 @Override
 public void init(FilterConfig filterConfig) throws ServletException {

 }

 @Override
 public void destroy() {

 }*/
