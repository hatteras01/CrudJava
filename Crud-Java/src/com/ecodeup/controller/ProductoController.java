package com.ecodeup.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.dao.ProductoDAO;
import com.ecodeup.model.producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "Administra peticiones para la tabla productos", urlPatterns = { "/Producto" })
public class ProductoController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion = request.getParameter("opcion");
		
		if(opcion.equals("crear"))
		{
			System.out.println("opción Crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/crear.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(opcion.equals("listar"))
		{
			ProductoDAO productoDAO = new ProductoDAO();
			List<producto> lista = new ArrayList<>();
			try 
			{
				lista = productoDAO.obtenerProductos();
							/*for(producto producto : lista)
							{
								System.out.println(producto);
							}*/
				//Enviamos los objetos recogidos a la vista
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/listar.jsp");
				requestDispatcher.forward(request, response);
				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("opción Listar");
		}
		else if(opcion.equals("meditar"))
		{
			//El valor que viene de la jsp es siempre cadena. Hay que hacer un cast
			int id = Integer.parseInt(request.getParameter("id"));
			ProductoDAO productoDAO = new ProductoDAO();
			producto p = new producto();;
			p = productoDAO.obtenerProducto(id);
			
			request.setAttribute("producto", p);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/editar.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(opcion.equals("eliminar"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			ProductoDAO productoDAO = new ProductoDAO();
			try 
			{
				productoDAO.eliminar(id);
				System.out.println("Registro eliminado");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request, response)
		//Recogemos el valor enviado por Post del form en el Type hidden
		String opcion = request.getParameter("opcion");
		//Fecha tipo java.util
		Date fechaActual = new Date();
		
		if(opcion.equals("guardar"))
		{	
		
			//Instancia del objeto que tiene los métodos para tratar con la BBDD
			ProductoDAO productoDAO = new ProductoDAO();
			//Instancia del objeto que persistiremos en la BBDD
			producto producto = new producto();
			//Damos valores al objeto desde el jsp a través del request
			producto.setNombre(request.getParameter("nombre"));
			//Los valores que proceden del jsp son Strings
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			//La fecha no la traemos de la vista sino que la creamos aquí
			//La fecha del objeto es una fecha SQL
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			//Persistimos el objeto
			try 
			{
				productoDAO.guardar(producto);
				//System.out.println("Registro guardado");
				//Volvemos a la página index
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		else if(opcion.equals("editar"))
		{
			ProductoDAO productoDAO = new ProductoDAO();
			producto producto = new producto();
			//Damos valores al objeto desde el jsp a través del request
			producto.setId(Integer.parseInt(request.getParameter("id")));
			producto.setNombre(request.getParameter("nombre"));
			//Los valores que proceden del jsp son Strings
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
			
			try 
			{
				productoDAO.editar(producto);
				System.out.println("Registro editado");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
