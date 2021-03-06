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
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        LoginBean userManager = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");

        String contextPath = ((HttpServletRequest) request).getContextPath();
        String urlReques = contextPath + "/faces/index.xhtml";

        if (userManager != null && userManager.isLoged() && userManager.getCurrentUsuario() != null) {
            chain.doFilter(request, response);
            return;
        }
        String pathURI = request.getRequestURI();
        if (pathURI.contains("javax.faces.resource")) {
            chain.doFilter(request, response);
            return;
        }
        if (!request.getRequestURI().equals(urlReques)) {
            response.sendRedirect(urlReques);
            return;
        }

        chain.doFilter(request, response);
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
