package co.edu.eam.presentation.businessDelegate;

import co.edu.eam.modelo.Evaluacion;
import co.edu.eam.modelo.Periodo;
import co.edu.eam.modelo.Pregunta;
import co.edu.eam.modelo.Presentacion;
import co.edu.eam.modelo.ProgramaAcademico;
import co.edu.eam.modelo.Respuesta;
import co.edu.eam.modelo.TipoEvaluacion;
import co.edu.eam.modelo.control.EvaluacionLogic;
import co.edu.eam.modelo.control.IEvaluacionLogic;
import co.edu.eam.modelo.control.IPeriodoLogic;
import co.edu.eam.modelo.control.IPreguntaLogic;
import co.edu.eam.modelo.control.IPresentacionLogic;
import co.edu.eam.modelo.control.IProgramaAcademicoLogic;
import co.edu.eam.modelo.control.IRespuestaLogic;
import co.edu.eam.modelo.control.ITipoEvaluacionLogic;
import co.edu.eam.modelo.control.PeriodoLogic;
import co.edu.eam.modelo.control.PreguntaLogic;
import co.edu.eam.modelo.control.PresentacionLogic;
import co.edu.eam.modelo.control.ProgramaAcademicoLogic;
import co.edu.eam.modelo.control.RespuestaLogic;
import co.edu.eam.modelo.control.TipoEvaluacionLogic;
import co.edu.eam.modelo.dto.EvaluacionDTO;
import co.edu.eam.modelo.dto.PeriodoDTO;
import co.edu.eam.modelo.dto.PreguntaDTO;
import co.edu.eam.modelo.dto.PresentacionDTO;
import co.edu.eam.modelo.dto.ProgramaAcademicoDTO;
import co.edu.eam.modelo.dto.RespuestaDTO;
import co.edu.eam.modelo.dto.TipoEvaluacionDTO;

import java.math.BigDecimal;

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
public interface IBusinessDelegatorView {
    public List<Evaluacion> getEvaluacion() throws Exception;

    public void saveEvaluacion(Evaluacion entity) throws Exception;

    public void deleteEvaluacion(Evaluacion entity) throws Exception;

    public void updateEvaluacion(Evaluacion entity) throws Exception;

    public Evaluacion getEvaluacion(Integer id) throws Exception;

    public List<Evaluacion> findByCriteriaInEvaluacion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Evaluacion> findPageEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEvaluacion() throws Exception;

    public List<EvaluacionDTO> getDataEvaluacion() throws Exception;

    public List<Periodo> getPeriodo() throws Exception;

    public void savePeriodo(Periodo entity) throws Exception;

    public void deletePeriodo(Periodo entity) throws Exception;

    public void updatePeriodo(Periodo entity) throws Exception;

    public Periodo getPeriodo(Integer id) throws Exception;

    public List<Periodo> findByCriteriaInPeriodo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Periodo> findPagePeriodo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPeriodo() throws Exception;

    public List<PeriodoDTO> getDataPeriodo() throws Exception;

    public List<Pregunta> getPregunta() throws Exception;

    public void savePregunta(Pregunta entity) throws Exception;

    public void deletePregunta(Pregunta entity) throws Exception;

    public void updatePregunta(Pregunta entity) throws Exception;

    public Pregunta getPregunta(Integer id) throws Exception;

    public List<Pregunta> findByCriteriaInPregunta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pregunta> findPagePregunta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPregunta() throws Exception;

    public List<PreguntaDTO> getDataPregunta() throws Exception;

    public List<Presentacion> getPresentacion() throws Exception;

    public void savePresentacion(Presentacion entity) throws Exception;

    public void deletePresentacion(Presentacion entity)
        throws Exception;

    public void updatePresentacion(Presentacion entity)
        throws Exception;

    public Presentacion getPresentacion(Integer id) throws Exception;

    public List<Presentacion> findByCriteriaInPresentacion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Presentacion> findPagePresentacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPresentacion() throws Exception;

    public List<PresentacionDTO> getDataPresentacion()
        throws Exception;

    public List<ProgramaAcademico> getProgramaAcademico()
        throws Exception;

    public void saveProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    public void deleteProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    public void updateProgramaAcademico(ProgramaAcademico entity)
        throws Exception;

    public ProgramaAcademico getProgramaAcademico(Integer id)
        throws Exception;

    public List<ProgramaAcademico> findByCriteriaInProgramaAcademico(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<ProgramaAcademico> findPageProgramaAcademico(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberProgramaAcademico() throws Exception;

    public List<ProgramaAcademicoDTO> getDataProgramaAcademico()
        throws Exception;

    public List<Respuesta> getRespuesta() throws Exception;

    public void saveRespuesta(Respuesta entity) throws Exception;

    public void deleteRespuesta(Respuesta entity) throws Exception;

    public void updateRespuesta(Respuesta entity) throws Exception;

    public Respuesta getRespuesta(Integer id) throws Exception;

    public List<Respuesta> findByCriteriaInRespuesta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Respuesta> findPageRespuesta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRespuesta() throws Exception;

    public List<RespuestaDTO> getDataRespuesta() throws Exception;

    public List<TipoEvaluacion> getTipoEvaluacion() throws Exception;

    public void saveTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public void deleteTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public void updateTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public TipoEvaluacion getTipoEvaluacion(Integer id)
        throws Exception;

    public List<TipoEvaluacion> findByCriteriaInTipoEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoEvaluacion() throws Exception;

    public List<TipoEvaluacionDTO> getDataTipoEvaluacion()throws Exception;
}
