<%-- 
    Document   : CadastroCategoria
    Created on : 22 de mai de 2025
    Author     : 12968505602
--%>
  <%@include file="menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Categoria</title>
    </head>
    <body>
        <h1>Cadastro de Categoria</h1>
        
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador">
            
            <input type="hidden" name="codCategoria" value="${codCategoria}" />
            <input type="hidden" name="opcao" value="${opcao}" />

            <p><label>Nome: </label> 
               <input type="text" required="" name="nome" value="${nome}" size="40" /></p>

            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 2px;" />
        </form>

        <form id="cancelarForm" name="cancelar" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" name="btnCancelar" />
        </form>

        <br />
        <h3>${mensagem}</h3>

        <c:if test="${not empty listaCategoria}">
            <table border="1">
                <tr>
                    <td>CÓDIGO</td>
                    <td>NOME</td>
                    <td>ALTERAR</td>
                    <td>EXCLUIR</td>
                </tr>
                <c:forEach var="categoria" items="${listaCategoria}">
                    <tr>
                        <td>${categoria.codCategoria}</td>
                        <td>${categoria.nome}</td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador">
                                <input type="hidden" name="codCategoria" value="${categoria.codCategoria}" />
                                <input type="hidden" name="nome" value="${categoria.nome}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador">
                                <input type="hidden" name="codCategoria" value="${categoria.codCategoria}" />
                                <input type="hidden" name="nome" value="${categoria.nome}" />
                                <input type="hidden" name="opcao" value="excluir" />
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
