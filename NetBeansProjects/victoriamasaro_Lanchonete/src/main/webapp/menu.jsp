<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu Principal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            text-align: center;
        }
        h1 {
            margin-top: 50px;
        }
        .menu {
            margin: 40px auto;
            width: 300px;
        }
        .menu a {
            display: block;
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            margin: 10px 0;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .menu a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h1>Menu Principal</h1>
    
    <div class="menu">
        <a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cliente</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador?opcao=cancelar">Marca</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador?opcao=cancelar">Categoria</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/CargoControlador?opcao=cancelar">Cargo</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador?opcao=cancelar">Produto</a>
    </div>

</body>
</html>