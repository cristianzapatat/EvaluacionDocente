package co.edu.eam.modelo.control;

import co.edu.eam.modelo.TipoEvaluacion;
import co.edu.eam.modelo.dto.TipoEvaluacionDTO;

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
public interface ITipoEvaluacionLogic {
    public List<TipoEvaluacion> getTipoEvaluacion() throws Exception;

    /**
         * Save an new TipoEvaluacion entity
         */
    public void saveTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    /**
         * Delete an existing TipoEvaluacion entity
         *
         */
    public void deleteTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    /**
        * Update an existing TipoEvaluacion entity
        *
        */
    public void updateTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    /**
         * Load an existing TipoEvaluacion entity
         *
         */
    public TipoEvaluacion getTipoEvaluacion(Integer id)
        throws Exception;

    public List<TipoEvaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoEvaluacion() throws Exception;

    public List<TipoEvaluacionDTO> getDataTipoEvaluacion()
        throws Exception;
}
