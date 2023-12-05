package cl.litscl.estructurageneralejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity
@Table(name = "producto")
@NamedQueries({
	@NamedQuery(name = "Producto.getAll", query = "SELECT p FROM Producto p")
})
public class Producto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "precio")
	private double precio;
	@Column(name = "categoria_id")
	private int categoriaFK;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCategoriaFK() {
		return categoriaFK;
	}
	public void setCategoriaFK(int categoriaFK) {
		this.categoriaFK = categoriaFK;
	}
	 
}
