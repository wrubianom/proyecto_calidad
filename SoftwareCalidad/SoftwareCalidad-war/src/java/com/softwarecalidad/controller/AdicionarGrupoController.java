/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.HorarioMateria;
import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.HorarioMateriaEJBLocal;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author WRubianoM
 */
@ManagedBean
@RequestScoped
public class AdicionarGrupoController {

    @EJB
    private HorarioMateriaEJBLocal horarioMateriaEJB;

    @EJB
    private MateriaEJBLocal materiaEJB;

    private Integer renderError = 1;
    private Integer renderOperacionOk = 2;

    private Integer panelRenderizado;

    private HorarioMateria nuevoGrupo;
    private List<Materia> listaMaterias;

    @PostConstruct
    private void init() {
        this.nuevoGrupo = new HorarioMateria();
        this.listaMaterias = materiaEJB.getAllMaterias();
        this.panelRenderizado = 0;
    }

    public Integer getRenderError() {
        return renderError;
    }

    public void setRenderError(Integer renderError) {
        this.renderError = renderError;
    }

    public Integer getRenderOperacionOk() {
        return renderOperacionOk;
    }

    public void setRenderOperacionOk(Integer renderOperacionOk) {
        this.renderOperacionOk = renderOperacionOk;
    }

    public Integer getPanelRenderizado() {
        return panelRenderizado;
    }

    public void setPanelRenderizado(Integer panelRenderizado) {
        this.panelRenderizado = panelRenderizado;
    }

    public HorarioMateria getNuevoGrupo() {
        return nuevoGrupo;
    }

    public void setNuevoGrupo(HorarioMateria nuevoGrupo) {
        this.nuevoGrupo = nuevoGrupo;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
    private Integer idMateria;

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * Creates a new instance of AdicionarGrupoController
     */
    public AdicionarGrupoController() {
    }

    public void guardarGrupo() {

        this.panelRenderizado = 0;
        Materia materiaCon = new Materia(this.idMateria);
        this.nuevoGrupo.setIdMateria(materiaCon);

        ResultadoOperation res = this.horarioMateriaEJB.crearhorarioMateria(nuevoGrupo);

        if (res.isOk()) {
            this.panelRenderizado = this.renderOperacionOk;
            System.out.println("Lo agrero correctamente");
            System.out.println("Error : " + res.getMensaje());
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Lo agrero correctamente");
            mensaje.setDetail("Lo agrero correctamente");
            fc.addMessage("mensajeError", mensaje);
        } else {
            this.panelRenderizado = this.renderError;
            System.out.println("Error : " + res.getMensaje());
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary(res.getMensaje());
            mensaje.setDetail(res.getMensaje());
            fc.addMessage("mensajeError", mensaje);
        }
    }

    public boolean renderizarPanel(Integer idPanelrender) {
        if (idPanelrender.equals(this.panelRenderizado)) {
            return true;
        } else if (idPanelrender.equals(this.panelRenderizado)) {
            return true;
        }
        return false;
    }
}
