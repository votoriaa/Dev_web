<%-- 
    Document   : CadastroCidade
    Created on : 28 de mar de 2025, 08:59:10
    Author     : 13826640608
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Marca</title>
    </head>
    <body>
        <h1>Cadastro Marca</h1>
        <form id="cadastroForm" name="cadastro" 
              method="get" action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
             <input type="hidden" name="opcao" value="${opcao}">
             <input type="hidden" name="codMarca" value="${codMarca}">
            <p><label>Nome da marca:</label><input type="text" required="" name="nome" value="${nome}" size="40"></p>
            <p><label>Observações:</label><input type="text" required="" name="observacoes" value="${observacoes}" size="20"></p>
          <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 3px" >     
        </form>
          <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            <br> <h3>${mensagem}</h3>
            


        <c:if test="${not empty listaCidade}">
            <table border = "1">
                <tr>
                    <td>CÓDIGO</TD>
                    <td>NOME MARCA</td>
                    <td>OBSERVACOES</td>
                    <td>ALTERAR</td>
                    <td>EXCLUIR</td>
                </tr>
                
                <c:forEach var="marca" items="${listaCidade}">
                    <tr>
                        <td> ${marca.codMarca}</td>
                        <td> ${marca.nome}</td>
                        <td> ${marca.obervacoes}</td> 
                        <td><form id="cadastroForm" name="cadastro" method="get"
                                  action="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador">
                                <input type="hidden" name="codMarca" value="${marca.codMarca}">
                                <input type="hidden" name="nome" value="${marca.nome}">
                                <input type="hidden" name="obervacoes" value="${marca.obervacoes}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit">Editar</button>
                                <input type="hidden" name="opcao" value="excluir">
                                 </form>
                            </td> 
               
                           
                         <td><form id="cadastroForm" name="cadastro" method="get"
                                  action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
                                <input type="hidden" name="codMarca" value="${marca.codMarca}">
                                <input type="hidden" name="nome" value="${marca.nome}">
                                <input type="hidden" name="obervacoes" value="${marca.obervacoes}">
                                <input type="hidden" name="opcao" value="excluir">
                                <button type="submit">excluir</button>
                                <input type="hidden" name="opcao" value="excluir">
                                 </form>
                            </td> 
                            
                                
                    </tr>
                    
                    
                   
                        
                </c:forEach>
                           
            </table>
    </c:if>


</body>
</html>
