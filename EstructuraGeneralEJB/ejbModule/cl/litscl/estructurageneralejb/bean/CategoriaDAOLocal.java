package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.litscl.estructurageneralejb.model.Categoria;

@Local
public interface CategoriaDAOLocal {
	public boolean save(Categoria c);
	public List<Categoria> getAll();
	public Categoria find(int id);
}
