package cl.litscl.estructurageneralwar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.litscl.estructurageneralejb.bean.UsuarioDAOLocal;
import cl.litscl.estructurageneralejb.model.Usuario;
import cl.litscl.estructurageneralwar.util.RutUtil;

/**
 * Servlet implementation class UsuarioControlador
 */
@WebServlet("/Usuario")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAOLocal daoUsuario;
	
	private Usuario u = new Usuario();
	
	private String rut;
	private String nombre;
	private String apellido;
	private String email;
	private String clave;
	private String tipo;
	
	private RutUtil rutUtil = new RutUtil();
	
	private String jpql;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet: Se debe utilizar para renderizar vistas y controlar datos que llegan desde la URL.
		
		//1. Crear una sesi�n.
		HttpSession sesion = request.getSession(false);
		
		//2. recibir los par�metros.
		String vista = request.getParameter("vista");
		String opcion = request.getParameter("opcion");
		
		//3. Verificar si llegan los par�metros (Switch no puede recibir datos "null").
		if (vista == null) {
			vista = "";
		}
		if (opcion == null) {
			opcion = "";
		}	
		
		//4. Revisar que vista se desea renderizar.
		switch (vista) { //NOTA: Este Switch es para renderiza vistas.
		case "crear_usuario":
			sesion.setAttribute("renderizarVista", "crearUsuario");
			response.sendRedirect(request.getContextPath() + "/crear_usuario");
			break;
		case "mostrar_usuarios":
			List<Usuario> usuarios = daoUsuario.getAll();
			sesion.setAttribute("usuarios", usuarios);
			
			sesion.setAttribute("renderizarVista", "mostrarUsuarios");
			response.sendRedirect(request.getContextPath() + "/mostrar_usuarios");
			break;
		case "iniciar_sesion":	
			sesion.setAttribute("renderizarVista", "iniciarSesion");
			response.sendRedirect(request.getContextPath() + "/iniciar_sesion");
			break;
		case "panel_administrador":
			if (sesion.getAttribute("usuario") != null) {
				this.u = (Usuario)sesion.getAttribute("usuario");
				if (this.u.getTipo().equals("Administrador")) {
					sesion.setAttribute("renderizarVista", "panelAdministrador");
					response.sendRedirect(request.getContextPath() + "/panel_administrador");
				}
				else {
					response.sendRedirect(request.getContextPath());
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}		
			break;
		case "panel_cliente":
			if (sesion.getAttribute("usuario") != null) {
				this.u = (Usuario)sesion.getAttribute("usuario");
				if (this.u.getTipo().equals("Cliente")) {
					sesion.setAttribute("renderizarVista", "panelCliente");
					response.sendRedirect(request.getContextPath() + "/panel_cliente");
				}
				else {
					response.sendRedirect(request.getContextPath());
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}		
			break;
		default:
			break;
		}
		
		switch (opcion) { //NOTA: Este Switch es para controlar datos que llegan desde la URL.
		case "1": //Desloguear.	
			sesion.removeAttribute("usuario");
			response.sendRedirect(request.getContextPath());
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
		
		//1. Crear una sesi�n.
		HttpSession sesion = request.getSession(false);
		
		//2. Recibir la opci�n a controlar.
		String opcion = request.getParameter("opcion");
		
		List<String> errores = new ArrayList<String>();
		
		//3. Revisar que hay que procesar.
		switch (opcion) { //NOTA: Este Switch es para controlar datos que llegan desde un formulario.
		case "1": //Crear.
			this.rut = request.getParameter("rut");
			this.nombre = request.getParameter("nombre");
			this.apellido = request.getParameter("apellido");
			this.email = request.getParameter("email");
			this.clave = request.getParameter("clave");
			this.tipo = request.getParameter("tipo");	
			
			if (rutUtil.validarRutChileno(this.rut) == false) {
				errores.add("El rut ingresado no posee formato v�lido");
			}
			
			this.u.setRut(rut);
			this.u.setNombre(nombre);
			this.u.setApellido(apellido);
			this.u.setEmail(email);
			this.u.setClave(clave);
			this.u.setTipo(tipo);
			
			if (errores.isEmpty() == true) {
				if (daoUsuario.save(this.u)) {
					sesion.setAttribute("crearUsuario", "Exitoso");
				}
				else {
					sesion.setAttribute("crearUsuario", "Fallido");
				}			
			}
			else {
				sesion.setAttribute("crearUsuario", "Fallido");
				sesion.setAttribute("errores", errores);
			}
			response.sendRedirect(request.getContextPath() + "/crear_usuario");		
			break;
		case "2": //Logear.
			this.rut = request.getParameter("rut");
			this.clave = request.getParameter("clave");	
			
			this.u = daoUsuario.find(this.rut);
			
			if (this.u != null) {
				if (this.u.getClave().equals(this.clave) == true) {
					sesion.setAttribute("usuario", this.u);
					response.sendRedirect(request.getContextPath());
				}
				else {
					sesion.setAttribute("errorLogin", "Credenciales incorrectas");
					response.sendRedirect(request.getContextPath() + "/iniciar_sesion");
				}
			}
			else {
				sesion.setAttribute("errorLogin", "Credenciales incorrectas");
				response.sendRedirect(request.getContextPath() + "/iniciar_sesion");
			}			
			break;
		default:
			break;
		}
	}
}
