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
import cl.litscl.estructurageneralejb.model.Categoria;

/**
 * Servlet implementation class CategoriaControlador
 */
@WebServlet("/Categoria")
public class CategoriaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CategoriaDAOLocal daoCategoria;
	
	private Categoria c = new Categoria();
	
	private int id;
	private String nombre;
	
	private String jpql;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaControlador() {
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
			case "crear_categoria":		
				sesion.setAttribute("renderizarVista", "crearCategoria");
				response.sendRedirect(request.getContextPath() + "/crear_categoria");
				break;
			case "mostrar_categorias":
				List<Categoria> categorias = daoCategoria.getAll();
				sesion.setAttribute("categorias", categorias);
				
				sesion.setAttribute("renderizarVista", "mostrarCategorias");
				response.sendRedirect(request.getContextPath() + "/mostrar_categorias");
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
				this.nombre = request.getParameter("nombre");	
				
				this.c.setNombre(nombre);
				
				if (daoCategoria.save(this.c)) {
					sesion.setAttribute("crearCategoria", "Exitoso");
				}
				else {
					sesion.setAttribute("crearCategoria", "Fallido");
				}			
				response.sendRedirect(request.getContextPath() + "/crear_categoria");		
				break;
			default:
				break;
		}
	}
}
