<%-- 
    Document   : CadastroCidade
    Created on : 28 de mar de 2025, 08:59:10
    Author     : 12172700606
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cidade</title>
    </head>
    <body>
        <h1>Cadastro Cidade</h1>
        <form id="cadastroForm" name="cadastro" 
             method="get" action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
             <p><label>Cidade:</label><input type="text" name="nomeCidade" value="" size="40"></p>
             <p><label>UF:</label><input type="text" name="ufCidade" value="" size="5"></p>
             <input type="submit" value="Salvar" name="Salvar"/>          
        </form>
    </body>
</html>
