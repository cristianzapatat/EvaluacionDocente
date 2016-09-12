package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.Dao;
import co.edu.eam.modelo.Evaluacion;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;


/**
* Interface for   EvaluacionDAO.
*
*/
@Local
public interface IEvaluacionDAO extends Dao<Evaluacion, Integer> {
}
