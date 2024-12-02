<%@page import="Model.Libro" %>    
<% 
Libro libro = (Libro) request.getAttribute("libro");
%>

<div class="form-container">
    <h2>Información del Libro</h2>
    <form method="post" action="/libreria/libros/editar">
        <div class="form-group">
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" name="isbn" value="<%= libro.getIsbn()%>" required>
        </div>
        <div class="form-group">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" value="<%= libro.getTitulo() %>" required>
        </div>
        <div class="form-group">
            <label for="autor">Autor:</label>
            <input type="text" id="autor" name="autor" value="<%= libro.getAutor() %>" required>
        </div>
        <div class="form-group">
            <label for="anio">Año:</label>
            <input type="number" id="anio" name="anio" value="<%= libro.getAnio() %>" required>
        </div>
        <div class="form-group">
            <label for="editorial">Editorial:</label>
            <input type="text" id="editorial" name="editorial" value="<%= libro.getEditorial()%>" required>
        </div>
        <div class="btn-container">
            <button type="submit" class="btn">Guardar Cambios</button>
            <button type="button" class="btn btn-danger">Eliminar</button>
        </div>
    </form>
</div>
