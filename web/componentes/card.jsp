<div class="card border-primary p-3 m-3" style="width: 18rem;">
  <img src="<%= request.getContextPath()%>/img/libro.png" style="max-width: 100px; margin: auto;" class="card-img-top" alt="...">
  <div class="card-body text-center">
      <h5 class="card-title"><%= request.getParameter("titulo_libro") %></h5>
  </div>
  <ul class="list-group list-group-flush text-center">
    <li class="list-group-item">Ed. <%= request.getParameter("editorial") %></li>
    <li class="list-group-item"><%= request.getParameter("anio_libro") %></li>
  </ul>
  <div class="card-body text-center">
    <a class="enlace-animado" href="#" class="card-link">Ver libro</a>
  </div>
</div>