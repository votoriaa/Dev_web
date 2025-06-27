<%-- 
    Document   : CadastroCliente
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
        <title>Cadastro Cliente</title>
    </head>
    <body>
        <h1>Cadastro Cliente</h1>
        <form 
            id="cadastroForm" name="cadastro" method="get" 
            action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
            <input type="hidden" name="codCliente" value="${codCliente}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            
            <p><label>Nome: </label> <input type="text" required="" name="nome" value="${nome}" size="40"/></p>
            <p><label>CPF: </label> <input type="text" required="" name="cpf" value="${cpf}" size="20"/></p>
            <p><label>Email: </label> <input type="text" required="" name="email" value="${email}" size="40"/></p>
            <p><label>Data de Nascimento: </label> <input type="date" required="" name="dataNascimento" value="${dataNascimento}"/></p>
            <p><label>Telefone: </label> <input type="text" required="" name="telefone" value="${telefone}" size="20"/></p>
            <p><label>Endereço: </label> <input type="text" required="" name="endereco" value="${endereco}" size="40"/></p>
            <p><label>Bairro: </label> <input type="text" required="" name="bairro" value="${bairro}" size="30"/></p>
            <p><label>Cidade: </label> <input type="text" required="" name="cidade" value="${cidade}" size="30"/></p>
            <p><label>UF: </label> <input type="text" required="" name="uf" value="${uf}" size="2"/></p>
            
            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 2px"/>
        </form>
        
        <form id="cancelarForm" name="cancelar" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
            <input type="hidden" name="opcao" value="cancelar"/>
            <input type="submit" value="Cancelar" name="btnCancelar"/>
        </form>
        
        <br>
        <h3>${mensagem}</h3>
        
        <c:if test="${not empty listaCliente}">
            <table border="1">
                <tr>
                    <td>CÓDIGO</td><td>NOME</td><td>CPF</td><td>EMAIL</td><td>DATA NASC.</td><td>TELEFONE</td>
                    <td>ENDEREÇO</td><td>BAIRRO</td><td>CIDADE</td><td>UF</td><td>ALTERAR</td><td>EXCLUIR</td>
                </tr>
                <c:forEach var="cliente" items="${listaCliente}">
                    <tr>
                        <td>${cliente.codCliente}</td>
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.email}</td>
                        <td>${cliente.dataNascimento}</td>
                        <td>${cliente.telefone}</td>
                        <td>${cliente.endereco}</td>
                        <td>${cliente.bairro}</td>
                        <td>${cliente.cidade}</td>
                        <td>${cliente.uf}</td>
                        
                        <td>
                            <form id="editarForm" name="editar" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                <input type="hidden" name="codCliente" value="${cliente.codCliente}"/>
                                <input type="hidden" name="nome" value="${cliente.nome}"/>
                                <input type="hidden" name="cpf" value="${cliente.cpf}"/>
                                <input type="hidden" name="email" value="${cliente.email}"/>
                                <input type="hidden" name="dataNascimento" value="${cliente.dataNascimento}"/>
                                <input type="hidden" name="telefone" value="${cliente.telefone}"/>
                                <input type="hidden" name="endereco" value="${cliente.endereco}"/>
                                <input type="hidden" name="bairro" value="${cliente.bairro}"/>
                                <input type="hidden" name="cidade" value="${cliente.cidade}"/>
                                <input type="hidden" name="uf" value="${cliente.uf}"/>
                                <input type="hidden" name="opcao" value="editar"/>
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        
                        <td>
                            <form id="excluirForm" name="excluir" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                <input type="hidden" name="codCliente" value="${cliente.codCliente}"/>
                                <input type="hidden" name="nome" value="${cliente.nome}"/>
                                <input type="hidden" name="cpf" value="${cliente.cpf}"/>
                                <input type="hidden" name="email" value="${cliente.email}"/>
                                <input type="hidden" name="dataNascimento" value="${cliente.dataNascimento}"/>
                                <input type="hidden" name="telefone" value="${cliente.telefone}"/>
                                <input type="hidden" name="endereco" value="${cliente.endereco}"/>
                                <input type="hidden" name="bairro" value="${cliente.bairro}"/>
                                <input type="hidden" name="cidade" value="${cliente.cidade}"/>
                                <input type="hidden" name="uf" value="${cliente.uf}"/>
                                <input type="hidden" name="opcao" value="excluir"/>
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
