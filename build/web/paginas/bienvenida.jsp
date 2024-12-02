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
                    <jsp:param name="libro_titulo" value="<%= libro.getTitulo() %>" />
                    <jsp:param name="libro_autor" value="<%= libro.getAutor() %>" />
                    <jsp:param name="libro_editorial" value="<%= libro.getEditorial() %>" />
                    <jsp:param name="libro_slug" value="<%= libro.getSlug()%>" />
                    <jsp:param name="libro_anio" value="<%= libro.getAnio()%>" />
                    <jsp:param name="libro_id" value="<%= libro.getId()%>"/>
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