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
import com.softwarecalidad.utilVista.DiasClaseUtil;
import com.softwarecalidad.utilVista.HorasClaseUtil;
import com.softwarecalidad.utilVista.OperacionesDiasUtil;
import com.softwarecalidad.utilVista.OperacionesHorasClase;
import com.softwarecalidad.utilidades.ResultadoOperation;
import com.softwarecalidad.utilidades.UtilFaces;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author WRubianoM
 */
@ManagedBean
@ViewScoped
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
    private List<HorasClaseUtil> listaHorasUtil;
    private List<DiasClaseUtil> listaDiasUtil;

    @PostConstruct
    private void init() {
        this.nuevoGrupo = new HorarioMateria();
        this.listaMaterias = materiaEJB.getAllMaterias();
        this.panelRenderizado = 0;
        this.listaDiasUtil = new ArrayList<>();//OperacionesDiasUtil.getDiasSemana();
        this.listaHorasUtil = new ArrayList<>();//OperacionesHorasClase.traerTodasLasHorasDeClase();
    }

    public List<DiasClaseUtil> getListaDiasUtil() {
        return listaDiasUtil;
    }

    public void setListaDiasUtil(List<DiasClaseUtil> listaDiasUtil) {
        this.listaDiasUtil = listaDiasUtil;
    }

    public Integer getRenderError() {
        return renderError;
    }

    public List<HorasClaseUtil> getListaHorasUtil() {
        return listaHorasUtil;
    }

    public void setListaHorasUtil(List<HorasClaseUtil> listaHorasUtil) {
        this.listaHorasUtil = listaHorasUtil;
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
        System.out.println("La jornada es " + this.nuevoGrupo.getJornada());
        Materia materiaCon = new Materia(this.idMateria);
        this.nuevoGrupo.setIdMateria(materiaCon);
        String trimGrupo = this.nuevoGrupo.getGrupo();
        this.nuevoGrupo.setGrupo(trimGrupo.trim());
        ResultadoOperation res = this.horarioMateriaEJB.crearhorarioMateria(nuevoGrupo);

        if (res.isOk()) {
            this.panelRenderizado = this.renderOperacionOk;

            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "Agrego correctamente el grupo");
            this.iniciarAdicionarGrupo();
        } else {
            this.panelRenderizado = this.renderError;
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "El grupo ingresado ya se encuentra registrado para la materia seleccionada");
        }
    }

    private void iniciarAdicionarGrupo() {
        this.nuevoGrupo = new HorarioMateria();
        this.idMateria = null;
        this.listaDiasUtil = new ArrayList<>();
        this.listaHorasUtil = new ArrayList<>();
    }

    public boolean renderizarPanel(Integer idPanelrender) {
        if (idPanelrender.equals(this.panelRenderizado)) {
            return true;
        } else if (idPanelrender.equals(this.panelRenderizado)) {
            return true;
        }
        return false;
    }

    public void cambioJornada(ValueChangeEvent event) {
        String jornadaSelect = ((String) event.getNewValue());

        System.out.println("La jornada es " + jornadaSelect);

        if (jornadaSelect.equals("E")) {
            this.listaDiasUtil = OperacionesDiasUtil.getDiasEntreSemanaConSabado(true);
        } else {
            this.listaDiasUtil = OperacionesDiasUtil.getDiasEntreSemanaConSabado(false);
        }
    }

    public void cambioDia(ValueChangeEvent event) {
        String jornadaSelect = ((String) event.getNewValue());

        // seis es sabado
        System.out.println("el dia es " + jornadaSelect + " y la jornada " + this.nuevoGrupo.getJornada());

        if (jornadaSelect.equals("6")) {
            System.out.println("entro Dia 6");
            this.listaHorasUtil = OperacionesHorasClase.traerHorasEspecialSabado();
        } else {
            System.out.println("No es sabadoS");

            if (this.nuevoGrupo.getJornada().equals("E")) {
                System.out.println("Entro por horas solo especial");
                this.listaHorasUtil = OperacionesHorasClase.traerHorasEspecial();
            } else {
                System.out.println("Entro por horas diurnas");
                this.listaHorasUtil = OperacionesHorasClase.traerHorasDiurnas();
            }

        }
    }

}
