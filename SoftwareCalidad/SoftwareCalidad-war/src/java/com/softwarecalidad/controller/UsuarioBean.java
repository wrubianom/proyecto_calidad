/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Usuario;
import com.softwarecalidad.negocio.UsuarioEJBLocal;
import com.softwarecalidad.utilidades.Hash;
import com.softwarecalidad.utilidades.UtilFaces;
import com.softwarecalidad.utilidades.Utilidades;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sebastian Vega
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    @EJB
    private UsuarioEJBLocal usuarioEJB;

    private Utilidades util = new Utilidades();
    //public static final String INJECTION_NAME = "#{usuarioBean}";
    //private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private boolean isLoggin = false;
    private String user;
    private String password;
    //private String passold;
    //private String passnew;
    //private String passconfirm;
    private String path;
    private Usuario selectedUsuario;
//    private Perfil selectedPerfil;

    public UsuarioBean() {
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isIsLoggin() {
        return isLoggin;
    }

    public void setIsLoggin(boolean isLoggin) {
        this.isLoggin = isLoggin;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*    public String getPassold() {
     return passold;
     }

     public void setPassold(String passold) {
     this.passold = passold;
     }

     public String getPassnew() {
     return passnew;
     }

     public void setPassnew(String passnew) {
     this.passnew = passnew;
     }

     public String getPassconfirm() {
     return passconfirm;
     }

     public void setPassconfirm(String passconfirm) {
     this.passconfirm = passconfirm;
     }
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    /*    public Perfil getSelectedPerfil() {
     return selectedPerfil;
     }

     public void setSelectedPerfil(Perfil selectedPerfil) {
     this.selectedPerfil = selectedPerfil;
     }*/
    public String redirect() {
        usuario = null;
        return "/pages/index.xhtml?faces-redirect=true";
    }

    public String login() {
        try {
            if (util.validateText(user)) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el User");
            } else if (util.validateText(password)) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Password");
            } else {
                usuario = this.usuarioEJB.login(user, Hash.encript(password));
                if (usuario != null) {
                    this.setIsLoggin(true);
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "Bienvenido");
                    user = "";
                    password = "";
                    path = "/pages/";
                    return path + "home.xhtml?faces-redirect=true";
                } else {
                    this.setIsLoggin(false);
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe o la contraseña es incorrecta");
                    return null;
                }
            }
        } catch (Exception e) {
            this.setIsLoggin(false);
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
        //UsuarioFacade usuarioFacade = new UsuarioFacade();
        //usuario = usuarioFacade.findUsuarioByLogin(user, password);

        /* if (usuario.getEstado().equals('I')) {
         addMessage(FacesMessage.SEVERITY_ERROR, "Usuario Inactivo, por favor comuniquese con el administrador", null);
         return null;
         }*/
        //  this.setIsLoggin(true);
        //if (usuario.getIdPerfil().getId() == 2 || usuario.getIdPerfil().getId() == 1) {

        /*} else if (usuario.getIdPerfil().getId() == 3) {
         path = "/pages/privadas/registro/";
         return path + "solicitudes.xhtml?faces-redirect=true";
         } else if (usuario.getIdPerfil().getId() == 4) {
         path = "/pages/privadas/consulta/";
         return path + "solicitudes.xhtml?faces-redirect=true";
         }*/
        /*} else {
         addMessage(FacesMessage.SEVERITY_ERROR, "Credenciales no validas o Usuario no Existe", null);
         this.setIsLoggin(false);
         }
         return null;*/
        return null;
    }

    public String actualizar() {
        //UsuarioFacade usuarioFacade = new UsuarioFacade();
        //usuarioFacade.updateUsuario(usuario);

        addMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados", null);
        return null;
    }

    public String salir() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.getSession().invalidate();
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("usuarioBean");
        this.setIsLoggin(false);
        return "/pages/index.xhtml?faces-redirect=true";
    }

    public void addMessage(FacesMessage.Severity type, String summary, String detail) {
        FacesMessage msg = new FacesMessage(type, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String cambiarPass() {
        return path + "password";
    }

    /*   public String guardarPass() {
     if (!passnew.equals(passconfirm)) {
     addMessage(FacesMessage.SEVERITY_WARN, "Nuevos password no coinciden", null);
     } else if (hashband(passnew, usuario.getPassword())) {
     addMessage(FacesMessage.SEVERITY_WARN, "No puede utilizar el antiguo password", null);
     } else if (hashband(passold, usuario.getPassword())) {
     UsuarioFacade usuarioFacade = new UsuarioFacade();
     usuario.setPassword(Hash.encript(passnew));
     usuarioFacade.updateUsuario(usuario);
     addMessage(FacesMessage.SEVERITY_INFO, "Cambio de password exitoso", null);
     } else {
     addMessage(FacesMessage.SEVERITY_FATAL, "Password incorrecto", null);
     }
     return null;
     }*/
    static boolean hashband(String hashcadena, String hashpass) {
        if (hashpass.equals(Hash.encript(hashcadena))) {
            return true;
        } else {
            return false;
        }
    }
}
