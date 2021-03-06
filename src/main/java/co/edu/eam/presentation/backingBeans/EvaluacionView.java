package co.edu.eam.presentation.backingBeans;

import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.EvaluacionDTO;
import co.edu.eam.presentation.businessDelegate.*;
import co.edu.eam.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class EvaluacionView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EvaluacionView.class);
	private InputText txtNombre;
	private InputText txtId_Periodo;
	private InputText txtId_ProgramaAcademico;
	private InputText txtId_TipoEvaluacion;
	private InputText txtId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<EvaluacionDTO> data;
	private EvaluacionDTO selectedEvaluacion;
	private Evaluacion entity;
	private boolean showDialog;

	private Integer idPrograma;
	private Map<String, Integer> programa;
	private List<ProgramaAcademico> listaProgramas;
	private List<ProgramaAcademico> listaEntidadProgramas;

	private Integer idPeriodo;
	private Map<String, Integer> periodo;
	private List<Periodo> listaPeriodos;
	private List<Periodo> listaEntidadPeriodos;

	@EJB
	private IBusinessDelegatorView businessDelegatorView;

	public EvaluacionView() {
		super();
	}

	@PostConstruct
	public void init() throws Exception {

		listaProgramas = new ArrayList<ProgramaAcademico>();
		programa = new HashMap<String, Integer>();
		listaEntidadProgramas = new ArrayList<ProgramaAcademico>();

		listaProgramas = businessDelegatorView.getProgramaAcademico();

		for (int i = 0; i < listaProgramas.size(); i++) {
			programa.put(listaProgramas.get(i).getNombre(), listaProgramas.get(i).getId());
		}

		listaPeriodos = new ArrayList<Periodo>();
		periodo = new HashMap<String, Integer>();
		listaEntidadPeriodos = new ArrayList<Periodo>();
		listaPeriodos = businessDelegatorView.getPeriodo();

		for (int i = 0; i < listaPeriodos.size(); i++) {
			periodo.put(listaPeriodos.get(i).getNombre(), listaPeriodos.get(i).getId());
		}

	}

	public void rowEventListener(RowEditEvent e) {
		try {
			EvaluacionDTO evaluacionDTO = (EvaluacionDTO) e.getObject();

			if (txtNombre == null) {
				txtNombre = new InputText();
			}

			txtNombre.setValue(evaluacionDTO.getNombre());

			if (txtId_Periodo == null) {
				txtId_Periodo = new InputText();
			}

			txtId_Periodo.setValue(evaluacionDTO.getId_Periodo());

			if (txtId_ProgramaAcademico == null) {
				txtId_ProgramaAcademico = new InputText();
			}

			txtId_ProgramaAcademico.setValue(evaluacionDTO.getId_ProgramaAcademico());

			if (txtId_TipoEvaluacion == null) {
				txtId_TipoEvaluacion = new InputText();
			}

			txtId_TipoEvaluacion.setValue(evaluacionDTO.getId_TipoEvaluacion());

			if (txtId == null) {
				txtId = new InputText();
			}

			txtId.setValue(evaluacionDTO.getId());

			Integer id = FacesUtils.checkInteger(txtId);
			entity = businessDelegatorView.getEvaluacion(id);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedEvaluacion = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedEvaluacion = null;

		if (txtNombre != null) {
			txtNombre.setValue(null);
			txtNombre.setDisabled(true);
		}

		if (txtId_Periodo != null) {
			txtId_Periodo.setValue(null);
			txtId_Periodo.setDisabled(true);
		}

		if (txtId_ProgramaAcademico != null) {
			txtId_ProgramaAcademico.setValue(null);
			txtId_ProgramaAcademico.setDisabled(true);
		}

		if (txtId_TipoEvaluacion != null) {
			txtId_TipoEvaluacion.setValue(null);
			txtId_TipoEvaluacion.setDisabled(true);
		}

		if (txtId != null) {
			txtId.setValue(null);
			txtId.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtId() {
		try {
			Integer id = FacesUtils.checkInteger(txtId);
			entity = (id != null) ? businessDelegatorView.getEvaluacion(id) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtNombre.setDisabled(false);
			txtId_Periodo.setDisabled(false);
			txtId_ProgramaAcademico.setDisabled(false);
			txtId_TipoEvaluacion.setDisabled(false);
			txtId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtNombre.setValue(entity.getNombre());
			txtNombre.setDisabled(false);
			txtId_Periodo.setValue(entity.getPeriodo().getId());
			txtId_Periodo.setDisabled(false);
			txtId_ProgramaAcademico.setValue(entity.getProgramaAcademico().getId());
			txtId_ProgramaAcademico.setDisabled(false);
			txtId_TipoEvaluacion.setValue(entity.getTipoEvaluacion().getId());
			txtId_TipoEvaluacion.setDisabled(false);
			txtId.setValue(entity.getId());
			txtId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));
		txtNombre.setValue(selectedEvaluacion.getNombre());
		txtNombre.setDisabled(false);
		txtId_Periodo.setValue(selectedEvaluacion.getId_Periodo());
		txtId_Periodo.setDisabled(false);
		txtId_ProgramaAcademico.setValue(selectedEvaluacion.getId_ProgramaAcademico());
		txtId_ProgramaAcademico.setDisabled(false);
		txtId_TipoEvaluacion.setValue(selectedEvaluacion.getId_TipoEvaluacion());
		txtId_TipoEvaluacion.setDisabled(false);
		txtId.setValue(selectedEvaluacion.getId());
		txtId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	/**
	 * <b>
	 * <p>
	 * Metodo que se encarga de gestionar el guardar de una <br>
	 * evaluacion el cual internamente se llama el metodo: <br>
	 * <ul>
	 * <li>action_create</li>
	 * </ul>
	 * </p>
	 * </b>
	 * 
	 * @author Daniel Giraldo <br>
	 *         Email: <pipe_635@hotmail.com> <br>
	 *         ${date}
	 * @return Cadena vacia
	 *
	 */
	public String action_save() {
		try {
			action_create();
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	/**
	 * <b>
	 * <p>
	 * Metodo que se encarga de crear la evaluacion el cual se le setea los
	 * datos a la Evaluacion <br>
	 * </p>
	 * </b>
	 * 
	 * @author Daniel Giraldo <br>
	 *         Email: <pipe_635@hotmail.com> <br>
	 *         ${date}
	 * @return Cadena vacia
	 * 
	 */
	public String action_create() {
		try {
			entity = new Evaluacion();
			Integer id = generarID();
		    	Integer tipo = 2;
			entity.setId(id);
			entity.setNombre(FacesUtils.checkString(txtNombre));
			entity.setPeriodo(((idPeriodo) != null) ? businessDelegatorView.getPeriodo((idPeriodo)) : null);
			entity.setProgramaAcademico(
					((idPrograma) != null) ? businessDelegatorView.getProgramaAcademico((idPrograma)) : null);
			entity.setTipoEvaluacion(((tipo) != null) ? businessDelegatorView.getTipoEvaluacion((tipo)) : null);
			businessDelegatorView.saveEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	/**
	 * <b>
	 * <p>
	 * Este Metodo se encarga de Generar un id aleatorio <br>
	 * que se va utilizar al moment de guardar la evaluacione<br>
	 * </p>
	 * </b>
	 * 
	 * @author Daniel Giraldo <br>
	 *         Email: <pipe_635@hotmail.com> <br>
	 *         ${date}
	 * @return Cadena vacia
	 * 
	 */
	public Integer generarID() {
		Random rdn = new Random();
		return rdn.nextInt(1000);
	}

	/**
	 * <b>
	 * <p>
	 * Metodo que se encarga de modificar la pregunta buscando la <br>
	 * pregunta por el id
	 * </p>
	 * </b>
	 * 
	 * @author Daniel Giraldo <br>
	 *         Email: <pipe_635@hotmail.com> <br>
	 *         ${date}
	 * @return Cadena vacia
	 */
	public String action_modify() {
		try {
			if (entity == null) {
				Integer id = new Integer(selectedEvaluacion.getId());
				entity = businessDelegatorView.getEvaluacion(id);
			}

			entity.setNombre(FacesUtils.checkString(txtNombre));
			entity.setPeriodo((FacesUtils.checkInteger(txtId_Periodo) != null)
					? businessDelegatorView.getPeriodo(FacesUtils.checkInteger(txtId_Periodo)) : null);
			entity.setProgramaAcademico((FacesUtils.checkInteger(txtId_ProgramaAcademico) != null)
					? businessDelegatorView.getProgramaAcademico(FacesUtils.checkInteger(txtId_ProgramaAcademico))
					: null);
			entity.setTipoEvaluacion((FacesUtils.checkInteger(txtId_TipoEvaluacion) != null)
					? businessDelegatorView.getTipoEvaluacion(FacesUtils.checkInteger(txtId_TipoEvaluacion)) : null);
			businessDelegatorView.updateEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));

			Integer id = new Integer(selectedEvaluacion.getId());
			entity = businessDelegatorView.getEvaluacion(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Integer id = FacesUtils.checkInteger(txtId);
			entity = businessDelegatorView.getEvaluacion(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	/**
	 * <b>
	 * <p>
	 * Metodo que se encarga de borrar la pregunta por su id <br>
	 * </p>
	 * </b>
	 * 
	 * @author Daniel Giraldo <br>
	 *         Email: <pipe_635@hotmail.com> <br>
	 *         ${date}
	 * @return
	 * 
	 */
	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));

			Integer id = new Integer(selectedEvaluacion.getId());
			entity = businessDelegatorView.getEvaluacion(id);
			businessDelegatorView.deleteEvaluacion(entity);
			data.remove(selectedEvaluacion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Integer id, String nombre, Integer id_Periodo, Integer id_ProgramaAcademico,
			Integer id_TipoEvaluacion) throws Exception {
		try {
			entity.setNombre(FacesUtils.checkString(nombre));
			businessDelegatorView.updateEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("EvaluacionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtId_Periodo() {
		return txtId_Periodo;
	}

	public void setTxtId_Periodo(InputText txtId_Periodo) {
		this.txtId_Periodo = txtId_Periodo;
	}

	public InputText getTxtId_ProgramaAcademico() {
		return txtId_ProgramaAcademico;
	}

	public void setTxtId_ProgramaAcademico(InputText txtId_ProgramaAcademico) {
		this.txtId_ProgramaAcademico = txtId_ProgramaAcademico;
	}

	public InputText getTxtId_TipoEvaluacion() {
		return txtId_TipoEvaluacion;
	}

	public void setTxtId_TipoEvaluacion(InputText txtId_TipoEvaluacion) {
		this.txtId_TipoEvaluacion = txtId_TipoEvaluacion;
	}

	public InputText getTxtId() {
		return txtId;
	}

	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}

	public List<EvaluacionDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataEvaluacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<EvaluacionDTO> evaluacionDTO) {
		this.data = evaluacionDTO;
	}

	public EvaluacionDTO getSelectedEvaluacion() {
		return selectedEvaluacion;
	}

	public void setSelectedEvaluacion(EvaluacionDTO evaluacion) {
		this.selectedEvaluacion = evaluacion;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Map<String, Integer> getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Map<String, Integer> periodo) {
		this.periodo = periodo;
	}

	public List<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public List<Periodo> getListaEntidadPeriodos() {
		return listaEntidadPeriodos;
	}

	public void setListaEntidadPeriodos(List<Periodo> listaEntidadPeriodos) {
		this.listaEntidadPeriodos = listaEntidadPeriodos;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Map<String, Integer> getPrograma() {
		return programa;
	}

	public void setPrograma(Map<String, Integer> programa) {
		this.programa = programa;
	}

	public List<ProgramaAcademico> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(List<ProgramaAcademico> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	public List<ProgramaAcademico> getListaEntidadProgramas() {
		return listaEntidadProgramas;
	}

	public void setListaEntidadProgramas(List<ProgramaAcademico> listaEntidadProgramas) {
		this.listaEntidadProgramas = listaEntidadProgramas;
	}

}
