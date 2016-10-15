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
import co.edu.eam.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients
 * and business services. The Business Delegate hides the underlying
 * implementation details of the business service, such as lookup and access
 * details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides
 * an abstraction for, and thus hides, the implementation of the business
 * services. Using a Business Delegate reduces the coupling between
 * presentation-tier clients and the system's business services. Depending on
 * the implementation strategy, the Business Delegate may shield clients from
 * possible volatility in the implementation of the business service API.
 * Potentially, this reduces the number of changes that must be made to the
 * presentation-tier client code when the business service API or its underlying
 * implementation changes.
 *
 * However, interface methods in the Business Delegate may still require
 * modification if the underlying business service API changes. Admittedly,
 * though, it is more likely that changes will be made to the business service
 * rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the
 * business layer causes additional upfront work in return for future gains.
 * However, using this pattern or its strategies results in only a small amount
 * of additional upfront work and provides considerable benefits. The main
 * benefit is hiding the details of the underlying service. For example, the
 * client can become transparent to naming and lookup services. The Business
 * Delegate also handles the exceptions from the business services, such as
 * java.rmi.Remote exceptions, Java Messages Service (JMS) exceptions and so on.
 * The Business Delegate may intercept such service level exceptions and
 * generate application level exceptions instead. Application level exceptions
 * are easier to handle by the clients, and may be user friendly. The Business
 * Delegate may also transparently perform any retry or recovery operations
 * necessary in the event of a service failure without exposing the client to
 * the problem until it is determined that the problem is not resolvable. These
 * gains present a compelling reason to use the pattern.
 *
 * Another benefit is that the delegate may cache results and references to
 * remote business services. Caching can significantly improve performance,
 * because it limits unnecessary and potentially costly round trips over the
 * network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup
 * Service is responsible for hiding the underlying implementation details of
 * the business service lookup code. The Lookup Service may be written as part
 * of the Delegate, but we recommend that it be implemented as a separate
 * component, as outlined in the Service Locator pattern (See "Service Locator"
 * on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is
 * a one-to-one relationship between the two. This one-to-one relationship
 * exists because logic that might have been encapsulated in a Business Delegate
 * relating to its interaction with multiple business services (creating a
 * one-to-many relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce
 * coupling between other tiers, not simply the presentation and the business
 * tiers.
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Stateless
public class BusinessDelegatorView implements IBusinessDelegatorView {
	private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
	@EJB
	private IEvaluacionLogic evaluacionLogic;
	@EJB
	private IPeriodoLogic periodoLogic;
	@EJB
	private IPreguntaLogic preguntaLogic;
	@EJB
	private IPresentacionLogic presentacionLogic;
	@EJB
	private IProgramaAcademicoLogic programaAcademicoLogic;
	@EJB
	private IRespuestaLogic respuestaLogic;
	@EJB
	private ITipoEvaluacionLogic tipoEvaluacionLogic;

	public List<Evaluacion> getEvaluacion() throws Exception {
		return evaluacionLogic.getEvaluacion();
	}

	public void saveEvaluacion(Evaluacion entity) throws Exception {
		evaluacionLogic.saveEvaluacion(entity);
	}

	public void deleteEvaluacion(Evaluacion entity) throws Exception {
		evaluacionLogic.deleteEvaluacion(entity);
	}

	public void updateEvaluacion(Evaluacion entity) throws Exception {
		evaluacionLogic.updateEvaluacion(entity);
	}

	public Evaluacion getEvaluacion(Integer id) throws Exception {
		Evaluacion evaluacion = null;

		try {
			evaluacion = evaluacionLogic.getEvaluacion(id);
		} catch (Exception e) {
			throw e;
		}

		return evaluacion;
	}

	public List<Evaluacion> findByCriteriaInEvaluacion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return evaluacionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<Evaluacion> findPageEvaluacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return evaluacionLogic.findPageEvaluacion(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberEvaluacion() throws Exception {
		return evaluacionLogic.findTotalNumberEvaluacion();
	}

	public List<EvaluacionDTO> getDataEvaluacion() throws Exception {
		return evaluacionLogic.getDataEvaluacion();
	}

	public List<Periodo> getPeriodo() throws Exception {
		return periodoLogic.getPeriodo();
	}

	public void savePeriodo(Periodo entity) throws Exception {
		periodoLogic.savePeriodo(entity);
	}

	public void deletePeriodo(Periodo entity) throws Exception {
		periodoLogic.deletePeriodo(entity);
	}

	public void updatePeriodo(Periodo entity) throws Exception {
		periodoLogic.updatePeriodo(entity);
	}

	public Periodo getPeriodo(Integer id) throws Exception {
		Periodo periodo = null;

		try {
			periodo = periodoLogic.getPeriodo(id);
		} catch (Exception e) {
			throw e;
		}

		return periodo;
	}

	public List<Periodo> findByCriteriaInPeriodo(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return periodoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<Periodo> findPagePeriodo(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return periodoLogic.findPagePeriodo(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberPeriodo() throws Exception {
		return periodoLogic.findTotalNumberPeriodo();
	}

	public List<PeriodoDTO> getDataPeriodo() throws Exception {
		return periodoLogic.getDataPeriodo();
	}

	public List<Pregunta> getPregunta() throws Exception {
		return preguntaLogic.getPregunta();
	}

	public void savePregunta(Pregunta entity) throws Exception {
		preguntaLogic.savePregunta(entity);
	}

	public void deletePregunta(Pregunta entity) throws Exception {
		preguntaLogic.deletePregunta(entity);
	}

	public void updatePregunta(Pregunta entity) throws Exception {
		preguntaLogic.updatePregunta(entity);
	}

	public Pregunta getPregunta(Integer id) throws Exception {
		Pregunta pregunta = null;

		try {
			pregunta = preguntaLogic.getPregunta(id);
		} catch (Exception e) {
			throw e;
		}

		return pregunta;
	}

	public List<Pregunta> findByCriteriaInPregunta(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return preguntaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<Pregunta> findPagePregunta(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return preguntaLogic.findPagePregunta(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberPregunta() throws Exception {
		return preguntaLogic.findTotalNumberPregunta();
	}

	public List<PreguntaDTO> getDataPregunta() throws Exception {
		return preguntaLogic.getDataPregunta();
	}

	public List<Presentacion> getPresentacion() throws Exception {
		return presentacionLogic.getPresentacion();
	}

	public void savePresentacion(Presentacion entity) throws Exception {
		presentacionLogic.savePresentacion(entity);
	}

	public void deletePresentacion(Presentacion entity) throws Exception {
		presentacionLogic.deletePresentacion(entity);
	}

	public void updatePresentacion(Presentacion entity) throws Exception {
		presentacionLogic.updatePresentacion(entity);
	}

	public Presentacion getPresentacion(Integer id) throws Exception {
		Presentacion presentacion = null;

		try {
			presentacion = presentacionLogic.getPresentacion(id);
		} catch (Exception e) {
			throw e;
		}

		return presentacion;
	}

	public List<Presentacion> findByCriteriaInPresentacion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return presentacionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<Presentacion> findPagePresentacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return presentacionLogic.findPagePresentacion(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberPresentacion() throws Exception {
		return presentacionLogic.findTotalNumberPresentacion();
	}

	public List<PresentacionDTO> getDataPresentacion() throws Exception {
		return presentacionLogic.getDataPresentacion();
	}

	public List<ProgramaAcademico> getProgramaAcademico() throws Exception {
		return programaAcademicoLogic.getProgramaAcademico();
	}

	public void saveProgramaAcademico(ProgramaAcademico entity) throws Exception {
		programaAcademicoLogic.saveProgramaAcademico(entity);
	}

	public void deleteProgramaAcademico(ProgramaAcademico entity) throws Exception {
		programaAcademicoLogic.deleteProgramaAcademico(entity);
	}

	public void updateProgramaAcademico(ProgramaAcademico entity) throws Exception {
		programaAcademicoLogic.updateProgramaAcademico(entity);
	}

	public ProgramaAcademico getProgramaAcademico(Integer id) throws Exception {
		ProgramaAcademico programaAcademico = null;

		try {
			programaAcademico = programaAcademicoLogic.getProgramaAcademico(id);
		} catch (Exception e) {
			throw e;
		}

		return programaAcademico;
	}

	public List<ProgramaAcademico> findByCriteriaInProgramaAcademico(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return programaAcademicoLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<ProgramaAcademico> findPageProgramaAcademico(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return programaAcademicoLogic.findPageProgramaAcademico(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberProgramaAcademico() throws Exception {
		return programaAcademicoLogic.findTotalNumberProgramaAcademico();
	}

	public List<ProgramaAcademicoDTO> getDataProgramaAcademico() throws Exception {
		return programaAcademicoLogic.getDataProgramaAcademico();
	}

	public List<Respuesta> getRespuesta() throws Exception {
		return respuestaLogic.getRespuesta();
	}

	public void saveRespuesta(Respuesta entity) throws Exception {
		respuestaLogic.saveRespuesta(entity);
	}

	public void deleteRespuesta(Respuesta entity) throws Exception {
		respuestaLogic.deleteRespuesta(entity);
	}

	public void updateRespuesta(Respuesta entity) throws Exception {
		respuestaLogic.updateRespuesta(entity);
	}

	public Respuesta getRespuesta(Integer id) throws Exception {
		Respuesta respuesta = null;

		try {
			respuesta = respuestaLogic.getRespuesta(id);
		} catch (Exception e) {
			throw e;
		}

		return respuesta;
	}

	public List<Respuesta> findByCriteriaInRespuesta(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return respuestaLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<Respuesta> findPageRespuesta(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		return respuestaLogic.findPageRespuesta(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberRespuesta() throws Exception {
		return respuestaLogic.findTotalNumberRespuesta();
	}

	public List<RespuestaDTO> getDataRespuesta() throws Exception {
		return respuestaLogic.getDataRespuesta();
	}

	public List<TipoEvaluacion> getTipoEvaluacion() throws Exception {
		return tipoEvaluacionLogic.getTipoEvaluacion();
	}

	public void saveTipoEvaluacion(TipoEvaluacion entity) throws Exception {
		tipoEvaluacionLogic.saveTipoEvaluacion(entity);
	}

	public void deleteTipoEvaluacion(TipoEvaluacion entity) throws Exception {
		tipoEvaluacionLogic.deleteTipoEvaluacion(entity);
	}

	public void updateTipoEvaluacion(TipoEvaluacion entity) throws Exception {
		tipoEvaluacionLogic.updateTipoEvaluacion(entity);
	}

	public TipoEvaluacion getTipoEvaluacion(Integer id) throws Exception {
		TipoEvaluacion tipoEvaluacion = null;

		try {
			tipoEvaluacion = tipoEvaluacionLogic.getTipoEvaluacion(id);
		} catch (Exception e) {
			throw e;
		}

		return tipoEvaluacion;
	}

	public List<TipoEvaluacion> findByCriteriaInTipoEvaluacion(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		return tipoEvaluacionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
	}

	public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		return tipoEvaluacionLogic.findPageTipoEvaluacion(sortColumnName, sortAscending, startRow, maxResults);
	}

	public Long findTotalNumberTipoEvaluacion() throws Exception {
		return tipoEvaluacionLogic.findTotalNumberTipoEvaluacion();
	}

	public List<TipoEvaluacionDTO> getDataTipoEvaluacion() throws Exception {
		return tipoEvaluacionLogic.getDataTipoEvaluacion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.eam.presentation.businessDelegate.IBusinessDelegatorView#
	 * getDataPregunta(java.lang.String)
	 */

	public List<PreguntaDTO> getDataPregunta(int tipoEvaluacion, int estado) throws Exception {

		return preguntaLogic.getDataPregunta(tipoEvaluacion, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.eam.presentation.businessDelegate.IBusinessDelegatorView#
	 * getDataRespuesta(java.lang.String)
	 */
	public List<RespuestaDTO> getDataRespuesta(Integer idPregunta, Integer idPresentacion) throws Exception {

		return respuestaLogic.getDataRespuesta(idPregunta,idPresentacion);
	}
}
