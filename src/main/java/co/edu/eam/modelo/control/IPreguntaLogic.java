package co.edu.eam.modelo.control;

import co.edu.eam.modelo.Pregunta;
import co.edu.eam.modelo.dto.PreguntaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Local
public interface IPreguntaLogic {
	public List<Pregunta> getPregunta() throws Exception;

	/**
	 * Save an new Pregunta entity
	 */
	public void savePregunta(Pregunta entity) throws Exception;

	/**
	 * Delete an existing Pregunta entity
	 *
	 */
	public void deletePregunta(Pregunta entity) throws Exception;

	/**
	 * Update an existing Pregunta entity
	 *
	 */
	public void updatePregunta(Pregunta entity) throws Exception;

	/**
	 * Load an existing Pregunta entity
	 *
	 */
	public Pregunta getPregunta(Integer id) throws Exception;

	public List<Pregunta> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception;

	public List<Pregunta> findPagePregunta(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception;

	public Long findTotalNumberPregunta() throws Exception;

	public List<PreguntaDTO> getDataPregunta() throws Exception;

	/**
	 * 
	 * <p>
	 * <b>Obtiene las preguntas de la base de datos para mostrarlas en la vista
	 * de respuestas</b>
	 * </p>
	 * <br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * <br/>
	 * 
	 * @author EAM <br/>
	 *         Jefry Londoño Acosta <br/>
	 *         Email: jjmb2789@gmail.com <br/>
	 * @author EAM <br/>
	 *         Alvaro Javier Lotero <br/>
	 *         Email: <br/>
	 * @author EAM <br/>
	 *         Santiago Idarraga <br/>
	 *         <br/>
	 *         14/10/2016
	 * @version 1.0
	 * @param tipoEvaluacion
	 *            el identificador de que tipo de evaluacion es el que se va a
	 *            realizar, con el fin de cargar las preguntas de acuerdo a este
	 *            tipo
	 * @param estado
	 *            se refiera a si la pregunta se encuentra activa o inactiva
	 * @return una lista con todas las preguntas que se mostraran posteriormente
	 *         en la vista
	 * @throws Exception
	 */
	public List<PreguntaDTO> getDataPregunta(int tipoEvaluacion, int estado) throws Exception;
}
