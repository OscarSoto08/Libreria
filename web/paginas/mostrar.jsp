<%@page import="Model.LibroDTO" %>    
<% 
LibroDTO libro = (LibroDTO) request.getAttribute("libro");
%>

<div class="my-5 form-container">

    <h2>Información del Libro</h2>
    <form method="post" action="/libreria/libros/editar">
        <input  type="hidden" value="${libro.getId()}" name="id" /> 
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
            <button type="submit" class="mx-auto btn btn-success">Guardar Cambios</button>
        </div>
    </form>
        <div class="btn btn-danger">
            <form action="/libreria/libros/borrar" method="post">
                <input  type="hidden" value="${libro.getId()}" name="id_eliminar" /> 
                <button type="submit" >Eliminar</button>
            </form>       
        </div>
            <div class="btn btn-info">
                <form action="/libreria/libros/pdf" method="post">
                   <input  type="hidden" name="id" value="${libro.getId()}"/>
                   <input type="submit" value="Guardar PDF" />
                </form>
            </div>
</div>
        
        
