/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William.Rubiano
 */
@Entity
@Table(name = "horario_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorarioMateria.findAll", query = "SELECT h FROM HorarioMateria h"),
    @NamedQuery(name = "HorarioMateria.findByIdHorarioMateria", query = "SELECT h FROM HorarioMateria h WHERE h.idHorarioMateria = :idHorarioMateria"),
    @NamedQuery(name = "HorarioMateria.findByIdMateria", query = "SELECT h FROM HorarioMateria h WHERE h.idMateria.idMateria = :idMateriaInt order by H.grupo"),
    @NamedQuery(name = "HorarioMateria.findByGrupo", query = "SELECT h FROM HorarioMateria h WHERE h.grupo = :grupo"),
    @NamedQuery(name = "HorarioMateria.findByDia", query = "SELECT h FROM HorarioMateria h WHERE h.dia = :dia"),
    @NamedQuery(name = "HorarioMateria.findByHora", query = "SELECT h FROM HorarioMateria h WHERE h.hora = :hora"),
    @NamedQuery(name = "HorarioMateria.findByDuracion", query = "SELECT h FROM HorarioMateria h WHERE h.duracion = :duracion"),
    @NamedQuery(name = "HorarioMateria.findBySemestre", query = "SELECT h FROM HorarioMateria h WHERE h.semestre = :semestre"),
    @NamedQuery(name = "HorarioMateria.findByJornada", query = "SELECT h FROM HorarioMateria h WHERE h.jornada = :jornada")})
public class HorarioMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario_materia")
    private Integer idHorarioMateria;
    @Size(max = 3)
    @Column(name = "grupo")
    private String grupo;
    @Size(max = 1)
    @Column(name = "dia")
    private String dia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "hora")
    private String hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "duracion")
    private String duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "semestre")
    private String semestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "jornada")
    private String jornada;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;
    @OneToMany(mappedBy = "idHorarioMateria")
    private List<HorarioProfesor> horarioProfesorList;

    public HorarioMateria() {
    }

    public HorarioMateria(Integer idHorarioMateria) {
        this.idHorarioMateria = idHorarioMateria;
    }

    public HorarioMateria(Integer idHorarioMateria, String hora, String duracion, String semestre, String jornada) {
        this.idHorarioMateria = idHorarioMateria;
        this.hora = hora;
        this.duracion = duracion;
        this.semestre = semestre;
        this.jornada = jornada;
    }

    public Integer getIdHorarioMateria() {
        return idHorarioMateria;
    }

    public void setIdHorarioMateria(Integer idHorarioMateria) {
        this.idHorarioMateria = idHorarioMateria;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    @XmlTransient
    public List<HorarioProfesor> getHorarioProfesorList() {
        return horarioProfesorList;
    }

    public void setHorarioProfesorList(List<HorarioProfesor> horarioProfesorList) {
        this.horarioProfesorList = horarioProfesorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioMateria != null ? idHorarioMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioMateria)) {
            return false;
        }
        HorarioMateria other = (HorarioMateria) object;
        if ((this.idHorarioMateria == null && other.idHorarioMateria != null) || (this.idHorarioMateria != null && !this.idHorarioMateria.equals(other.idHorarioMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softwarecalidad.entidades.HorarioMateria[ idHorarioMateria=" + idHorarioMateria + " ]";
    }

    public String getNombreDia() {
        String dia = this.dia;
        switch (dia) {
            case "1":
                return "lunes";
            case "2":
                return "Martes";
            case "3":
                return "Miercoles";
            case "4":
                return "Jueves";
            case "5":
                return "Viernes";
            case "6":
                return "Sabado";
        }
        return "";
    }

    public String getNombreJornada() {
        String jorda = this.jornada;
        System.out.println("La jornada es :" + this.jornada);
        switch (jorda) {
            case "N":
                return "Nocturna";

            case "D":
                return "Diurna";
            case "E":
                return "Especial";
        }
        return "";
    }
}
