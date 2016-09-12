package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.Dao;
import co.edu.eam.modelo.Periodo;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;


/**
* Interface for   PeriodoDAO.
*
*/
@Local
public interface IPeriodoDAO extends Dao<Periodo, Integer> {
}
