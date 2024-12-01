<%@ page import="java.util.List" %>
<%@ page import="Model.Libro" %>
<%
    List<Libro> libros = (List<Libro>) request.getAttribute("datos");

    if (libros == null || libros.isEmpty()) {
%>
        <h1>Cargando libreria...</h1> 
<% 
    } else {
%>
<div class="container">
  <% 
    for (int i = 0; i < libros.size(); i++) {
        if (i % 4 == 0) { 
  %>
            <div class="row">
  <% 
        }
        Libro libro = libros.get(i);
  %>
            <div class="col-md-3">
                <jsp:include page="/componentes/card.jsp">
                    <jsp:param name="titulo_libro" value="<%= libro.getTitulo() %>"/>
                    <jsp:param name="editorial" value="<%= libro.getEditorial() %>"/>
                    <jsp:param name="anio_libro" value="<%= libro.getAnio() %>"/>
                </jsp:include>
            </div>
  <%
        if ((i + 1) % 4 == 0 || (i + 1) == libros.size()) {
  %>
            </div>
  <% 
        }
    }
  %>
</div>
<%
    }
%>