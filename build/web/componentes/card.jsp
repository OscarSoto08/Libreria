<div class="card border-primary p-3 m-3" style="width: 18rem;">
  <img src="<%= request.getContextPath()%>/img/libro.png" style="max-width: 100px; margin: auto;" class="card-img-top" alt="...">
  <div class="card-body text-center">
      <h5 class="card-title"><%= request.getParameter("libro_titulo") %></h5>
  </div>
  <ul class="list-group list-group-flush text-center">
      <li class="list-group-item">Ed. <%= request.getParameter("libro_editorial") %></li>
      <li class="list-group-item"><%= request.getParameter("libro_anio") %></li>
  </ul>
  <form action="/libreria/libros/ver/<%= request.getParameter("libro_slug") %>" method="post">
  <div class="card-body text-center">
      <input type="hidden" name="id" value="<%= request.getParameter("libro_id") %>">
      <button style="border: none;" type="submit" class="enlace-animado">Ver libro</button>
  </div>
  </form>
</div>