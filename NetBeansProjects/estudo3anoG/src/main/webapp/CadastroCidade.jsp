<%-- 
    Document   : CadastroCidade
    Created on : 28 de mar. de 2025, 08:58:56
    Author     : tulio
--%>
<%@include file="menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cidade</title>
    </head>
    <body>
        <h1>Cadastro Cidade</h1>
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
            <input type="hidden" name="codigoCidade" value="${codigoCidade}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Cidade:</label><input type="text" required="" name="nomeCidade" value="${nomeCidade}" size="40"/></p>
            <p><label>UF:</label><input type="text" required="" name="ufCidade" value="${ufCidade}" size="5"/></p>
              <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 3px" >

        </form>
            
            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            <br>
            <h3>${mensagem}</h3>
        <c:if test="${not empty listaCidade}"> 
            <table border="1">
                <tr><td>CÓDIGO</td><td>NOME</td><td>UF</td><td>ALTERAR</td><td>EXCLUIR</td></tr>  
                <c:forEach var="cidade" items="${listaCidade}">
                    <tr>
                    <td>${cidade.codigoCidade}</td>
                    <td>${cidade.nomeCidade}</td>
                    <td>${cidade.ufCidade}</td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
                            <input type="hidden" name="codigoCidade" value="${cidade.codigoCidade}">
                            <input type="hidden" name="nomeCidade" value="${cidade.nomeCidade}">
                            <input type="hidden" name="ufCidade" value="${cidade.ufCidade}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                            
                        </form>
                    </td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
                            <input type="hidden" name="codigoCidade" value="${cidade.codigoCidade}">
                            <input type="hidden" name="nomeCidade" value="${cidade.nomeCidade}">
                            <input type="hidden" name="ufCidade" value="${cidade.ufCidade}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                            
                        </form>
                    </td>
                    
                    </tr>
                </c:forEach>
            </table>  
        </c:if>


    </body>
</html>
