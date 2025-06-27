<%-- 
    Document   : CadastroMarca
    Created on : 16 de mai de 2025
    Author     : 12968505602
--%>
  <%@include file="menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Marca</title>
    </head>
    <body>
        <h1>Cadastro de Marca</h1>
        
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
            
            <input type="hidden" name="codMarca" value="${codMarca}" />
            <input type="hidden" name="opcao" value="${opcao}" />

            <p><label>Nome: </label> 
               <input type="text" required="" name="nome" value="${nome}" size="40" /></p>

            <p><label>Observações: </label> 
               <input type="text" required="" name="observacoes" value="${observacoes}" size="60" /></p>

            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 2px;" />
        </form>

        <form id="cancelarForm" name="cancelar" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" name="btnCancelar" />
        </form>

        <br />
        <h3>${mensagem}</h3>

        <c:if test="${not empty listaMarca}">
            <table border="1">
                <tr>
                    <td>CÓDIGO</td>
                    <td>NOME</td>
                    <td>OBSERVAÇÕES</td>
                    <td>ALTERAR</td>
                    <td>EXCLUIR</td>
                </tr>
                <c:forEach var="marca" items="${listaMarca}">
                    <tr>
                        <td>${marca.codMarca}</td>
                        <td>${marca.nome}</td>
                        <td>${marca.observacoes}</td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
                                <input type="hidden" name="codMarca" value="${marca.codMarca}" />
                                <input type="hidden" name="nome" value="${marca.nome}" />
                                <input type="hidden" name="observacoes" value="${marca.observacoes}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
                                <input type="hidden" name="codMarca" value="${marca.codMarca}" />
                                <input type="hidden" name="nome" value="${marca.nome}" />
                                <input type="hidden" name="observacoes" value="${marca.observacoes}" />
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
