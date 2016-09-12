package co.edu.eam.modelo.control;

import co.edu.eam.modelo.ProgramaAcademico;
import co.edu.eam.modelo.dto.ProgramaAcademicoDTO;

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
public interface IProgramaAcademicoLogic {
    public List<ProgramaAcademico> getProgramaAcademico()
        throws Exception;

    /**
         * Save an new ProgramaAcademico entity
         */
    public void saveProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    /**
         * Delete an existing ProgramaAcademico entity
         *
         */
    public void deleteProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    /**
        * Update an existing ProgramaAcademico entity
        *
        */
    public void updateProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    /**
         * Load an existing ProgramaAcademico entity
         *
         */
    public ProgramaAcademico getProgramaAcademico(Integer id)
        throws Exception;

    public List<ProgramaAcademico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ProgramaAcademico> findPageProgramaAcademico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberProgramaAcademico() throws Exception;

    public List<ProgramaAcademicoDTO> getDataProgramaAcademico()
        throws Exception;
}
