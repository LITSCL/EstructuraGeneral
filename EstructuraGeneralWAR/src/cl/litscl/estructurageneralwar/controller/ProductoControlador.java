package cl.litscl.estructurageneralwar.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.litscl.estructurageneralejb.bean.CategoriaDAOLocal;
import cl.litscl.estructurageneralejb.bean.ProductoDAOLocal;
import cl.litscl.estructurageneralejb.model.Producto;

/**
 * Servlet implementation class ProductoControlador
 */
@WebServlet("/Producto")
public class ProductoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductoDAOLocal daoProducto;
	@Inject
	private CategoriaDAOLocal daoCategoria;
	
	private Producto p = new Producto();
	
	private String codigo;
	private String nombre;
	private Double precio;
	private int categoriaFK;
	
	private String jpql;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet: Se debe utilizar para renderizar vistas y controlar datos que llegan desde la URL.
		
		//1. Crear una sesión.
		HttpSession sesion = request.getSession(false);
		
		//2. recibir los parámetros.
		String vista = request.getParameter("vista");
		String opcion = request.getParameter("opcion");
		
		//3. Verificar si llegan los parámetros (Switch no puede recibir datos "null").
		if (vista == null) {
			vista = "";
		}
		if (opcion == null) {
			opcion = "";
		}	
		
		//4. Revisar que vista se desea renderizar.
		switch (vista) { //NOTA: Este Switch es para renderiza vistas.
			case "crear_producto":	
				sesion.setAttribute("categorias", daoCategoria.getAll());
				
				sesion.setAttribute("renderizarVista", "crearProducto");
				response.sendRedirect(request.getContextPath() + "/crear_producto");
				break;
			case "mostrar_productos":
				List<Producto> productos = daoProducto.getAll();
				sesion.setAttribute("productos", productos);
				
				sesion.setAttribute("renderizarVista", "mostrarProductos");
				response.sendRedirect(request.getContextPath() + "/mostrar_productos");
				break;
			default:
				break;
		}
		
		switch (opcion) { //NOTA: Este Switch es para controlar datos que llegan desde la URL.
			case "1": //Sin asignar.
				break;
			default:
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost: Se debe utilizar para procesar datos que llegan desde formularios y luego redireccionar a una vista.
		
		//1. Crear una sesión.
		HttpSession sesion = request.getSession(false);
		
		//2. Recibir la opción a controlar.
		String opcion = request.getParameter("opcion");
		
		//3. Revisar que hay que procesar.
		switch (opcion) { //NOTA: Este Switch es para controlar datos que llegan desde un formulario.
			case "1": //Crear.
				this.codigo = request.getParameter("codigo");
				this.nombre = request.getParameter("nombre");
				this.precio = Double.parseDouble(request.getParameter("precio"));
				this.categoriaFK = Integer.parseInt(request.getParameter("categoria"));	
				
				this.p.setCodigo(codigo);
				this.p.setNombre(nombre);
				this.p.setPrecio(precio);
				this.p.setCategoriaFK(categoriaFK);
				
				if (daoProducto.save(this.p)) {
					sesion.setAttribute("crearProducto", "Exitoso");
				}
				else {
					sesion.setAttribute("crearProducto", "Fallido");
				}			
				response.sendRedirect(request.getContextPath() + "/crear_producto");		
				break;
			default:
				break;
		}
	}
}
