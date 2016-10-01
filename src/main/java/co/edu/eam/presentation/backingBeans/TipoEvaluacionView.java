package co.edu.eam.presentation.backingBeans;

import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.TipoEvaluacionDTO;
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
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TipoEvaluacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoEvaluacionView.class);
    private InputText txtNombre;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoEvaluacionDTO> data;
    private TipoEvaluacionDTO selectedTipoEvaluacion;
    private TipoEvaluacion entity;
    private boolean showDialog;
    private long idTipoEvaluacion;
	private Map<String, Long> tipoEvaluacio;
	private List<TipoEvaluacion> listaTipoEvaluaciones;
	private List<TipoEvaluacion> listaEntidadTipoEvaluacion;
    @EJB
    private IBusinessDelegatorView businessDelegatorView;

    public TipoEvaluacionView() {
        super();
    }

    

	@PostConstruct
	public void init() throws Exception {
		
		listaTipoEvaluaciones = new ArrayList<TipoEvaluacion>();
		tipoEvaluacio = new HashMap<String, Long>();
		listaEntidadTipoEvaluacion = new ArrayList<TipoEvaluacion>();

		listaTipoEvaluaciones = businessDelegatorView.getTipoEvaluacion();
		for (int i = 0; i < listaTipoEvaluaciones.size(); i++) {
			listaEntidadTipoEvaluacion.add((TipoEvaluacion) listaTipoEvaluaciones.get(i));
		}
		for (int i = 0; i < listaEntidadTipoEvaluacion.size(); i++) {
			tipoEvaluacio.put(listaEntidadTipoEvaluacion.get(i).getNombre(), null);
		}
	}
    public void rowEventListener(RowEditEvent e) {
        try {
            TipoEvaluacionDTO tipoEvaluacionDTO = (TipoEvaluacionDTO) e.getObject();

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(tipoEvaluacionDTO.getNombre());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(tipoEvaluacionDTO.getId());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getTipoEvaluacion(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoEvaluacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoEvaluacion = null;

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getTipoEvaluacion(id)
                                  : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombre.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoEvaluacion"));
        txtNombre.setValue(selectedTipoEvaluacion.getNombre());
        txtNombre.setDisabled(false);
        txtId.setValue(selectedTipoEvaluacion.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoEvaluacion == null) && (entity == null)) {
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
            entity = new TipoEvaluacion();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setId(id);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.saveTipoEvaluacion(entity);
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
                Integer id = new Integer(selectedTipoEvaluacion.getId());
                entity = businessDelegatorView.getTipoEvaluacion(id);
            }

            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updateTipoEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedTipoEvaluacion"));

            Integer id = new Integer(selectedTipoEvaluacion.getId());
            entity = businessDelegatorView.getTipoEvaluacion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getTipoEvaluacion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoEvaluacion(entity);
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
            selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedTipoEvaluacion"));

            Integer id = new Integer(selectedTipoEvaluacion.getId());
            entity = businessDelegatorView.getTipoEvaluacion(id);
            businessDelegatorView.deleteTipoEvaluacion(entity);
            data.remove(selectedTipoEvaluacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer id, String nombre)
        throws Exception {
        try {
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateTipoEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoEvaluacionView").requestRender();
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

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<TipoEvaluacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoEvaluacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoEvaluacionDTO> tipoEvaluacionDTO) {
        this.data = tipoEvaluacionDTO;
    }

    public TipoEvaluacionDTO getSelectedTipoEvaluacion() {
        return selectedTipoEvaluacion;
    }

    public void setSelectedTipoEvaluacion(TipoEvaluacionDTO tipoEvaluacion) {
        this.selectedTipoEvaluacion = tipoEvaluacion;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }



	public long getIdTipoEvaluacion() {
		return idTipoEvaluacion;
	}



	public void setIdTipoEvaluacion(long idTipoEvaluacion) {
		this.idTipoEvaluacion = idTipoEvaluacion;
	}



	public Map<String, Long> getTipoEvaluacio() {
		return tipoEvaluacio;
	}



	public void setTipoEvaluacio(Map<String, Long> tipoEvaluacio) {
		this.tipoEvaluacio = tipoEvaluacio;
	}





	public List<TipoEvaluacion> getListaEntidadTipoEvaluacion() {
		return listaEntidadTipoEvaluacion;
	}



	public void setListaEntidadTipoEvaluacion(List<TipoEvaluacion> listaEntidadTipoEvaluacion) {
		this.listaEntidadTipoEvaluacion = listaEntidadTipoEvaluacion;
	}
    
}
