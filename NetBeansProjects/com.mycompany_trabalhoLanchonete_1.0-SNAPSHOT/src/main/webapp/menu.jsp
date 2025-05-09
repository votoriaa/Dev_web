<%-- 
    Document   : menu
    Created on : 21 de mar de 2025, 10:28:40
    Author     : 12172700606
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
                <li> <a href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                <li> <a href="${pageContext.request.contextPath}/CidadeControlador?opcao=cancelar">CIDADE</a></li>
                <li> <a href="${pageContext.request.contextPath}/FuncionarioControlador?opcao=cancelar">FUNCIONARIO</a></li>
                <li> <a href="${pageContext.request.contextPath}/login.jsp">LOGIN</a></li>
                <li> <a href="${pageContext.request.contextPath}/logout.jsp">LOGOUT</a></li>
            </ul>
        </nav>
    </body>
</html>
