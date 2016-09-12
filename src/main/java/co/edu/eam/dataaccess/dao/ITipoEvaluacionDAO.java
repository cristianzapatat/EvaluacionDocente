package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.Dao;
import co.edu.eam.modelo.TipoEvaluacion;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;


/**
* Interface for   TipoEvaluacionDAO.
*
*/
@Local
public interface ITipoEvaluacionDAO extends Dao<TipoEvaluacion, Integer> {
}
