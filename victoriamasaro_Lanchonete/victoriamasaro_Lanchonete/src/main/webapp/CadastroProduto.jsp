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
        /* (Mantenha todo seu CSS atual, omitido aqui para brevidade) */
    </style>

    <script>
        function validarNumero(campo) {
            let valor = campo.value;
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

        <!-- Aqui o formulário usa GET para manter padrão Cliente -->
        <form id="cadastroForm" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador">
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
                                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" style="display:inline;">
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

                                <!-- Botão Excluir -->
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" style="display:inline;">
                                        <input type="hidden" name="codigoProduto" value="${produto.codProduto}">
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
