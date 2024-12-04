package Controller;

import Model.LibroDTO;
import Persistencia.LibroDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/libros/*"})
public class LibroCTO extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (uri.equals("/libreria/libros/inicio")) {
                LibroDAO libroDAO = new LibroDAO();
                List<LibroDTO> libros = libroDAO.readAll();
                request.setAttribute("datos", libros);
                request.setAttribute("pagina", "/paginas/bienvenida.jsp");
                request.setAttribute("titulo_pag", "Pagina principal");
                request.getRequestDispatcher("/index.jsp").
                        forward(request, response);
            }
            if (uri.equals("/libreria/libros/crear")) {
                request.setAttribute("pagina", "/paginas/crear.jsp");
                request.setAttribute("titulo_pag", "Nuevo libro");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (IOException | ServletException ex) {
            // Manejar otras excepciones
            request.setAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo más tarde." + ex.getMessage());
            request.getRequestDispatcher("/paginas/error.jsp").forward(request, response); 
        }
    }
     
    
    //
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (uri.contains("/libreria/libros/crear")) {
            
                // Obtener los datos del formulario
                String isbn = request.getParameter("isbn");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                String editorial = request.getParameter("editorial");
                Integer anio = Integer.valueOf(request.getParameter("anio"));
                String slug = LibroDTO.toSlug(titulo); 
                // Validar los datos del formulario (implementar validaciones)

                LibroDAO gestorLibros = new LibroDAO();

                // Intentar crear el nuevo libro
                LibroDTO nuevoLibro = new LibroDTO(isbn, titulo, autor, editorial, anio, slug);
                if(gestorLibros.create(nuevoLibro)){
                    response.sendRedirect("/libreria/libros/inicio");
                }else{
                    request.setAttribute("error", "Hubo un problema al intentar agregar el registro a la base de datos");
                    request.getRequestDispatcher("/paginas/error.jsp")
                                .forward(request, response);
                }                
                
                
            }if(uri.contains("/libreria/libros/ver")){
//                 Recoger el isbn para recuperar el libro
                LibroDAO gestorLibros = new LibroDAO();
                LibroDTO objLibro = gestorLibros.read(Integer.parseInt(request.getParameter("id")));

//                request.removeAttribute("isbn"); //ya no lo necesito porque esta en la instancia
                if(objLibro != null){
                    request.setAttribute("libro", objLibro);
                    request.setAttribute("pagina", "/paginas/mostrar.jsp");
                    request.setAttribute("titulo_pag", objLibro.getTitulo());
                    request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                } else {
                    request.setAttribute("error", "Hubo un problema al intentar solicitar el registro a la base de datos");
                    request.getRequestDispatcher("/paginas/error.jsp")
                                .forward(request, response);
                }
                
            }if(uri.contains("/libreria/libros/editar")){
                int id = Integer.parseInt(request.getParameter("id"));
                String isbn = request.getParameter("isbn");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                int anio =  Integer.parseInt(request.getParameter("anio"));
                String editorial = request.getParameter("editorial");
                String slug = LibroDTO.toSlug(titulo);
                LibroDTO nuevoLibro = new LibroDTO(id, isbn, titulo, autor, editorial, anio, slug);
                LibroDAO libroDAO = new LibroDAO();
                libroDAO.update(nuevoLibro);
                response.sendRedirect("/libreria/libros/inicio");
            }if(uri.contains("/libreria/libros/borrar")){
               LibroDAO libroDAO = new LibroDAO();
                  if(libroDAO.delete(Integer.parseInt(request.getParameter("id_eliminar"))) == false){
                      response.sendRedirect("/libreria/libros/inicio");
                }else{
                       request.setAttribute("error", "Error al eliminar....");
                       request.getRequestDispatcher("/paginas/error.jsp").forward(request, response); 
                  }
            }
            
        }  catch (NumberFormatException ex) {
            // Manejar error de formato de número
            request.setAttribute("error", "Error en el formato de los datos numéricos.");
            request.getRequestDispatcher("/paginas/error.jsp").forward(request, response); 

        } catch (IOException ex) {
            // Manejar otras excepciones
            request.setAttribute("error", "Error al crear el libro. Por favor, inténtelo de nuevo más tarde.");
            request.getRequestDispatcher("/paginas/error.jsp").forward(request, response); 
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar libros";
    }
}