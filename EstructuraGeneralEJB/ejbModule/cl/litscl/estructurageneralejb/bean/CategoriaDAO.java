package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cl.litscl.estructurageneralejb.model.Categoria;

/**
 * Session Bean implementation class CategoriaDAO
 */
@Stateless
public class CategoriaDAO implements CategoriaDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstructuraGeneralEJB");

    /**
     * Default constructor. 
     */
    public CategoriaDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean save(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(c);
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
	public List<Categoria> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Categoria> categorias = em.createNamedQuery("Categoria.getAll", Categoria.class).getResultList();
			return categorias;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public Categoria find(int id) {
		EntityManager em = this.emf.createEntityManager();
		Categoria c = new Categoria();
		try {
			c = em.find(Categoria.class, id);
			return c;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}
}
