<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getAttribute("titulo_pag") %></title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="icon flavicon" href="<%= request.getContextPath() %>/img/libro.png"/>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"/>
    </head>
    <body>
        <jsp:include page="/componentes/navbar.jsp"/>
        <% 
            if (session.getAttribute("redirigido") == null) {
                session.setAttribute("redirigido", true); 
        %>
        <jsp:include page="/paginas/bienvenida.jsp" />
        <script>
            window.location.href = 'libros/inicio';
        </script>
        <% 
            }
            else{
        %>
        <jsp:include page="${requestScope.pagina}" />
        <% } %>
        <script src="https://kit.fontawesome.com/14596e32cc.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>