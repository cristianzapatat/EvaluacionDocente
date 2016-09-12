package co.edu.eam.modelo.control;

import co.edu.eam.dataaccess.dao.*;
import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.ProgramaAcademicoDTO;
import co.edu.eam.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Stateless
public class ProgramaAcademicoLogic implements IProgramaAcademicoLogic {
    private static final Logger log = LoggerFactory.getLogger(ProgramaAcademicoLogic.class);

    /**
     * DAO injected by Spring that manages ProgramaAcademico entities
     *
     */
    @EJB
    private IProgramaAcademicoDAO programaAcademicoDAO;

    /**
    * DAO injected by Spring that manages Evaluacion entities
    *
    */
    @EJB
    private IEvaluacionDAO evaluacionDAO;

    @TransactionAttribute
    public List<ProgramaAcademico> getProgramaAcademico()
        throws Exception {
        log.debug("finding all ProgramaAcademico instances");

        List<ProgramaAcademico> list = new ArrayList<ProgramaAcademico>();

        try {
            list = programaAcademicoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all ProgramaAcademico failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "ProgramaAcademico");
        } finally {
        }

        return list;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveProgramaAcademico(ProgramaAcademico entity)
        throws Exception {
        log.debug("saving ProgramaAcademico instance");

        try {
            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        30) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (getProgramaAcademico(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            programaAcademicoDAO.save(entity);
            log.debug("save ProgramaAcademico successful");
        } catch (Exception e) {
            log.error("save ProgramaAcademico failed", e);
            throw e;
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteProgramaAcademico(ProgramaAcademico entity)
        throws Exception {
        log.debug("deleting ProgramaAcademico instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "ProgramaAcademico");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        List<Evaluacion> evaluacions = null;

        try {
            evaluacions = evaluacionDAO.findByProperty("programaAcademico.id",
                    entity.getId());

            if (Utilities.validationsList(evaluacions) == true) {
                throw new ZMessManager().new DeletingException("evaluacions");
            }

            programaAcademicoDAO.deleteById(entity.getId());
            log.debug("delete ProgramaAcademico successful");
        } catch (Exception e) {
            log.error("delete ProgramaAcademico failed", e);
            throw e;
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateProgramaAcademico(ProgramaAcademico entity)
        throws Exception {
        log.debug("updating ProgramaAcademico instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "ProgramaAcademico");
            }

            if (entity.getId() == null) {
                throw new ZMessManager().new EmptyFieldException("id");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        30) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            programaAcademicoDAO.update(entity);

            log.debug("update ProgramaAcademico successful");
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @TransactionAttribute
    public List<ProgramaAcademicoDTO> getDataProgramaAcademico()
        throws Exception {
        try {
            List<ProgramaAcademico> programaAcademico = programaAcademicoDAO.findAll();

            List<ProgramaAcademicoDTO> programaAcademicoDTO = new ArrayList<ProgramaAcademicoDTO>();

            for (ProgramaAcademico programaAcademicoTmp : programaAcademico) {
                ProgramaAcademicoDTO programaAcademicoDTO2 = new ProgramaAcademicoDTO();

                programaAcademicoDTO2.setId(programaAcademicoTmp.getId());
                programaAcademicoDTO2.setNombre((programaAcademicoTmp.getNombre() != null)
                    ? programaAcademicoTmp.getNombre() : null);
                programaAcademicoDTO.add(programaAcademicoDTO2);
            }

            return programaAcademicoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @TransactionAttribute
    public ProgramaAcademico getProgramaAcademico(Integer id)
        throws Exception {
        log.debug("getting ProgramaAcademico instance");

        ProgramaAcademico entity = null;

        try {
            entity = programaAcademicoDAO.findById(id);
        } catch (Exception e) {
            log.error("get ProgramaAcademico failed", e);
            throw new ZMessManager().new FindingException("ProgramaAcademico");
        } finally {
        }

        return entity;
    }

    @TransactionAttribute
    public List<ProgramaAcademico> findPageProgramaAcademico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<ProgramaAcademico> entity = null;

        try {
            entity = programaAcademicoDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ProgramaAcademico Count");
        } finally {
        }

        return entity;
    }

    @TransactionAttribute
    public Long findTotalNumberProgramaAcademico() throws Exception {
        Long entity = null;

        try {
            entity = programaAcademicoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ProgramaAcademico Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @TransactionAttribute
    public List<ProgramaAcademico> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<ProgramaAcademico> list = new ArrayList<ProgramaAcademico>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = programaAcademicoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
