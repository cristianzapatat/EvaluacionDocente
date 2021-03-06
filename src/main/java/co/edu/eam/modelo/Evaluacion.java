package co.edu.eam.modelo;
// Generated 12/09/2016 01:19:13 AM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Evaluacion generated by hbm2java
 */
@Entity
@Table(name="evaluacion"
    ,schema="public"
)
public class Evaluacion  implements java.io.Serializable {


     private Integer id;
     private Periodo periodo;
     private ProgramaAcademico programaAcademico;
     private TipoEvaluacion tipoEvaluacion;
     private String nombre;
     private Set<Presentacion> presentacions = new HashSet<Presentacion>(0);

    public Evaluacion() {
    }

	
    public Evaluacion(Integer id, Periodo periodo, ProgramaAcademico programaAcademico, TipoEvaluacion tipoEvaluacion, String nombre) {
        this.id = id;
        this.periodo = periodo;
        this.programaAcademico = programaAcademico;
        this.tipoEvaluacion = tipoEvaluacion;
        this.nombre = nombre;
    }
    public Evaluacion(Integer id, Periodo periodo, ProgramaAcademico programaAcademico, TipoEvaluacion tipoEvaluacion, String nombre, Set<Presentacion> presentacions) {
       this.id = id;
       this.periodo = periodo;
       this.programaAcademico = programaAcademico;
       this.tipoEvaluacion = tipoEvaluacion;
       this.nombre = nombre;
       this.presentacions = presentacions;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="periodo_id", nullable=false)
    public Periodo getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="programa_id", nullable=false)
    public ProgramaAcademico getProgramaAcademico() {
        return this.programaAcademico;
    }
    
    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipo_evaluacion_id", nullable=false)
    public TipoEvaluacion getTipoEvaluacion() {
        return this.tipoEvaluacion;
    }
    
    public void setTipoEvaluacion(TipoEvaluacion tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    
    @Column(name="nombre", nullable=false, length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="evaluacion")
    public Set<Presentacion> getPresentacions() {
        return this.presentacions;
    }
    
    public void setPresentacions(Set<Presentacion> presentacions) {
        this.presentacions = presentacions;
    }




}


