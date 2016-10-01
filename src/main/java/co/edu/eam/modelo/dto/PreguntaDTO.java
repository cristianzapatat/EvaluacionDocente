package co.edu.eam.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class PreguntaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PreguntaDTO.class);
	private String estado;
	private Integer id;
	private String pregunta;
	private Integer id_Periodo;
	private Integer id_TipoEvaluacion;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Integer getId_Periodo() {

		return id_Periodo;
	}

	public void setId_Periodo(Integer id_Periodo) {
		this.id_Periodo = id_Periodo;
	}

	public Integer getId_TipoEvaluacion() {

		return id_TipoEvaluacion;
	}

	public void setId_TipoEvaluacion(Integer id_TipoEvaluacion) {
		this.id_TipoEvaluacion = id_TipoEvaluacion;
	}
}
