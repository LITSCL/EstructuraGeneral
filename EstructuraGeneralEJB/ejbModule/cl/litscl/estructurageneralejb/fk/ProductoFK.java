package cl.litscl.estructurageneralejb.fk;

import cl.litscl.estructurageneralejb.bean.CategoriaDAO;
import cl.litscl.estructurageneralejb.bean.CategoriaDAOLocal;
import cl.litscl.estructurageneralejb.model.Categoria;
import cl.litscl.estructurageneralejb.model.Producto;

public class ProductoFK {
	private CategoriaDAOLocal daoCategoria = new CategoriaDAO();
	private Categoria c = new Categoria();
	
	public int getCategoriaId(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());
		return c.getId();
	}
	
	public String getCategoriaNombre(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());	
		return c.getNombre();
	}
}
