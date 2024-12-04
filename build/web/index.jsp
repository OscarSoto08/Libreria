<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--jstl-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.titulo_pag}</title>
        <!--Tailwind-->
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="icon flavicon" href="<%= request.getContextPath() %>/img/libro.png"/>
        
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"/>
        
        
    </head>
    <body>
        <jsp:include page="/componentes/navbar.jsp"/>
        <% 
            if (session.getAttribute("redirigido") == null) {
                session.setAttribute("redirigido", true); 
        %>
        <script>
            window.location.href = 'libros/inicio';
        </script>
        <jsp:include page="/paginas/bienvenida.jsp" />
        <% 
            }
            else{
        %>
        <jsp:include page="${requestScope.pagina}" /> 
        <% } %>       
        
        <script src="https://kit.fontawesome.com/14596e32cc.js" crossorigin="anonymous"></script>
    </body>
</html>