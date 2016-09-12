package co.edu.eam.modelo.control;

import co.edu.eam.modelo.Periodo;
import co.edu.eam.modelo.dto.PeriodoDTO;

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
public interface IPeriodoLogic {
    public List<Periodo> getPeriodo() throws Exception;

    /**
         * Save an new Periodo entity
         */
    public void savePeriodo(Periodo entity) throws Exception;

    /**
         * Delete an existing Periodo entity
         *
         */
    public void deletePeriodo(Periodo entity) throws Exception;

    /**
        * Update an existing Periodo entity
        *
        */
    public void updatePeriodo(Periodo entity) throws Exception;

    /**
         * Load an existing Periodo entity
         *
         */
    public Periodo getPeriodo(Integer id) throws Exception;

    public List<Periodo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Periodo> findPagePeriodo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPeriodo() throws Exception;

    public List<PeriodoDTO> getDataPeriodo() throws Exception;
}
