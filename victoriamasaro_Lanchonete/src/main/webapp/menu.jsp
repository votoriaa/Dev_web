<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Só define a variável ocultarBoasVindas se houver "opcao" --%>
<c:if test="${not empty param.opcao}">
    <c:set var="ocultarBoasVindas" value="true" scope="request" />
</c:if>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Menu Lanchonete</title>
    <style>
        * {
            margin: 0; padding: 0; box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #FFF5E1;
            color: #4A2C2A;
            min-height: 100vh;
        }

        .page-container {
            border: 4px solid #E25822;
            border-radius: 12px;
            padding-top: 70px;
            max-width: 1200px;
            margin: 20px auto;
            background: #fff8f0;
            box-shadow: 0 0 20px rgba(226, 88, 34, 0.3);
        }

        .menu-wrapper {
            position: fixed;
            top: 0; left: 50%;
            transform: translateX(-50%);
            width: 100%;
            max-width: 1200px;
            background-color: #E25822;
            box-shadow: 0 3px 8px rgba(0,0,0,0.2);
            border-radius: 0 0 12px 12px;
            z-index: 1000;
        }

        .menu-bar {
            display: flex;
            justify-content: space-around;
            align-items: center;
            height: 60px;
        }

        .menu-bar a {
            flex: 1;
            text-align: center;
            padding: 12px 10px;
            text-decoration: none;
            color: white;
            font-weight: 700;
            font-size: 1.1rem;
            text-transform: uppercase;
            letter-spacing: 1px;
            border-right: 1px solid rgba(255, 255, 255, 0.3);
            transition: background-color 0.3s ease, transform 0.2s ease;
            user-select: none;
            font-family: 'Comic Sans MS', cursive, sans-serif;
        }

        .menu-bar a:last-child {
            border-right: none;
        }

        .menu-bar a:hover,
        .menu-bar a:focus {
            background-color: #cc4c1a;
            transform: scale(1.05);
            cursor: pointer;
            outline: none;
        }

        .menu-bar a::before {
            content: attr(data-icon);
            margin-right: 6px;
            font-size: 1.3rem;
            vertical-align: middle;
        }

   /* Conteúdo da página */
        .content {
            padding: 40px 20px;
            text-align: center;
        }

        .welcome-box {
            background-color: #fff3e6;
            border-radius: 12px;
            padding: 50px 30px;
            max-width: 600px;
            margin: 0 auto;
            box-shadow: 0 4px 15px rgba(226, 88, 34, 0.15);
            color: #6d3a22;
            font-family: 'Roboto', Arial, sans-serif;
        }

        .welcome-box h1 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            font-weight: 700;
        }

        .welcome-box p {
            font-size: 1.2rem;
            line-height: 1.5;
        }

        /* Responsivo simples */
        @media (max-width: 600px) {
            .menu-bar a {
                font-size: 0.9rem;
                padding: 10px 5px;
            }

            .welcome-box {
                padding: 30px 15px;
                max-width: 90%;
            }
        }
    </style>
</head>
<body>
    <div class="page-container">
       <div class="menu-wrapper">
    <nav class="menu-bar" role="navigation" aria-label="Menu principal lanchonete">
        <a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar" data-icon="👤">Cliente</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/MarcaControlador?opcao=cancelar" data-icon="🏷️">Marca</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador?opcao=cancelar" data-icon="📂">Categoria</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/CargoControlador?opcao=cancelar" data-icon="🛎️">Cargo</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador?opcao=cancelar" data-icon="🍔">Produto</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=cancelar" data-icon="🛒">Venda</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar" data-icon="🍳">Funcionario</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/ItensVendaControlador?opcao=cancelar" data-icon="🍳">Itens Vendidos</a>
    </nav>
</div>
<c:if test="${empty ocultarBoasVindas}">
    <div class="content">
        <div class="welcome-box">
            <h1>Seja bem-vindo, gerente!</h1>
            <p>Escolha uma opção no menu para gerenciar a Lanchonete.</p>
        </div>
    </div>
</c:if>

    </div>
</body>
</html>
