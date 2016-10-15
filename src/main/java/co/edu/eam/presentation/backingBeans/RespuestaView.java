package co.edu.eam.presentation.backingBeans;

import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.RespuestaDTO;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

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
public class RespuestaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RespuestaView.class);
	private InputText txtNota;
	private InputText txtId_Pregunta;
	private InputText txtId_Presentacion;
	private InputText txtId;

	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<RespuestaDTO> data;
	private RespuestaDTO selectedRespuesta;
	private Respuesta entity;
	private boolean showDialog;

	// nota refiere al tipo de calificacion de una pregunta
	private Integer nota;

	@EJB
	private IBusinessDelegatorView businessDelegatorView;

	public RespuestaView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			RespuestaDTO respuestaDTO = (RespuestaDTO) e.getObject();

			if (txtNota == null) {
				txtNota = new InputText();
			}

			txtNota.setValue(respuestaDTO.getNota());

			if (txtId_Pregunta == null) {
				txtId_Pregunta = new InputText();
			}

			txtId_Pregunta.setValue(respuestaDTO.getId_Pregunta());

			if (txtId_Presentacion == null) {
				txtId_Presentacion = new InputText();
			}

			txtId_Presentacion.setValue(respuestaDTO.getId_Presentacion());

			if (txtId == null) {
				txtId = new InputText();
			}

			txtId.setValue(respuestaDTO.getId());

			Integer id = FacesUtils.checkInteger(txtId);
			entity = businessDelegatorView.getRespuesta(id);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedRespuesta = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedRespuesta = null;

		if (txtNota != null) {
			txtNota.setValue(null);
			txtNota.setDisabled(true);
		}

		if (txtId_Pregunta != null) {
			txtId_Pregunta.setValue(null);
			txtId_Pregunta.setDisabled(true);
		}

		if (txtId_Presentacion != null) {
			txtId_Presentacion.setValue(null);
			txtId_Presentacion.setDisabled(true);
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

	/**
	 * 
	 * <p>
	 * <b>Este método se encarga de llamar al metodo que buscara la respuesta a
	 * modificar su nota, y ademas procede a realizar el update en la base de
	 * datos</b>
	 * </p>
	 * <br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * <br/>
	 * 
	 * @author EAM <br/>
	 *         Jefry Londoño Acosta <br/>
	 *         Email: jjmb2789@gmail.com <br/>
	 * @author EAM <br/>
	 *         Alvaro Javier Lotero <br/>
	 *         Email: <br/>
	 * @author EAM <br/>
	 *         Santiago Idarraga <br/>
	 *         <br/>
	 *         14/10/2016
	 * @version 1.0
	 */
	public void find_Entity() {
		try {
			Integer id = FacesUtils.checkInteger(txtId_Pregunta);
			List<RespuestaDTO> info = new LinkedList<>();
			info = getDataRespuesta(id, 1);
			data = null;
			entity = new Respuesta();
			if (!info.isEmpty()) {
				entity.setId(info.get(0).getId());
				entity.setPregunta((info.get(0).getId_Pregunta() != null)
						? businessDelegatorView.getPregunta(info.get(0).getId_Pregunta()) : null);
				entity.setPresentacion((info.get(0).getId_Presentacion() != null)
						? businessDelegatorView.getPresentacion(info.get(0).getId_Presentacion()) : null);
				entity.setNota(nota);
				businessDelegatorView.updateRespuesta(entity);

			}
		} catch (Exception e) {
			entity = null;
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * <b>Metodo que traera la informacion del objeto Respuesta, segun el id de
	 * la pregunta y el id de la presentación que en ese momento se este
	 * dando</b>
	 * </p>
	 * <br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * <br/>
	 * 
	 * @author EAM <br/>
	 *         Jefry Londoño Acosta <br/>
	 *         Email: jjmb2789@gmail.com <br/>
	 * @author EAM <br/>
	 *         Alvaro Javier Lotero <br/>
	 *         Email: <br/>
	 * @author EAM <br/>
	 *         Santiago Idarraga <br/>
	 *         <br/>
	 *         14/10/2016
	 * @version 1.0
	 * @param idPregunta
	 *            identificador de la pregunta
	 * @param idPresentacion
	 *            identificador de la presentacion
	 * @return una lista con el objeto que se va a modificar
	 */
	public List<RespuestaDTO> getDataRespuesta(Integer idPregunta, Integer idPresentacion) {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataRespuesta(idPregunta, idPresentacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void listener_txtId() {
		try {
			Integer id = FacesUtils.checkInteger(txtId);
			entity = (id != null) ? businessDelegatorView.getRespuesta(id) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtNota.setDisabled(false);
			txtId_Pregunta.setDisabled(false);
			txtId_Presentacion.setDisabled(false);
			txtId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtNota.setValue(entity.getNota());
			txtNota.setDisabled(false);
			txtId_Pregunta.setValue(entity.getPregunta().getId());
			txtId_Pregunta.setDisabled(false);
			txtId_Presentacion.setValue(entity.getPresentacion().getId());
			txtId_Presentacion.setDisabled(false);
			txtId.setValue(entity.getId());
			txtId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedRespuesta = (RespuestaDTO) (evt.getComponent().getAttributes().get("selectedRespuesta"));
		txtNota.setValue(selectedRespuesta.getNota());
		txtNota.setDisabled(false);
		txtId_Pregunta.setValue(selectedRespuesta.getId_Pregunta());
		txtId_Pregunta.setDisabled(false);
		txtId_Presentacion.setValue(selectedRespuesta.getId_Presentacion());
		txtId_Presentacion.setDisabled(false);
		txtId.setValue(selectedRespuesta.getId());
		txtId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedRespuesta == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}

			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_create() {
		try {
			entity = new Respuesta();

			Integer id = FacesUtils.checkInteger(txtId);

			entity.setId(id);
			entity.setNota(FacesUtils.checkInteger(txtNota));
			entity.setPregunta((FacesUtils.checkInteger(txtId_Pregunta) != null)
					? businessDelegatorView.getPregunta(FacesUtils.checkInteger(txtId_Pregunta)) : null);
			entity.setPresentacion((FacesUtils.checkInteger(txtId_Presentacion) != null)
					? businessDelegatorView.getPresentacion(FacesUtils.checkInteger(txtId_Presentacion)) : null);
			businessDelegatorView.saveRespuesta(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Integer id = new Integer(selectedRespuesta.getId());
				entity = businessDelegatorView.getRespuesta(id);
			}

			entity.setNota(FacesUtils.checkInteger(txtNota));
			entity.setPregunta((FacesUtils.checkInteger(txtId_Pregunta) != null)
					? businessDelegatorView.getPregunta(FacesUtils.checkInteger(txtId_Pregunta)) : null);
			entity.setPresentacion((FacesUtils.checkInteger(txtId_Presentacion) != null)
					? businessDelegatorView.getPresentacion(FacesUtils.checkInteger(txtId_Presentacion)) : null);
			businessDelegatorView.updateRespuesta(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedRespuesta = (RespuestaDTO) (evt.getComponent().getAttributes().get("selectedRespuesta"));

			Integer id = new Integer(selectedRespuesta.getId());
			entity = businessDelegatorView.getRespuesta(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Integer id = FacesUtils.checkInteger(txtId);
			entity = businessDelegatorView.getRespuesta(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteRespuesta(entity);
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
			selectedRespuesta = (RespuestaDTO) (evt.getComponent().getAttributes().get("selectedRespuesta"));

			Integer id = new Integer(selectedRespuesta.getId());
			entity = businessDelegatorView.getRespuesta(id);
			businessDelegatorView.deleteRespuesta(entity);
			data.remove(selectedRespuesta);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Integer id, Integer nota, Integer id_Pregunta, Integer id_Presentacion)
			throws Exception {
		try {
			entity.setNota(FacesUtils.checkInteger(nota));
			businessDelegatorView.updateRespuesta(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("RespuestaView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtNota() {
		return txtNota;
	}

	public void setTxtNota(InputText txtNota) {
		this.txtNota = txtNota;
	}

	public InputText getTxtId_Pregunta() {
		return txtId_Pregunta;
	}

	public void setTxtId_Pregunta(InputText txtId_Pregunta) {
		this.txtId_Pregunta = txtId_Pregunta;
	}

	public InputText getTxtId_Presentacion() {
		return txtId_Presentacion;
	}

	public void setTxtId_Presentacion(InputText txtId_Presentacion) {
		this.txtId_Presentacion = txtId_Presentacion;
	}

	public InputText getTxtId() {
		return txtId;
	}

	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}

	public List<RespuestaDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataRespuesta();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<RespuestaDTO> respuestaDTO) {
		this.data = respuestaDTO;
	}

	public RespuestaDTO getSelectedRespuesta() {
		return selectedRespuesta;
	}

	public void setSelectedRespuesta(RespuestaDTO respuesta) {
		this.selectedRespuesta = respuesta;
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

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

}
