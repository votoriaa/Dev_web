package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.MarcaDao;
import com.mycompany.victoriamasaro.modelo.dao.CategoriaDao;
import com.mycompany.victoriamasaro.modelo.dao.ProdutoDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Marca;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Categoria;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Produto;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/ProdutoControlador")
public class ProdutoControlador extends HttpServlet {

    private ProdutoDao objProdutoDao;
    private MarcaDao objMarcaDao;
    private CategoriaDao objCategoriaDao;

    @Override
    public void init() throws ServletException {
        objProdutoDao = new ProdutoDao();
        objMarcaDao = new MarcaDao();
        objCategoriaDao = new CategoriaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Só para exibir a página com lista e formulários em branco
        encaminharParaPagina(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao == null || opcao.isEmpty()) {
            opcao = "cadastrar";
        }

        // Ler parâmetros do formulário
        String codigoProduto = request.getParameter("codigoProduto");
        String nomeProduto = request.getParameter("nomeProduto");
        String ingredientesProduto = request.getParameter("ingredientesProduto");
        String quantidadeProduto = request.getParameter("quantidadeProduto");
        String precoCustoProduto = request.getParameter("precoCustoProduto");
        String precoVendaProduto = request.getParameter("precoVendaProduto");
        String codMarca = request.getParameter("codMarca");
        String codCategoria = request.getParameter("codCategoria");

        try {
            switch (opcao) {
                case "cadastrar":
                    cadastrar(nomeProduto, ingredientesProduto, quantidadeProduto, precoCustoProduto, precoVendaProduto, codMarca, codCategoria, request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(codigoProduto, nomeProduto, ingredientesProduto, quantidadeProduto, precoCustoProduto, precoVendaProduto, codMarca, codCategoria, request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(codigoProduto, request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                default:
                    response.getWriter().println("Opção inválida: " + opcao);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(String nomeProduto, String ingredientesProduto, String quantidadeProduto,
            String precoCustoProduto, String precoVendaProduto, String codMarca, String codCategoria,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Produto objProduto = new Produto();
        objProduto.setNome(nomeProduto);
        objProduto.setIngredientes(ingredientesProduto);
        objProduto.setQuantidade(Double.parseDouble(quantidadeProduto));
        objProduto.setPrecoCusto(Double.parseDouble(precoCustoProduto));
        objProduto.setPrecoVenda(Double.parseDouble(precoVendaProduto));

        Marca marca = new Marca();
        marca.setCodMarca(Integer.parseInt(codMarca));
        objProduto.setObjMarca(marca);

        Categoria categoria = new Categoria();
        categoria.setCodCategoria(Integer.parseInt(codCategoria));
        objProduto.setObjCategoria(categoria);

        objProdutoDao.salvar(objProduto);
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(String codigoProduto, String nomeProduto, String ingredientesProduto, String quantidadeProduto,
            String precoCustoProduto, String precoVendaProduto, String codMarca, String codCategoria,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Produto objProduto = new Produto();
        objProduto.setCodProduto(Integer.parseInt(codigoProduto));
        objProduto.setNome(nomeProduto);
        objProduto.setIngredientes(ingredientesProduto);
        objProduto.setQuantidade(Double.parseDouble(quantidadeProduto));
        objProduto.setPrecoCusto(Double.parseDouble(precoCustoProduto));
        objProduto.setPrecoVenda(Double.parseDouble(precoVendaProduto));

        Marca marca = new Marca();
        marca.setCodMarca(Integer.parseInt(codMarca));
        objProduto.setObjMarca(marca);

        Categoria categoria = new Categoria();
        categoria.setCodCategoria(Integer.parseInt(codCategoria));
        objProduto.setObjCategoria(categoria);

        objProdutoDao.alterar(objProduto);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(String codigoProduto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto objProduto = new Produto();
        objProduto.setCodProduto(Integer.parseInt(codigoProduto));
        objProdutoDao.excluir(objProduto);
        encaminharParaPagina(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoProduto", "");
        request.setAttribute("nomeProduto", "");
        request.setAttribute("ingredientesProduto", "");
        request.setAttribute("quantidadeProduto", "");
        request.setAttribute("precoCustoProduto", "");
        request.setAttribute("precoVendaProduto", "");
        request.setAttribute("codMarca", "");
        request.setAttribute("codCategoria", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Marca> listaMarca = objMarcaDao.buscarTodasMarcas();
        List<Categoria> listaCategoria = objCategoriaDao.buscarTodasCategorias();
        List<Produto> listaProduto = objProdutoDao.buscarTodosProdutos();

        request.setAttribute("listaMarca", listaMarca);
        request.setAttribute("listaCategoria", listaCategoria);
        request.setAttribute("listaProduto", listaProduto);

        // Para manter valores no formulário após ação
        if (request.getAttribute("opcao") == null) {
            request.setAttribute("opcao", "cadastrar");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroProduto.jsp");
        dispatcher.forward(request, response);
    }
}
