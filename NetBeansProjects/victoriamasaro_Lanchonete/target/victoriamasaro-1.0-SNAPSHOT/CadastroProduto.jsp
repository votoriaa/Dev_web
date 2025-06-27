<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro Produto</title>
    <style>
        /* Seu CSS original mantido igual */
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            display: flex;
            justify-content: center;
            padding: 40px 0;
        }
        .container {
            background: white;
            padding: 25px 40px;
            border-radius: 8px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            width: 600px;
        }
        h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        form {
            margin-bottom: 30px;
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="number"],
        select,
        textarea {
            width: 100%;
            padding: 8px 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
            box-sizing: border-box;
            resize: vertical;
        }
        textarea {
            height: 100px;
            font-family: Arial, sans-serif;
        }
        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 18px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            text-align: center;
            font-size: 0.9rem;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 6px;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #1e7e34;
        }
        .btn-excluir {
            background-color: #dc3545;
        }
        .btn-excluir:hover {
            background-color: #a71d2a;
        }
        .mensagem {
            text-align: center;
            color: #d9534f;
            margin-bottom: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cadastro Produto</h1>
        
        <c:if test="${not empty mensagem}">
            <div class="mensagem">${mensagem}</div>
        </c:if>

        <form id="cadastroForm" name="cadastro" method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
            <input type="hidden" name="codigoProduto" value="${codigoProduto}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            
            <label for="nomeProduto">Nome:</label>
            <input type="text" id="nomeProduto" name="nomeProduto" required value="${nomeProduto}" />

            <label for="ingredientesProduto">Ingredientes:</label>
            <textarea id="ingredientesProduto" name="ingredientesProduto">${ingredientesProduto}</textarea>

            <label for="quantidadeProduto">Quantidade:</label>
            <input type="number" id="quantidadeProduto" name="quantidadeProduto" step="0.01" required value="${quantidadeProduto}" />

            <label for="precoCustoProduto">Preço Custo:</label>
            <input type="number" id="precoCustoProduto" name="precoCustoProduto" step="0.01" required value="${precoCustoProduto}" />

            <label for="precoVendaProduto">Preço Venda:</label>
            <input type="number" id="precoVendaProduto" name="precoVendaProduto" step="0.01" required value="${precoVendaProduto}" />

            <label for="marcaProduto">Marca:</label>
            <select id="marcaProduto" name="codMarca" required>
                <c:forEach var="marca" items="${listaMarca}">
                    <option value="${marca.codMarca}" ${marca.codMarca == codMarca ? "selected" : ""}>${marca.nome}</option>
                </c:forEach>
            </select>

            <label for="categoriaProduto">Categoria:</label>
            <select id="categoriaProduto" name="codCategoria" required>
                <c:forEach var="categoria" items="${listaCategoria}">
                    <option value="${categoria.codCategoria}" ${categoria.codCategoria == codCategoria ? "selected" : ""}>${categoria.nome}</option>
                </c:forEach>
            </select>

            <input type="submit" value="Salvar" />
        </form>

        <form id="cancelarForm" name="cancelar" method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" />
        </form>

        <c:if test="${not empty listaProduto}">
            <table>
                <thead>
                    <tr>
                        <th>CÓDIGO</th>
                        <th>NOME</th>
                        <th>INGREDIENTES</th>
                        <th>QUANTIDADE</th>
                        <th>PREÇO CUSTO</th>
                        <th>PREÇO VENDA</th>
                        <th>MARCA</th>
                        <th>CATEGORIA</th>
                        <th>ALTERAR</th>
                        <th>EXCLUIR</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${listaProduto}">
                        <tr>
                            <td>${produto.codProduto}</td>
                            <td>${produto.nome}</td>
                            <td style="text-align:left;">${produto.ingredientes}</td>
                            <td>${produto.quantidade}</td>
                            <td>${produto.precoCusto}</td>
                            <td>${produto.precoVenda}</td>
                            <td>${produto.objMarca.nome}</td>
                            <td>${produto.objCategoria.nome}</td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
                                    <input type="hidden" name="codigoProduto" value="${produto.codProduto}"/>
                                    <input type="hidden" name="nomeProduto" value="${produto.nome}"/>
                                    <input type="hidden" name="ingredientesProduto" value="${produto.ingredientes}"/>
                                    <input type="hidden" name="quantidadeProduto" value="${produto.quantidade}"/>
                                    <input type="hidden" name="precoCustoProduto" value="${produto.precoCusto}"/>
                                    <input type="hidden" name="precoVendaProduto" value="${produto.precoVenda}"/>
                                    <input type="hidden" name="codMarca" value="${produto.objMarca.codMarca}"/>
                                    <input type="hidden" name="codCategoria" value="${produto.objCategoria.codCategoria}"/>
                                    <input type="hidden" name="opcao" value="confirmarEditar"/>
                                    <button type="submit">Editar</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
                                    <input type="hidden" name="codigoProduto" value="${produto.codProduto}"/>
                                    <input type="hidden" name="opcao" value="confirmarExcluir"/>
                                    <button type="submit" class="btn-excluir">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
