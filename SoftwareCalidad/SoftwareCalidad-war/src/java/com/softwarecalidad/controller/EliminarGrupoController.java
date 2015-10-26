package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.HorarioMateria;
import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.HorarioMateriaEJBLocal;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
import com.softwarecalidad.utilidades.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author William.Rubiano
 */
@ManagedBean
@ViewScoped
public class EliminarGrupoController implements Serializable {

    @EJB
    private HorarioMateriaEJBLocal horarioMateriaEJB;

    @EJB
    private MateriaEJBLocal materiaEJB;

    private Integer idMateria;
    private List<Materia> listaMaterias;
    private boolean encontroResultado;
    private List<HorarioMateria> listGrupoMateria;
    private Profesor profesorSelect;

    public Integer getIdMateria() {
        return idMateria;
    }

    public Profesor getProfesorSelect() {
        return profesorSelect;
    }

    public void setProfesorSelect(Profesor profesorSelect) {
        this.profesorSelect = profesorSelect;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public List<HorarioMateria> getListGrupoMateria() {
        return listGrupoMateria;
    }

    public void setListGrupoMateria(List<HorarioMateria> listGrupoMateria) {
        this.listGrupoMateria = listGrupoMateria;
    }

    public boolean isEncontroResultado() {
        return encontroResultado;
    }

    public void setEncontroResultado(boolean encontroResultado) {
        this.encontroResultado = encontroResultado;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    @PostConstruct
    private void init() {
        this.listaMaterias = materiaEJB.getAllMaterias();
        this.encontroResultado = false;
        this.listGrupoMateria = new ArrayList<>();
        this.profesorSelect = new Profesor();
    }

    public EliminarGrupoController() {
    }

    public void consultarMateria() {
        this.encontroResultado = false;
        this.listGrupoMateria = new ArrayList<>();
        List<HorarioMateria> resul = this.horarioMateriaEJB.consultarGrupoMateriaByIdMateria(this.idMateria);
        if (resul != null && !resul.isEmpty()) {

            this.encontroResultado = true;
            this.listGrupoMateria = resul;
        } else {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados");
        }
    }

    public void consultarMateriaInt() {

        this.listGrupoMateria = new ArrayList<>();
        List<HorarioMateria> resul = this.horarioMateriaEJB.consultarGrupoMateriaByIdMateria(this.idMateria);
        if (resul != null && !resul.isEmpty()) {
            this.encontroResultado = true;
            this.listGrupoMateria = resul;
        }
    }

    public void eliminarGrupo(Integer idhorarioMateriaEli) {
        System.out.println("El horario materia a eliminar es " + idhorarioMateriaEli);
        ResultadoOperation res = this.horarioMateriaEJB.eliminarHorarioGrupoByidHorarioMateria(idhorarioMateriaEli);
        if (res.isOk()) {
            this.consultarMateriaInt();
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "Se elimino correctamente el grupo");
        } else {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al eliminar el grupo");
            System.out.println("Error : " + res.getMensaje());
        }
    }

    public void iniciarPopUp(Integer idhorarioMateriaEli) {
        this.profesorSelect = this.getProfesorBylista(idhorarioMateriaEli);
    }

    public Profesor getProfesorBylista(Integer idhorarioMateriaEli) {
        HorarioMateria hm = null;
        Profesor prof = new Profesor();
        System.out.println("entro1111111");
        for (HorarioMateria item : listGrupoMateria) {
            if (item.getIdHorarioMateria().equals(idhorarioMateriaEli)) {
                hm = item;
                System.out.println("entro1");
                break;
            }
        }
        if (hm != null && !hm.getHorarioProfesorList().isEmpty()) {
            prof = hm.getHorarioProfesorList().get(0).getIdProfesor();
            System.out.println("entro2");
        }
        if (prof != null) {
            System.out.println("el profesor es " + prof.getNombre());
        }

        return prof;
    }

    public String getProfesorBylistaNombre(Integer idhorarioMateriaEli) {
        HorarioMateria hm = null;
        Profesor prof = new Profesor();
        System.out.println("entro1111111");
        for (HorarioMateria item : listGrupoMateria) {
            if (item.getIdHorarioMateria().equals(idhorarioMateriaEli)) {
                hm = item;
                System.out.println("entro1");
                break;
            }
        }
        if (hm != null && !hm.getHorarioProfesorList().isEmpty()) {
            prof = hm.getHorarioProfesorList().get(0).getIdProfesor();
            System.out.println("entro2");
        }
        if (prof != null) {
            System.out.println("el profesor es " + prof.getNombre());
            return prof.getNombre();
        }

        return "No registra";
    }

    public void eliminarMateria() {

    }
}
