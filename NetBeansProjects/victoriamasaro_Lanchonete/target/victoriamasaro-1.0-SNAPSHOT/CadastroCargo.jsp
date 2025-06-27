<%-- 
    Document   : CadastroCargo
    Created on : 22 de mai de 2025
    Author     : 12968505602
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cargo</title>
    </head>
    <%@include file="menu.jsp" %>
    <body>
        <h1>Cadastro de Cargo</h1>
        
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
            
            <input type="hidden" name="codCargo" value="${codCargo}" />
            <input type="hidden" name="opcao" value="${opcao}" />

            <p><label>Nome: </label> 
               <input type="text" required="" name="nome" value="${nome}" size="40" /></p>

            <p><label>Salário Inicial: </label> 
               <input type="text" required="" name="salarioInicial" value="${salarioInicial}" size="20" /></p>

            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 2px;" />
        </form>

        <form id="cancelarForm" name="cancelar" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" name="btnCancelar" />
        </form>

        <br />
        <h3>${mensagem}</h3>

        <c:if test="${not empty listaCargo}">
            <table border="1">
                <tr>
                    <td>CÓDIGO</td>
                    <td>NOME</td>
                    <td>SALÁRIO INICIAL</td>
                    <td>ALTERAR</td>
                    <td>EXCLUIR</td>
                </tr>
                <c:forEach var="cargo" items="${listaCargo}">
                    <tr>
                        <td>${cargo.codCargo}</td>
                        <td>${cargo.nome}</td>
                        <td>${cargo.salarioInicial}</td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
                                <input type="hidden" name="codCargo" value="${cargo.codCargo}" />
                                <input type="hidden" name="nome" value="${cargo.nome}" />
                                <input type="hidden" name="salarioInicial" value="${cargo.salarioInicial}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/CargoControlador">
                                <input type="hidden" name="codCargo" value="${cargo.codCargo}" />
                                <input type="hidden" name="nome" value="${cargo.nome}" />
                                <input type="hidden" name="salarioInicial" value="${cargo.salarioInicial}" />
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
