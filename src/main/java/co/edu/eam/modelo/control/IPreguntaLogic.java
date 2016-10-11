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
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
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

    public List<Pregunta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pregunta> findPagePregunta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPregunta() throws Exception;

    public List<PreguntaDTO> getDataPregunta() throws Exception;
	
	/**
     * Obtiene las preguntas de la base de datos para enviarlas a BusinessDelgator
     * @param whereCondition condicion que servira para listar las preguntas que se encuentren en la base de datos
     * @return una lista con todas las preguntas que se mostraran posteriormente en la vista
     * @throws Exception
     */
    public List<PreguntaDTO> getDataPregunta(String whereCondition ) throws Exception;
}
