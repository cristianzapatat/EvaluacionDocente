package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.Dao;
import co.edu.eam.modelo.Presentacion;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;


/**
* Interface for   PresentacionDAO.
*
*/
@Local
public interface IPresentacionDAO extends Dao<Presentacion, Integer> {
}
