<%@ page import="java.util.List" %>
<%@ page import="Model.LibroDTO" %>
<%
    List<LibroDTO> libros = (List<LibroDTO>) request.getAttribute("datos");

    if (libros == null || libros.isEmpty()) {
%>
        <h1 class="text-3xl text-center my-auto">No se encuentran registros, ¡crea uno!</h1> 
<% 
    } else {
%>
    <div class="container mx-auto mt-5 grid grid-cols-4 gap-4">
  <% 
    for (int i = 0; i < libros.size(); i++) {
        LibroDTO libro = libros.get(i);
  %>
    <jsp:include page="/componentes/card.jsp">
        <jsp:param name="libro_titulo" value="<%= libro.getTitulo() %>" />
        <jsp:param name="libro_autor" value="<%= libro.getAutor() %>" />
        <jsp:param name="libro_editorial" value="<%= libro.getEditorial() %>" />
        <jsp:param name="libro_slug" value="<%= libro.getSlug()%>" />
        <jsp:param name="libro_anio" value="<%= libro.getAnio()%>" />
        <jsp:param name="libro_id" value="<%= libro.getId()%>"/>
    </jsp:include>
  <%
    }
}
  %>
    </div>