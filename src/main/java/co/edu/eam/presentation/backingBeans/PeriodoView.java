package co.edu.eam.presentation.backingBeans;

import co.edu.eam.exceptions.*;
import co.edu.eam.modelo.*;
import co.edu.eam.modelo.dto.PeriodoDTO;
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
public class PeriodoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PeriodoView.class);
    private InputText txtAnio;
    private InputText txtNombre;
    private InputText txtPeriodo;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PeriodoDTO> data;
    private PeriodoDTO selectedPeriodo;
    private Periodo entity;
    private boolean showDialog;
    @EJB
    private IBusinessDelegatorView businessDelegatorView;

    public PeriodoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PeriodoDTO periodoDTO = (PeriodoDTO) e.getObject();

            if (txtAnio == null) {
                txtAnio = new InputText();
            }

            txtAnio.setValue(periodoDTO.getAnio());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(periodoDTO.getNombre());

            if (txtPeriodo == null) {
                txtPeriodo = new InputText();
            }

            txtPeriodo.setValue(periodoDTO.getPeriodo());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(periodoDTO.getId());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getPeriodo(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPeriodo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPeriodo = null;

        if (txtAnio != null) {
            txtAnio.setValue(null);
            txtAnio.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPeriodo != null) {
            txtPeriodo.setValue(null);
            txtPeriodo.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getPeriodo(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAnio.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPeriodo.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAnio.setValue(entity.getAnio());
            txtAnio.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPeriodo.setValue(entity.getPeriodo());
            txtPeriodo.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPeriodo = (PeriodoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPeriodo"));
        txtAnio.setValue(selectedPeriodo.getAnio());
        txtAnio.setDisabled(false);
        txtNombre.setValue(selectedPeriodo.getNombre());
        txtNombre.setDisabled(false);
        txtPeriodo.setValue(selectedPeriodo.getPeriodo());
        txtPeriodo.setDisabled(false);
        txtId.setValue(selectedPeriodo.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPeriodo == null) && (entity == null)) {
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
            entity = new Periodo();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setAnio(FacesUtils.checkInteger(txtAnio));
            entity.setId(id);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPeriodo(FacesUtils.checkInteger(txtPeriodo));
            businessDelegatorView.savePeriodo(entity);
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
                Integer id = new Integer(selectedPeriodo.getId());
                entity = businessDelegatorView.getPeriodo(id);
            }

            entity.setAnio(FacesUtils.checkInteger(txtAnio));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPeriodo(FacesUtils.checkInteger(txtPeriodo));
            businessDelegatorView.updatePeriodo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPeriodo = (PeriodoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPeriodo"));

            Integer id = new Integer(selectedPeriodo.getId());
            entity = businessDelegatorView.getPeriodo(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getPeriodo(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePeriodo(entity);
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
            selectedPeriodo = (PeriodoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPeriodo"));

            Integer id = new Integer(selectedPeriodo.getId());
            entity = businessDelegatorView.getPeriodo(id);
            businessDelegatorView.deletePeriodo(entity);
            data.remove(selectedPeriodo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Integer anio, Integer id, String nombre,
        Integer periodo) throws Exception {
        try {
            entity.setAnio(FacesUtils.checkInteger(anio));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPeriodo(FacesUtils.checkInteger(periodo));
            businessDelegatorView.updatePeriodo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PeriodoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAnio() {
        return txtAnio;
    }

    public void setTxtAnio(InputText txtAnio) {
        this.txtAnio = txtAnio;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPeriodo() {
        return txtPeriodo;
    }

    public void setTxtPeriodo(InputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<PeriodoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPeriodo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PeriodoDTO> periodoDTO) {
        this.data = periodoDTO;
    }

    public PeriodoDTO getSelectedPeriodo() {
        return selectedPeriodo;
    }

    public void setSelectedPeriodo(PeriodoDTO periodo) {
        this.selectedPeriodo = periodo;
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
