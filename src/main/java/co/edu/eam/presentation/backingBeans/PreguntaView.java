package co.edu.eam.presentation.backingBeans;

import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.PreguntaDTO;
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
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PreguntaView.class);
    private InputText txtEstado;
    private InputText txtPregunta;
    private InputText txtId_Periodo;
    private InputText txtId_TipoEvaluacion;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PreguntaDTO> data;
    private PreguntaDTO selectedPregunta;
    private Pregunta entity;
    private boolean showDialog;
    @EJB
    private IBusinessDelegatorView businessDelegatorView;

    public PreguntaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PreguntaDTO preguntaDTO = (PreguntaDTO) e.getObject();

            if (txtEstado == null) {
                txtEstado = new InputText();
            }

            txtEstado.setValue(preguntaDTO.getEstado());

            if (txtPregunta == null) {
                txtPregunta = new InputText();
            }

            txtPregunta.setValue(preguntaDTO.getPregunta());

            if (txtId_Periodo == null) {
                txtId_Periodo = new InputText();
            }

            txtId_Periodo.setValue(preguntaDTO.getId_Periodo());

            if (txtId_TipoEvaluacion == null) {
                txtId_TipoEvaluacion = new InputText();
            }

            txtId_TipoEvaluacion.setValue(preguntaDTO.getId_TipoEvaluacion());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(preguntaDTO.getId());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getPregunta(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPregunta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPregunta = null;

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtPregunta != null) {
            txtPregunta.setValue(null);
            txtPregunta.setDisabled(true);
        }

        if (txtId_Periodo != null) {
            txtId_Periodo.setValue(null);
            txtId_Periodo.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getPregunta(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstado.setDisabled(false);
            txtPregunta.setDisabled(false);
            txtId_Periodo.setDisabled(false);
            txtId_TipoEvaluacion.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtPregunta.setValue(entity.getPregunta());
            txtPregunta.setDisabled(false);
            txtId_Periodo.setValue(entity.getPeriodo().getId());
            txtId_Periodo.setDisabled(false);
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
        selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPregunta"));
        txtEstado.setValue(selectedPregunta.getEstado());
        txtEstado.setDisabled(false);
        txtPregunta.setValue(selectedPregunta.getPregunta());
        txtPregunta.setDisabled(false);
        txtId_Periodo.setValue(selectedPregunta.getId_Periodo());
        txtId_Periodo.setDisabled(false);
        txtId_TipoEvaluacion.setValue(selectedPregunta.getId_TipoEvaluacion());
        txtId_TipoEvaluacion.setDisabled(false);
        txtId.setValue(selectedPregunta.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPregunta == null) && (entity == null)) {
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
            entity = new Pregunta();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setId(id);
            entity.setPregunta(FacesUtils.checkString(txtPregunta));
            entity.setPeriodo((FacesUtils.checkInteger(txtId_Periodo) != null)
                ? businessDelegatorView.getPeriodo(FacesUtils.checkInteger(
                        txtId_Periodo)) : null);
            entity.setTipoEvaluacion((FacesUtils.checkInteger(
                    txtId_TipoEvaluacion) != null)
                ? businessDelegatorView.getTipoEvaluacion(
                    FacesUtils.checkInteger(txtId_TipoEvaluacion)) : null);
            businessDelegatorView.savePregunta(entity);
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
                Integer id = new Integer(selectedPregunta.getId());
                entity = businessDelegatorView.getPregunta(id);
            }

            entity.setEstado(FacesUtils.checkString(txtEstado));
            entity.setPregunta(FacesUtils.checkString(txtPregunta));
            entity.setPeriodo((FacesUtils.checkInteger(txtId_Periodo) != null)
                ? businessDelegatorView.getPeriodo(FacesUtils.checkInteger(
                        txtId_Periodo)) : null);
            entity.setTipoEvaluacion((FacesUtils.checkInteger(
                    txtId_TipoEvaluacion) != null)
                ? businessDelegatorView.getTipoEvaluacion(
                    FacesUtils.checkInteger(txtId_TipoEvaluacion)) : null);
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPregunta"));

            Integer id = new Integer(selectedPregunta.getId());
            entity = businessDelegatorView.getPregunta(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getPregunta(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePregunta(entity);
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
            selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPregunta"));

            Integer id = new Integer(selectedPregunta.getId());
            entity = businessDelegatorView.getPregunta(id);
            businessDelegatorView.deletePregunta(entity);
            data.remove(selectedPregunta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estado, Integer id,
        String pregunta, Integer id_Periodo, Integer id_TipoEvaluacion)
        throws Exception {
        try {
            entity.setEstado(FacesUtils.checkString(estado));
            entity.setPregunta(FacesUtils.checkString(pregunta));
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PreguntaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(InputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public InputText getTxtPregunta() {
        return txtPregunta;
    }

    public void setTxtPregunta(InputText txtPregunta) {
        this.txtPregunta = txtPregunta;
    }

    public InputText getTxtId_Periodo() {
        return txtId_Periodo;
    }

    public void setTxtId_Periodo(InputText txtId_Periodo) {
        this.txtId_Periodo = txtId_Periodo;
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

    public List<PreguntaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPregunta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PreguntaDTO> preguntaDTO) {
        this.data = preguntaDTO;
    }

    public PreguntaDTO getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(PreguntaDTO pregunta) {
        this.selectedPregunta = pregunta;
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
}
