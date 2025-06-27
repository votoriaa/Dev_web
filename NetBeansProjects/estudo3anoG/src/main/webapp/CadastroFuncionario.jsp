<%-- 
    Document   : CadastroFuncionario
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
        <title>Cadastro Funcionário</title>
    </head>
    <body>
        <h1>Cadastro Funcionário</h1>
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
            <input type="hidden" name="codigoFuncionario" value="${codigoFuncionario}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Funcionário:</label><input type="text" required="" name="nomeFuncionario" value="${nomeFuncionario}" size="40"/></p>
            <p><label>Salário:</label><input type="number" required="" name="salarioFuncionario" value="${salarioFuncionario}" size="15"/></p>
             
            <p><label>Nascimento:</label><input type="date"  name="nascimentoFuncionario" value="${nascimentoFuncionario}" /></p>
             
            <p><label>Cidade:</label>
                <select name="cidadeFuncionario">
                    <c:forEach var="cidade" items="${listaCidade}">
                        <c:choose>
                            <c:when test="${cidade.codigoCidade eq cidadeFuncionario}">
                                <option selected value="${cidade.codigoCidade}">${cidade.nomeCidade}</option> 
                            </c:when>
                            <c:otherwise>
                                <option value="${cidade.codigoCidade}">${cidade.nomeCidade}</option> 
                            </c:otherwise>
                        </c:choose>
                   </c:forEach>
                    
                </select>
            
            </p>
            
            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 3px" >

        </form>
            
            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            <br>
            <h3>${mensagem}</h3>
        <c:if test="${not empty listaFuncionario}"> 
            <table border="1">
                <tr><td>CÓDIGO</td><td>NOME</td><td>SALÁRIO</td><td>NASCIMENTO</td><td>CIDADE</td><td>ALTERAR</td><td>EXCLUIR</td></tr>  
                <c:forEach var="funcionario" items="${listaFuncionario}">
                    <tr>
                    <td>${funcionario.codigoFuncionario}</td>
                    <td>${funcionario.nomeFuncionario}</td>
                    <td>${funcionario.salarioFuncionario}</td>
                    <td>${funcionario.nascimentoFormatado}</td>
                    <td>${funcionario.objCidade.nomeCidade}</td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                            <input type="hidden" name="codigoFuncionario" value="${funcionario.codigoFuncionario}">
                            <input type="hidden" name="nomeFuncionario" value="${funcionario.nomeFuncionario}">
                            <input type="hidden" name="salarioFuncionario" value="${funcionario.salarioFuncionario}">
                            <input type="hidden" name="nascimentoFuncionario" value="${funcionario.nascimentoFormatado}">
                            <input type="hidden" name="cidadeFuncionario" value="${funcionario.objCidade.codigoCidade}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                            
                        </form>
                    </td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                            <input type="hidden" name="codigoFuncionario" value="${funcionario.codigoFuncionario}">
                            <input type="hidden" name="nomeFuncionario" value="${funcionario.nomeFuncionario}">
                            <input type="hidden" name="salarioFuncionario" value="${funcionario.salarioFuncionario}">
                            <input type="hidden" name="nascimentoFuncionario" value="${funcionario.nascimentoFormatado}">
                            <input type="hidden" name="cidadeFuncionario" value="${funcionario.objCidade.codigoCidade}">
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
