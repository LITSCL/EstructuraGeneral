package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cl.litscl.estructurageneralejb.model.Producto;

/**
 * Session Bean implementation class ProductoDAO
 */
@Stateless
public class ProductoDAO implements ProductoDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstructuraGeneralEJB");
	
    /**
     * Default constructor. 
     */
    public ProductoDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean save(Producto p) {	
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(p);
			em.flush();
			return true;
		} catch (Exception ex) {
			return false;
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Producto> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Producto> productos = em.createNamedQuery("Producto.getAll", Producto.class).getResultList();
			return productos;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
}
