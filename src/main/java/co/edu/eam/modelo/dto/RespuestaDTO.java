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
public class RespuestaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaDTO.class);
    private Integer id;
    private Integer nota;
    private Integer id_Pregunta;
    private Integer id_Presentacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getId_Pregunta() {
        return id_Pregunta;
    }

    public void setId_Pregunta(Integer id_Pregunta) {
        this.id_Pregunta = id_Pregunta;
    }

    public Integer getId_Presentacion() {
        return id_Presentacion;
    }

    public void setId_Presentacion(Integer id_Presentacion) {
        this.id_Presentacion = id_Presentacion;
    }
}
