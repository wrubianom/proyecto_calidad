/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.entidades.Perfil;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.negocio.PerfilEJBLocal;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Personal
 */
@ManagedBean
@RequestScoped
public class AdicionarMateriaProfesorController implements Serializable {

    @EJB
    private PerfilEJBLocal perfilEJB;
    @EJB
    private MateriaEJBLocal materiaEJB;
    @EJB
    private ProfesorEJBLocal profesorEJB;

    private ArrayList<Profesor> profesores;
    private ArrayList<Materia> materias;
    private ArrayList<Perfil> perfiles;
    private Profesor profesor;
    private Materia materia;
    private int materiasCodigo[];
    private boolean ban;

    public AdicionarMateriaProfesorController() {
    }

    @PostConstruct
    public void init() {
        profesor = new Profesor();
        materia = new Materia();
        perfiles = new ArrayList<Perfil>();
        profesores = new ArrayList<Profesor>();
        materias = new ArrayList<Materia>();
        ban = false;
        profesores.addAll(profesorEJB.findAllProfesor());
        materias.addAll(materiaEJB.getAllMaterias());
    }

    public boolean isSession() {
        return ban;
    }

    public void cargarMaterias() {
        materias.clear();
        materias.addAll(materiaEJB.getAllMaterias());
        ban = true;
    }

    public void adicionarMaterias() {
        System.out.println("--- " + materiasCodigo.length);
        try {
            for (int i = 0; i < materiasCodigo.length; i++) {
                Perfil perfil = new Perfil();
                perfil.setIdProfesor(profesor);
                perfil.setIdMateria(new Materia(materiasCodigo[i]));
                boolean ban = perfilEJB.crearPerfil(perfil);
                if (ban) {
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "El Perfil a sido Agregado");
                } else {
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "El Perfil no a sido Agregado");
                }
            }
        } catch (Exception e) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public void cargarPerfiles() {
        try {
            List<Perfil> auxPerfiles = perfilEJB.cargarPerfiles();
            List<Materia> auxMaterias = materiaEJB.getAllMaterias();
            materias.clear();
            if (auxPerfiles != null) {
                for (Perfil perfil : auxPerfiles) {
                    if (perfil.getIdPerfil().equals(profesor.getIdProfesor())) {
                        for (Materia auxMateria : auxMaterias) {
                            if (perfil.getIdMateria().equals(auxMateria.getIdMateria())) {
                                materias.add(materia);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public void eliminarMateria() {
        try {
            for (int i = 0; i < materiasCodigo.length; i++) {
                Perfil auxPerfil = new Perfil();
                auxPerfil.setIdMateria(new Materia(materiasCodigo[i]));
                auxPerfil.setIdProfesor(profesor);
                perfilEJB.eliminarPerfil(auxPerfil);
            }
        } catch (Exception e) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(ArrayList<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int[] getMateriasCodigo() {
        return materiasCodigo;
    }

    public void setMateriasCodigo(int[] materiasCodigo) {
        this.materiasCodigo = materiasCodigo;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

}
