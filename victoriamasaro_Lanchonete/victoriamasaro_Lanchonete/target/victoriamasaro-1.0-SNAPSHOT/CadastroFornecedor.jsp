<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro Fornecedor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #3333cc;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #d3d3d3;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #3333cc;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        input[type="number"],
        input[type="submit"] {
            padding: 6px;
            margin-top: 5px;
            width: 300px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            width: auto;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .mensagem {
            margin: 10px 0;
            color: red;
        }

        a {
            display: inline-block;
            margin-top: 10px;
            color: white;
            background-color: #cc0000;
            padding: 6px 10px;
            text-decoration: none;
            border-radius: 4px;
        }

        a:hover {
            background-color: #a30000;
        }
    </style>
</head>
<body>
    <h1>Cadastro de Fornecedor</h1>

    <c:if test="${not empty mensagem}">
        <div class="mensagem">${mensagem}</div>
    </c:if>

    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
        <input type="hidden" name="codFornecedor" value="${codFornecedor}">
        <input type="hidden" name="opcao" value="${opcao}">

        <label>Nome:</label>
        <input type="text" name="nome" value="${nome}" required>

        <label>CNPJ:</label>
        <input type="text" name="cnpj" value="${cnpj}" required>

        <label>Telefone:</label>
        <input type="text" name="telefone" value="${telefone}" required>

        <label>Endereço:</label>
        <input type="text" name="endereco" value="${endereco}" required>

        <label>Custo:</label>
        <input type="text" name="custo" value="${custo}" required>

        <br><br>
        <input type="submit" value="Salvar">
        <a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar">Cancelar</a>
    </form>

    <c:if test="${not empty listaFornecedor}">
        <table>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Telefone</th>
                <th>Endereço</th>
                <th>Custo</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <c:forEach var="fornecedor" items="${listaFornecedor}">
                <tr>
                    <td>${fornecedor.codFornecedor}</td>
                    <td>${fornecedor.nome}</td>
                    <td>${fornecedor.cnpj}</td>
                    <td>${fornecedor.telefone}</td>
                    <td>${fornecedor.endereco}</td>
                    <td>${fornecedor.custo}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                            <input type="hidden" name="opcao" value="editar"/>
                            <input type="hidden" name="codFornecedor" value="${fornecedor.codFornecedor}"/>
                            <input type="hidden" name="nome" value="${fornecedor.nome}"/>
                            <input type="hidden" name="cnpj" value="${fornecedor.cnpj}"/>
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}"/>
                            <input type="hidden" name="endereco" value="${fornecedor.endereco}"/>
                            <input type="hidden" name="custo" value="${fornecedor.custo}"/>
                            <input type="submit" value="Editar"/>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                            <input type="hidden" name="opcao" value="excluir"/>
                            <input type="hidden" name="codFornecedor" value="${fornecedor.codFornecedor}"/>
                            <input type="hidden" name="nome" value="${fornecedor.nome}"/>
                            <input type="hidden" name="cnpj" value="${fornecedor.cnpj}"/>
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}"/>
                            <input type="hidden" name="endereco" value="${fornecedor.endereco}"/>
                            <input type="hidden" name="custo" value="${fornecedor.custo}"/>
                            <input type="submit" value="Excluir"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
