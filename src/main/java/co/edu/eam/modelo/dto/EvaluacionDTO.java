package co.edu.eam.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class EvaluacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EvaluacionDTO.class);
    private Integer id;
    private String nombre;
    private Integer id_Periodo;
    private Integer id_ProgramaAcademico;
    private Integer id_TipoEvaluacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_Periodo() {
        return id_Periodo;
    }

    public void setId_Periodo(Integer id_Periodo) {
        this.id_Periodo = id_Periodo;
    }

    public Integer getId_ProgramaAcademico() {
        return id_ProgramaAcademico;
    }

    public void setId_ProgramaAcademico(Integer id_ProgramaAcademico) {
        this.id_ProgramaAcademico = id_ProgramaAcademico;
    }

    public Integer getId_TipoEvaluacion() {
        return id_TipoEvaluacion;
    }

    public void setId_TipoEvaluacion(Integer id_TipoEvaluacion) {
        this.id_TipoEvaluacion = id_TipoEvaluacion;
    }
}
