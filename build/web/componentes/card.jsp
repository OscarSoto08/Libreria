
<div
  class="flex flex-col max-h-34 justify-between items-baseline gap-2 bg-indigo-500 rounded-lg shadow p-2"
>
  <div class="flex text-center gap-2" style="">
    <img src="<%=request.getContextPath()%>/img/libro.png" class="max-w-20 max-h-20 p-2 bg-purple-200 w-24 shrink-0 rounded-lg" />
    <div class="flex flex-col text-white" >
      <span class="font-bold italic">${param.libro_titulo}</span>
      <p class="line-clamp-3">
        Ed. ${param.libro_editorial}
        <br>
        <span class="">Año: 2025</span>
      </p>
      
    </div>
        
  </div>
  <form class="mx-auto" action="/libreria/libros/ver/<%= request.getParameter("libro_slug") %>" method="post">
      <input type="hidden" name="id" value="<%= request.getParameter("libro_id") %>">  
      <button
      type="submit"
      class="bg-neutral-50 font-bold w-50 text-indigo-500 rounded enlace-animado"
  >
    Ver libro
  </button>
  </form>
</div>