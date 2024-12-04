<%@ page import="java.util.List" %>
<%@ page import="Model.LibroDTO" %>
<%@ page import="Controller.LibroCTO" %>

<div class="my-5 form-container">
        <h1 class="text-center font-semibold text-3xl my-3">NUEVO LIBRO</h1 >
        <form method="post" action="/libreria/libros/crear">
            <div class="form-group">
                <label for="isbn" class="">ISBN:</label>
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
                <button type="submit" class="btn btn-success">CREAR</button>
            </div>
        </form>
    </div>