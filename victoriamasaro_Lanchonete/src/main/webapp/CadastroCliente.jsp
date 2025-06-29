<%-- 
    Document   : CadastroCliente
    Created on : 16 de mai de 2025
    Author     : 12968505602
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="ocultarBoasVindas" value="true"/>
<%@ include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro Cliente</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        
        <style>
            /* Reset Radical */
            * {
                margin: 0;
                padding: 0;
                border: 0;
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

            /* Formulários */
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
            input[type="date"],
            select {
                padding: 8px 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 1rem;
                width: 100%;
                max-width: 320px;
            }

            input[type="text"][size="2"] {
                max-width: 50px;
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
                input[type="date"],
                select {
                    max-width: 100%;
                }
                
                .content {
                    padding: 15px;
                }
            }
        </style>

        <script>
            // Função para máscara de CPF
            function mascaraCPF(i){
                var v = i.value.replace(/\D/g,'');
                v = v.replace(/(\d{3})(\d)/,"$1.$2");
                v = v.replace(/(\d{3})(\d)/,"$1.$2");
                v = v.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
                i.value = v;
            }

            // Função para máscara de telefone
            function mascaraTelefone(i){
                var v = i.value.replace(/\D/g,'');
                if(v.length > 10){
                    // formato (99) 99999-9999
                    v = v.replace(/^(\d{2})(\d{5})(\d{4}).*/,"($1) $2-$3");
                } else {
                    // formato (99) 9999-9999
                    v = v.replace(/^(\d{2})(\d{4})(\d{0,4}).*/,"($1) $2-$3");
                }
                i.value = v;
            }
        </script>
    </head>
    <body>
        <div class="page-container">
            <div class="content">
                <h1>Cadastro Cliente</h1>
                
                <c:if test="${not empty mensagem}">
                    <div class="mensagem">${mensagem}</div>
                </c:if>

                <form id="cadastroForm" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                    <input type="hidden" name="codCliente" value="${codCliente}">
                    <input type="hidden" name="opcao" value="${opcao}">
                    
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input type="text" id="nome" required name="nome" value="${nome}" maxlength="100" size="40">
                    </div>
                    
                    <div class="form-group">
                        <label for="cpf">CPF:</label>
                        <input type="text" id="cpf" required name="cpf" value="${cpf}" maxlength="14" oninput="mascaraCPF(this)" size="20" placeholder="000.000.000-00">
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" required name="email" value="${email}" maxlength="100" size="40">
                    </div>
                    
                    <div class="form-group">
                        <label for="dataNascimento">Data de Nascimento:</label>
                        <input type="date" id="dataNascimento" required name="dataNascimento" value="${dataNascimento}">
                    </div>
                    
                    <div class="form-group">
                        <label for="telefone">Telefone:</label>
                        <input type="text" id="telefone" required name="telefone" value="${telefone}" maxlength="15" oninput="mascaraTelefone(this)" size="20" placeholder="(00) 00000-0000">
                    </div>
                    
                    <div class="form-group">
                        <label for="endereco">Endereço:</label>
                        <input type="text" id="endereco" required name="endereco" value="${endereco}" maxlength="100" size="40">
                    </div>
                    
                    <div class="form-group">
                        <label for="bairro">Bairro:</label>
                        <input type="text" id="bairro" required name="bairro" value="${bairro}" maxlength="50" size="30">
                    </div>
                    
                    <div class="form-group">
                        <label for="cidade">Cidade:</label>
                        <input type="text" id="cidade" required name="cidade" value="${cidade}" maxlength="50" size="30">
                    </div>
                    
                    <div class="form-group">
                        <label for="uf">UF:</label>
                        <select id="uf" name="uf" required>
                            <option value="">Selecione</option>
                            <option value="AC" ${uf == 'AC' ? 'selected' : ''}>AC</option>
                            <option value="AL" ${uf == 'AL' ? 'selected' : ''}>AL</option>
                            <option value="AP" ${uf == 'AP' ? 'selected' : ''}>AP</option>
                            <option value="AM" ${uf == 'AM' ? 'selected' : ''}>AM</option>
                            <option value="BA" ${uf == 'BA' ? 'selected' : ''}>BA</option>
                            <option value="CE" ${uf == 'CE' ? 'selected' : ''}>CE</option>
                            <option value="DF" ${uf == 'DF' ? 'selected' : ''}>DF</option>
                            <option value="ES" ${uf == 'ES' ? 'selected' : ''}>ES</option>
                            <option value="GO" ${uf == 'GO' ? 'selected' : ''}>GO</option>
                            <option value="MA" ${uf == 'MA' ? 'selected' : ''}>MA</option>
                            <option value="MT" ${uf == 'MT' ? 'selected' : ''}>MT</option>
                            <option value="MS" ${uf == 'MS' ? 'selected' : ''}>MS</option>
                            <option value="MG" ${uf == 'MG' ? 'selected' : ''}>MG</option>
                            <option value="PA" ${uf == 'PA' ? 'selected' : ''}>PA</option>
                            <option value="PB" ${uf == 'PB' ? 'selected' : ''}>PB</option>
                            <option value="PR" ${uf == 'PR' ? 'selected' : ''}>PR</option>
                            <option value="PE" ${uf == 'PE' ? 'selected' : ''}>PE</option>
                            <option value="PI" ${uf == 'PI' ? 'selected' : ''}>PI</option>
                            <option value="RJ" ${uf == 'RJ' ? 'selected' : ''}>RJ</option>
                            <option value="RN" ${uf == 'RN' ? 'selected' : ''}>RN</option>
                            <option value="RS" ${uf == 'RS' ? 'selected' : ''}>RS</option>
                            <option value="RO" ${uf == 'RO' ? 'selected' : ''}>RO</option>
                            <option value="RR" ${uf == 'RR' ? 'selected' : ''}>RR</option>
                            <option value="SC" ${uf == 'SC' ? 'selected' : ''}>SC</option>
                            <option value="SP" ${uf == 'SP' ? 'selected' : ''}>SP</option>
                            <option value="SE" ${uf == 'SE' ? 'selected' : ''}>SE</option>
                            <option value="TO" ${uf == 'TO' ? 'selected' : ''}>TO</option>
                        </select>
                    </div>

                    <div class="form-buttons">
                        <input type="submit" class="btn-salvar" value="Salvar" name="btnSalvar">
                        <a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar" class="btn-cancelar">Cancelar</a>
                    </div>
                </form>

                <c:if test="${not empty listaCliente}">
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>CÓDIGO</th>
                                    <th>NOME</th>
                                    <th>CPF</th>
                                    <th>EMAIL</th>
                                    <th>DATA NASC.</th>
                                    <th>TELEFONE</th>
                                    <th>ENDEREÇO</th>
                                    <th>BAIRRO</th>
                                    <th>CIDADE</th>
                                    <th>UF</th>
                                    <th>ALTERAR</th>
                                    <th>EXCLUIR</th>
                                </tr>
                            </thead>
                            <tbody>
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
                                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" style="display:inline;">
                                                <input type="hidden" name="codCliente" value="${cliente.codCliente}">
                                                <input type="hidden" name="nome" value="${cliente.nome}">
                                                <input type="hidden" name="cpf" value="${cliente.cpf}">
                                                <input type="hidden" name="email" value="${cliente.email}">
                                                <input type="hidden" name="dataNascimento" value="${cliente.dataNascimento}">
                                                <input type="hidden" name="telefone" value="${cliente.telefone}">
                                                <input type="hidden" name="endereco" value="${cliente.endereco}">
                                                <input type="hidden" name="bairro" value="${cliente.bairro}">
                                                <input type="hidden" name="cidade" value="${cliente.cidade}">
                                                <input type="hidden" name="uf" value="${cliente.uf}">
                                                <input type="hidden" name="opcao" value="editar">
                                                <button type="submit" class="table-btn">Editar</button>
                                            </form>
                                        </td>
                                        
                                        <td>
                                            <form method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" style="display:inline;">
                                                <input type="hidden" name="codCliente" value="${cliente.codCliente}">
                                                <input type="hidden" name="nome" value="${cliente.nome}">
                                                <input type="hidden" name="cpf" value="${cliente.cpf}">
                                                <input type="hidden" name="email" value="${cliente.email}">
                                                <input type="hidden" name="dataNascimento" value="${cliente.dataNascimento}">
                                                <input type="hidden" name="telefone" value="${cliente.telefone}">
                                                <input type="hidden" name="endereco" value="${cliente.endereco}">
                                                <input type="hidden" name="bairro" value="${cliente.bairro}">
                                                <input type="hidden" name="cidade" value="${cliente.cidade}">
                                                <input type="hidden" name="uf" value="${cliente.uf}">
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
