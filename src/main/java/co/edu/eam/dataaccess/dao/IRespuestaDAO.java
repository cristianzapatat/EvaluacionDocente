package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.Dao;
import co.edu.eam.modelo.Respuesta;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;


/**
* Interface for   RespuestaDAO.
*
*/
@Local
public interface IRespuestaDAO extends Dao<Respuesta, Integer> {
}
