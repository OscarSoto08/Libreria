<%@ page import="java.util.List" %>
<%@ page import="Model.Libro" %>
<%@ page import="Controller.LibroCTO" %>
<div class="form-container">
        <h2>Nuevo Libro</h2>
        <form method="post" action="/libreria/libros/crear">
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" id="isbn" name="isbn" required>
            </div>
            <div class="form-group">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required>
            </div>
            <div class="form-group">
                <label for="autor">Autor:</label>
                <input type="text" id="autor" name="autor" required>
            </div>
            <div class="form-group">
                <label for="anio">Año:</label>
                <input type="number" id="anio" name="anio" required>
            </div>
            <div class="form-group">
                <label for="editorial">Editorial:</label>
                <input type="text" id="editorial" name="editorial" required>
            </div>
            <div class="btn-container">
                <button type="submit" class="btn">Guardar</button>
            </div>
        </form>
    </div>