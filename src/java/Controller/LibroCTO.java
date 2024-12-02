package Controller;

import Model.Libro;
import Persist.LibroJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@WebServlet(urlPatterns = {"/libros/*"})
public class LibroCTO extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (uri.equals("/libreria/libros/inicio")) {
                LibroJpaController libroDAO = new LibroJpaController();
                List<Libro> libros = libroDAO.findLibroEntities();
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
        } catch (EntityNotFoundException ex) {
            // Manejar la excepción: libro no encontrado
            request.setAttribute("error", "No se encontraron libros.");
            request.getRequestDispatcher("/error.jsp").forward(request, response); 
        } catch (IOException | ServletException ex) {
            // Manejar otras excepciones
            request.setAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo más tarde.");
            request.getRequestDispatcher("/error.jsp").forward(request, response); 
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
                String slug = Libro.toSlug(titulo); 
                // Validar los datos del formulario (implementar validaciones)

                LibroJpaController gestorLibros = new LibroJpaController();

                // Intentar crear el nuevo libro
                Libro nuevoLibro = new Libro(isbn, titulo, autor, editorial, anio, slug);
                gestorLibros.create(nuevoLibro);

                // Redireccionar a la página de éxito
                response.sendRedirect("/libreria/libros/inicio");
            }if(uri.contains("/libreria/libros/ver")){
//                 Recoger el isbn para recuperar el libro
                LibroJpaController gestorLibros = new LibroJpaController();
                    Libro objLibro = gestorLibros.findLibro(Integer.valueOf(request.getParameter("id")));
//                request.removeAttribute("isbn"); //ya no lo necesito porque esta en la instancia
                request.setAttribute("libro", objLibro);
                request.setAttribute("pagina", "/paginas/mostrar.jsp");
                request.setAttribute("titulo_pag", objLibro.getTitulo());
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
            }if(uri.contains("/libreria/libros/editar")){
                response.sendRedirect("/libreria/libros/inicio");
                //TODO: recuperar los parametros que te llegan y modificarlos en la bd
            }if(uri.contains("/libreria/libros/eliminar")){
                //TODO: recuperar el id y eliminar el registro de la bd
            }
            
        } catch (EntityExistsException ex) {
            // Manejar el error: el libro ya existe
            request.setAttribute("error", "El libro con ISBN " + request.getParameter("isbn") + " ya existe.");
            request.getRequestDispatcher("/paginas/error.jsp").forward(request, response);

        } catch (NumberFormatException ex) {
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