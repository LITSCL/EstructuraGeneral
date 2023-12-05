package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cl.litscl.estructurageneralejb.model.Usuario;

/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
public class UsuarioDAO implements UsuarioDAOLocal {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstructuraGeneralEJB");	
	
    /**
     * Default constructor. 
     */
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean save(Usuario u) {	
		EntityManager em = this.emf.createEntityManager();
		try {
			em.persist(u);
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
	public List<Usuario> getAll() {
		EntityManager em = this.emf.createEntityManager();
		try {
			List<Usuario> usuarios = em.createNamedQuery("Usuario.getAll", Usuario.class).getResultList();
			return usuarios;
		} catch (Exception ex) {
			return null;
		}
		finally {
			em.close();
		}
	}

	@Override
	public Usuario find(String rut) {
		EntityManager em = this.emf.createEntityManager();
		Usuario u = new Usuario();
		try {
			u = em.find(Usuario.class, rut);
			return u;
		} catch (Exception ex) {
			return null;
		}
	}
}
