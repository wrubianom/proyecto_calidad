/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William.Rubiano
 */
@Entity
@Table(name = "horario_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioProfesor.findAll", query = "SELECT h FROM HorarioProfesor h"),
    @NamedQuery(name = "HorarioProfesor.findByIdHorarioProfesor", query = "SELECT h FROM HorarioProfesor h WHERE h.idHorarioProfesor = :idHorarioProfesor"),
    @NamedQuery(name = "HorarioProfesor.findByDia", query = "SELECT h FROM HorarioProfesor h WHERE h.dia = :dia"),
    @NamedQuery(name = "HorarioProfesor.findByHora", query = "SELECT h FROM HorarioProfesor h WHERE h.hora = :hora"),
    @NamedQuery(name = "HorarioProfesor.findByEstado", query = "SELECT h FROM HorarioProfesor h WHERE h.estado = :estado"),
    @NamedQuery(name = "HorarioProfesor.findByManual", query = "SELECT h FROM HorarioProfesor h WHERE h.manual = :manual")})
public class HorarioProfesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario_profesor")
    private Integer idHorarioProfesor;
    @Size(max = 1)
    @Column(name = "dia")
    private String dia;
    @Size(max = 2)
    @Column(name = "hora")
    private String hora;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 1)
    @Column(name = "manual")
    private String manual;
    @JoinColumn(name = "id_horario_materia", referencedColumnName = "id_horario_materia")
    @ManyToOne
    private HorarioMateria idHorarioMateria;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    @ManyToOne
    private Profesor idProfesor;

    public HorarioProfesor() {
    }

    public HorarioProfesor(Integer idHorarioProfesor) {
        this.idHorarioProfesor = idHorarioProfesor;
    }

    public Integer getIdHorarioProfesor() {
        return idHorarioProfesor;
    }

    public void setIdHorarioProfesor(Integer idHorarioProfesor) {
        this.idHorarioProfesor = idHorarioProfesor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public HorarioMateria getIdHorarioMateria() {
        return idHorarioMateria;
    }

    public void setIdHorarioMateria(HorarioMateria idHorarioMateria) {
        this.idHorarioMateria = idHorarioMateria;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioProfesor != null ? idHorarioProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioProfesor)) {
            return false;
        }
        HorarioProfesor other = (HorarioProfesor) object;
        if ((this.idHorarioProfesor == null && other.idHorarioProfesor != null) || (this.idHorarioProfesor != null && !this.idHorarioProfesor.equals(other.idHorarioProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softwarecalidad.entidades.HorarioProfesor[ idHorarioProfesor=" + idHorarioProfesor + " ]";
    }
    
}
