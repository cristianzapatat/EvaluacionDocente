package co.edu.eam.dataaccess.dao;

import co.edu.eam.dataaccess.api.JpaDaoImpl;
import co.edu.eam.modelo.Pregunta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Pregunta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Pregunta
 */
@Stateless
public class PreguntaDAO extends JpaDaoImpl<Pregunta, Integer>
    implements IPreguntaDAO {
    private static final Logger log = LoggerFactory.getLogger(PreguntaDAO.class);
    @PersistenceContext
    private EntityManager entityManager;
    
    public PreguntaDAO(EntityManager entityManager){
    	super(entityManager,Pregunta.class);
    	this.entityManager = entityManager;
    	
    }
    
    
}
