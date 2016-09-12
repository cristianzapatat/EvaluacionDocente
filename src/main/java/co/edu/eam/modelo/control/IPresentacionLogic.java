package co.edu.eam.modelo.control;

import co.edu.eam.modelo.Presentacion;
import co.edu.eam.modelo.dto.PresentacionDTO;

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
public interface IPresentacionLogic {
    public List<Presentacion> getPresentacion() throws Exception;

    /**
         * Save an new Presentacion entity
         */
    public void savePresentacion(Presentacion entity) throws Exception;

    /**
         * Delete an existing Presentacion entity
         *
         */
    public void deletePresentacion(Presentacion entity)
        throws Exception;

    /**
        * Update an existing Presentacion entity
        *
        */
    public void updatePresentacion(Presentacion entity)
        throws Exception;

    /**
         * Load an existing Presentacion entity
         *
         */
    public Presentacion getPresentacion(Integer id) throws Exception;

    public List<Presentacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Presentacion> findPagePresentacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPresentacion() throws Exception;

    public List<PresentacionDTO> getDataPresentacion()
        throws Exception;
}
