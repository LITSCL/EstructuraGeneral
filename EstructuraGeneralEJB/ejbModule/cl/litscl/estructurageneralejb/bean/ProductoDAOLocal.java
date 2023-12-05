package cl.litscl.estructurageneralejb.bean;

import java.util.List;

import javax.ejb.Local;

import cl.litscl.estructurageneralejb.model.Producto;

@Local
public interface ProductoDAOLocal {
	public boolean save(Producto p);
	public List<Producto> getAll();
}
