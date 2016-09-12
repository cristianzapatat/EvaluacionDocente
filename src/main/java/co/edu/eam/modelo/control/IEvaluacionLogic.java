package co.edu.eam.modelo.control;

import co.edu.eam.modelo.Evaluacion;
import co.edu.eam.modelo.dto.EvaluacionDTO;

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
public interface IEvaluacionLogic {
    public List<Evaluacion> getEvaluacion() throws Exception;

    /**
         * Save an new Evaluacion entity
         */
    public void saveEvaluacion(Evaluacion entity) throws Exception;

    /**
         * Delete an existing Evaluacion entity
         *
         */
    public void deleteEvaluacion(Evaluacion entity) throws Exception;

    /**
        * Update an existing Evaluacion entity
        *
        */
    public void updateEvaluacion(Evaluacion entity) throws Exception;

    /**
         * Load an existing Evaluacion entity
         *
         */
    public Evaluacion getEvaluacion(Integer id) throws Exception;

    public List<Evaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Evaluacion> findPageEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEvaluacion() throws Exception;

    public List<EvaluacionDTO> getDataEvaluacion() throws Exception;
}
