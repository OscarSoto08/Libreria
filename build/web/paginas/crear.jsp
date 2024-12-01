<%@ page import="java.util.List" %>
<%@ page import="Model.Libro" %>
<%@ page import="Controller.LibroCTO" %>
<div class="form-container my-4 mx-auto"> � 

    <form method="post" action="LibroCTO">
      <h1>Crea un nuevo libro</h1>
      <div class="input-group">
        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" placeholder="ISBN">
        <div class="input-icon"><i class="fa fa-barcode"></i></div>
      </div>
      <div class="input-group">
        <label for="titulo">T�tulo:</label>
        <input type="text" id="titulo" name="titulo" placeholder="T�tulo">
        <div class="input-icon"><i class="fa fa-book"></i></div>
      </div>
      <div class="input-group">
        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor" placeholder="Autor">
        <div class="input-icon"><i class="fa fa-user"></i></div>
      </div>
      <div class="input-group">
        <label for="editorial">Editorial:</label>
        <input type="text" id="editorial" name="editorial" placeholder="Editorial">
        <div class="input-icon"><i class="fa fa-building"></i></div>
      </div>
      <div class="input-group">
        <label for="a�o">A�o:</label>
        <input type="text" id="a�o" name="a�o" placeholder="A�o">
        <div class="input-icon"><i class="fa fa-calendar"></i></div>
      </div>
      <button type="submit">Enviar</button>
    </form>
  </div>