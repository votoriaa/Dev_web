<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="ocultarBoasVindas" value="true"/>
<%@ include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro Produto</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

    <style>
        * {
            margin: 0; padding: 0; border: 0;
            box-sizing: border-box;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background: #FFF5E1;
            color: #4A2C2A;
            line-height: 1;
            min-height: 100vh;
        }
        .page-container {
            border: 4px solid #E25822;
            border-radius: 12px;
            max-width: 1200px;
            margin: 10px auto 20px;
            background: #fff8f0;
            box-shadow: 0 0 20px rgba(226, 88, 34, 0.3);
            padding: 10px 0 20px;
        }
        .content {
            padding: 20px 30px;
            margin: 0 auto;
            overflow-x: auto;
            max-width: 1200px;
        }
        h1 {
            text-align: center;
            font-weight: 700;
            font-size: 2.2rem;
            margin: 0 0 15px;
            color: #E25822;
            line-height: 1.2;
        }
        form {
            max-width: 700px;
            margin: 0 auto 30px auto;
        }
        .form-group {
            display: grid;
            grid-template-columns: 140px 1fr;
            gap: 10px;
            margin-bottom: 15px;
            align-items: center;
            justify-content: center;
            max-width: 700px;
            margin-left: auto;
            margin-right: auto;
        }
        label {
            font-weight: 700;
            font-size: 1.05rem;
            text-align: right;
            padding-right: 10px;
        }
        input[type="text"],
        select,
        textarea {
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 1rem;
            width: 100%;
            max-width: 320px;
            font-family: 'Roboto', Arial, sans-serif;
        }
        textarea {
            resize: vertical;
            min-height: 80px;
        }
        /* Botões */
        .form-buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin: 20px 0;
        }
        .btn-salvar, .btn-cancelar {
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            font-weight: 700;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
            min-width: 100px;
        }
        .btn-salvar {
            background-color: #28a745;
        }
        .btn-salvar:hover {
            background-color: #218838;
        }
        .btn-cancelar {
            background-color: #dc3545;
            text-decoration: none;
            text-align: center;
            line-height: normal;
            display: inline-block;
        }
        .btn-cancelar:hover {
            background-color: #c82333;
        }
        /* Mensagens */
        .mensagem {
            display: block;
            text-align: center;
            color: #d9534f;
            font-weight: 700;
            margin: 0 0 15px;
            padding: 10px;
            background-color: #fff3e6;
            border-radius: 4px;
        }
        /* Tabela */
        .table-wrapper {
            overflow-x: auto;
            width: 100%;
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 0.9rem;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 8px;
            border-bottom: 1px solid #E25822;
            text-align: center;
        }
        th {
            background-color: #E25822;
            color: white;
            font-weight: 700;
        }
        tr:hover {
            background-color: #fff3e6;
        }
        .table-btn {
            padding: 5px 10px;
            font-size: 0.85rem;
            background-color: #E25822;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin: 2px;
        }
        .table-btn:hover {
            background-color: #cc4c1a;
        }
        /* Responsividade */
        @media (max-width: 768px) {
            .form-group {
                grid-template-columns: 1fr;
            }
            label {
                text-align: left;
                padding-right: 0;
                margin-bottom: 5px;
            }
            input[type="text"],
            select,
            textarea {
                max-width: 100%;
            }
            .content {
                padding: 15px;
            }
        }
    </style>

    <script>
        // Opcional: validação simples para números com vírgula e ponto
        function validarNumero(campo) {
            let valor = campo.value;
            // aceita dígitos, vírgula e ponto
            let regex = /^[0-9.,]*$/;
            if (!regex.test(valor)) {
                campo.value = valor.replace(/[^0-9.,]/g, '');
            }
        }
    </script>
</head>
<body>
<div class="page-container">
    <div class="content">
        <h1>Cadastro Produto</h1>
<c:if test="${not empty mensagem}">
    <div class="mensagem">${mensagem}</div>
</c:if>
        <form id="cadastroForm" method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
            <input type="hidden" name="codigoProduto" value="${codigoProduto}">
            <input type="hidden" name="opcao" value="${opcao}">

            <div class="form-group">
                <label for="nomeProduto">Nome:</label>
                <input type="text" id="nomeProduto" required name="nomeProduto" maxlength="100" size="40" value="${nomeProduto}">
            </div>

            <div class="form-group">
                <label for="ingredientesProduto">Ingredientes:</label>
                <textarea id="ingredientesProduto" name="ingredientesProduto" required maxlength="1000" rows="4">${ingredientesProduto}</textarea>
            </div>

            <div class="form-group">
                <label for="quantidadeProduto">Quantidade:</label>
                <input type="text" id="quantidadeProduto" required name="quantidadeProduto" maxlength="20" size="20" value="${quantidadeProduto}" oninput="validarNumero(this)">
            </div>

            <div class="form-group">
                <label for="precoCustoProduto">Preço de Custo:</label>
                <input type="text" id="precoCustoProduto" required name="precoCustoProduto" maxlength="20" size="20" value="${precoCustoProduto}" oninput="validarNumero(this)">
            </div>

            <div class="form-group">
                <label for="precoVendaProduto">Preço de Venda:</label>
                <input type="text" id="precoVendaProduto" required name="precoVendaProduto" maxlength="20" size="20" value="${precoVendaProduto}" oninput="validarNumero(this)">
            </div>

            <div class="form-group">
                <label for="codMarca">Marca:</label>
                <select id="codMarca" name="codMarca" required>
                    <option value="">Selecione</option>
                    <c:forEach var="marca" items="${listaMarca}">
                        <option value="${marca.codMarca}" ${marca.codMarca == codMarca ? 'selected' : ''}>${marca.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="codCategoria">Categoria:</label>
                <select id="codCategoria" name="codCategoria" required>
                    <option value="">Selecione</option>
                    <c:forEach var="categoria" items="${listaCategoria}">
                        <option value="${categoria.codCategoria}" ${categoria.codCategoria == codCategoria ? 'selected' : ''}>${categoria.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-buttons">
                <input type="submit" class="btn-salvar" value="Salvar" name="btnSalvar">
                <a href="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador?opcao=cancelar" class="btn-cancelar">Cancelar</a>
            </div>
        </form>

        <c:if test="${not empty listaProduto}">
            <div class="table-wrapper">
                <table>
    <thead>
        <tr>
            <th>CÓDIGO</th>
            <th>NOME</th>
            <th>INGREDIENTES</th>
            <th>QUANTIDADE</th>
            <th>PREÇO DE CUSTO</th>
            <th>PREÇO DE VENDA</th>
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
                <td>${produto.ingredientes}</td>
                <td>${produto.quantidade}</td>
                <td>${produto.precoCusto}</td>
                <td>${produto.precoVenda}</td>
                <td>${produto.objMarca.nome}</td>
                <td>${produto.objCategoria.nome}</td>

                <!-- Botão Editar -->
                <td>
                    <form method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" style="display:inline;">
                        <input type="hidden" name="codigoProduto" value="${produto.codProduto}">
                        <input type="hidden" name="nomeProduto" value="${produto.nome}">
                        <input type="hidden" name="ingredientesProduto" value="${produto.ingredientes}">
                        <input type="hidden" name="quantidadeProduto" value="${produto.quantidade}">
                        <input type="hidden" name="precoCustoProduto" value="${produto.precoCusto}">
                        <input type="hidden" name="precoVendaProduto" value="${produto.precoVenda}">
                        <input type="hidden" name="codMarca" value="${produto.objMarca.codMarca}">
                        <input type="hidden" name="codCategoria" value="${produto.objCategoria.codCategoria}">
                        <input type="hidden" name="opcao" value="editar">
                        <button type="submit" class="table-btn">Editar</button>
                    </form>
                </td>

                <!-- Botão Excluir com preenchimento do formulário -->
                <td>
                    <form method="post" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" style="display:inline;">
                        <input type="hidden" name="codigoProduto" value="${produto.codProduto}">
                        <input type="hidden" name="nomeProduto" value="${produto.nome}">
                        <input type="hidden" name="ingredientesProduto" value="${produto.ingredientes}">
                        <input type="hidden" name="quantidadeProduto" value="${produto.quantidade}">
                        <input type="hidden" name="precoCustoProduto" value="${produto.precoCusto}">
                        <input type="hidden" name="precoVendaProduto" value="${produto.precoVenda}">
                        <input type="hidden" name="codMarca" value="${produto.objMarca.codMarca}">
                        <input type="hidden" name="codCategoria" value="${produto.objCategoria.codCategoria}">
                        <input type="hidden" name="opcao" value="excluir">
                        <button type="submit" class="table-btn">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

            </div>
        </c:if>
    </div>
</div>
</body>
</html>
