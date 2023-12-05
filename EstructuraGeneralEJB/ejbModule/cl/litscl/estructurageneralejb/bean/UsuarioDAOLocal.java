package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.litscl.estructurageneralejb.model.Usuario;

@Local
public interface UsuarioDAOLocal {
	public boolean save(Usuario u);
	public List<Usuario> getAll();
	public Usuario find(String rut);
}
