package co.edu.eam.modelo.control;

import co.edu.eam.dataaccess.dao.*;
import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.TipoEvaluacionDTO;
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
public class TipoEvaluacionLogic implements ITipoEvaluacionLogic {
    private static final Logger log = LoggerFactory.getLogger(TipoEvaluacionLogic.class);

    /**
     * DAO injected by Spring that manages TipoEvaluacion entities
     *
     */
    @EJB
    private ITipoEvaluacionDAO tipoEvaluacionDAO;

    /**
    * DAO injected by Spring that manages Evaluacion entities
    *
    */
    @EJB
    private IEvaluacionDAO evaluacionDAO;

    /**
    * DAO injected by Spring that manages Pregunta entities
    *
    */
    @EJB
    private IPreguntaDAO preguntaDAO;

    @TransactionAttribute
    public List<TipoEvaluacion> getTipoEvaluacion() throws Exception {
        log.debug("finding all TipoEvaluacion instances");

        List<TipoEvaluacion> list = new ArrayList<TipoEvaluacion>();

        try {
            list = tipoEvaluacionDAO.findAll();
        } catch (Exception e) {
            log.error("finding all TipoEvaluacion failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "TipoEvaluacion");
        } finally {
        }

        return list;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        log.debug("saving TipoEvaluacion instance");

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

            if (getTipoEvaluacion(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            tipoEvaluacionDAO.save(entity);
            log.debug("save TipoEvaluacion successful");
        } catch (Exception e) {
            log.error("save TipoEvaluacion failed", e);
            throw e;
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        log.debug("deleting TipoEvaluacion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("TipoEvaluacion");
        }

        if (entity.getId() == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        List<Evaluacion> evaluacions = null;
        List<Pregunta> preguntas = null;

        try {
            evaluacions = evaluacionDAO.findByProperty("tipoEvaluacion.id",
                    entity.getId());

            if (Utilities.validationsList(evaluacions) == true) {
                throw new ZMessManager().new DeletingException("evaluacions");
            }

            preguntas = preguntaDAO.findByProperty("tipoEvaluacion.id",
                    entity.getId());

            if (Utilities.validationsList(preguntas) == true) {
                throw new ZMessManager().new DeletingException("preguntas");
            }

            tipoEvaluacionDAO.deleteById(entity.getId());
            log.debug("delete TipoEvaluacion successful");
        } catch (Exception e) {
            log.error("delete TipoEvaluacion failed", e);
            throw e;
        } finally {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        log.debug("updating TipoEvaluacion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "TipoEvaluacion");
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

            tipoEvaluacionDAO.update(entity);

            log.debug("update TipoEvaluacion successful");
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @TransactionAttribute
    public List<TipoEvaluacionDTO> getDataTipoEvaluacion()
        throws Exception {
        try {
            List<TipoEvaluacion> tipoEvaluacion = tipoEvaluacionDAO.findAll();

            List<TipoEvaluacionDTO> tipoEvaluacionDTO = new ArrayList<TipoEvaluacionDTO>();

            for (TipoEvaluacion tipoEvaluacionTmp : tipoEvaluacion) {
                TipoEvaluacionDTO tipoEvaluacionDTO2 = new TipoEvaluacionDTO();

                tipoEvaluacionDTO2.setId(tipoEvaluacionTmp.getId());
                tipoEvaluacionDTO2.setNombre((tipoEvaluacionTmp.getNombre() != null)
                    ? tipoEvaluacionTmp.getNombre() : null);
                tipoEvaluacionDTO.add(tipoEvaluacionDTO2);
            }

            return tipoEvaluacionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @TransactionAttribute
    public TipoEvaluacion getTipoEvaluacion(Integer id)
        throws Exception {
        log.debug("getting TipoEvaluacion instance");

        TipoEvaluacion entity = null;

        try {
            entity = tipoEvaluacionDAO.findById(id);
        } catch (Exception e) {
            log.error("get TipoEvaluacion failed", e);
            throw new ZMessManager().new FindingException("TipoEvaluacion");
        } finally {
        }

        return entity;
    }

    @TransactionAttribute
    public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<TipoEvaluacion> entity = null;

        try {
            entity = tipoEvaluacionDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "TipoEvaluacion Count");
        } finally {
        }

        return entity;
    }

    @TransactionAttribute
    public Long findTotalNumberTipoEvaluacion() throws Exception {
        Long entity = null;

        try {
            entity = tipoEvaluacionDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "TipoEvaluacion Count");
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
    public List<TipoEvaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<TipoEvaluacion> list = new ArrayList<TipoEvaluacion>();
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
            list = tipoEvaluacionDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
