package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.CategoriaDao;
import com.mycompany.victoriamasaro.modelo.dao.MarcaDao;
import com.mycompany.victoriamasaro.modelo.dao.ProdutoDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Categoria;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Marca;
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

    private ProdutoDao produtoDao;
    private MarcaDao marcaDao;
    private CategoriaDao categoriaDao;

    @Override
    public void init() throws ServletException {
        produtoDao = new ProdutoDao();
        marcaDao = new MarcaDao();
        categoriaDao = new CategoriaDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarProdutos(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        if (opcao == null) opcao = "cadastrar";

        switch (opcao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "confirmarEditar":
                confirmarEditar(req, resp);
                break;
            case "excluir":
                prepararExclusao(req, resp);
                break;
            case "confirmarExcluir":
                confirmarExcluir(req, resp);
                break;
            case "cancelar":
                cancelar(req, resp);
                break;
            default:
                resp.getWriter().println("Opção inválida: " + opcao);
        }
    }

    private void listarProdutos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> listaProduto = produtoDao.buscarTodosProdutos();
        List<Marca> listaMarca = marcaDao.buscarTodasMarcas();
        List<Categoria> listaCategoria = categoriaDao.buscarTodasCategorias();

        req.setAttribute("listaProduto", listaProduto);
        req.setAttribute("listaMarca", listaMarca);
        req.setAttribute("listaCategoria", listaCategoria);

        if (req.getAttribute("mensagem") == null) {
            limparCampos(req);
        }

        req.setAttribute("opcao", "cadastrar");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroProduto.jsp");
        dispatcher.forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produto p = criarProdutoAPartirDoFormulario(req);
        produtoDao.salvar(p);
        req.setAttribute("mensagem", "Cadastro realizado com sucesso.");
        listarProdutos(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarEditar");
        req.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produto p = criarProdutoAPartirDoFormulario(req);
        p.setCodProduto(parseIntSafe(req.getParameter("codigoProduto")));
        produtoDao.alterar(p);
        req.setAttribute("mensagem", "Alteração realizada com sucesso.");
        listarProdutos(req, resp);
    }

    private void prepararExclusao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarExcluir");
        req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarExcluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = parseIntSafe(req.getParameter("codigoProduto"));
        Produto p = new Produto();
        p.setCodProduto(codigo);
        produtoDao.excluir(p);
        req.setAttribute("mensagem", "Exclusão realizada com sucesso.");
        listarProdutos(req, resp);
    }

    private void cancelar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarProdutos(req, resp);
    }

    // --- MÉTODOS AUXILIARES ---

    private Produto criarProdutoAPartirDoFormulario(HttpServletRequest req) {
        Produto p = new Produto();
        p.setNome(req.getParameter("nomeProduto"));
        p.setIngredientes(req.getParameter("ingredientesProduto"));
        p.setQuantidade(parseDoubleSafe(req.getParameter("quantidadeProduto")));
        p.setPrecoCusto(parseDoubleSafe(req.getParameter("precoCustoProduto")));
        p.setPrecoVenda(parseDoubleSafe(req.getParameter("precoVendaProduto")));

        Marca m = new Marca();
        m.setCodMarca(parseIntSafe(req.getParameter("codMarca")));
        p.setObjMarca(m);

        Categoria c = new Categoria();
        c.setCodCategoria(parseIntSafe(req.getParameter("codCategoria")));
        p.setObjCategoria(c);

        return p;
    }

    private void carregarCampos(HttpServletRequest req) {
        req.setAttribute("codigoProduto", req.getParameter("codigoProduto"));
        req.setAttribute("nomeProduto", req.getParameter("nomeProduto"));
        req.setAttribute("ingredientesProduto", req.getParameter("ingredientesProduto"));
        req.setAttribute("quantidadeProduto", req.getParameter("quantidadeProduto"));
        req.setAttribute("precoCustoProduto", req.getParameter("precoCustoProduto"));
        req.setAttribute("precoVendaProduto", req.getParameter("precoVendaProduto"));
        req.setAttribute("codMarca", req.getParameter("codMarca"));
        req.setAttribute("codCategoria", req.getParameter("codCategoria"));
    }

    private void limparCampos(HttpServletRequest req) {
        req.setAttribute("codigoProduto", "");
        req.setAttribute("nomeProduto", "");
        req.setAttribute("ingredientesProduto", "");
        req.setAttribute("quantidadeProduto", "");
        req.setAttribute("precoCustoProduto", "");
        req.setAttribute("precoVendaProduto", "");
        req.setAttribute("codMarca", "");
        req.setAttribute("codCategoria", "");
    }

    private void carregarListas(HttpServletRequest req) {
        req.setAttribute("listaMarca", marcaDao.buscarTodasMarcas());
        req.setAttribute("listaCategoria", categoriaDao.buscarTodasCategorias());
        req.setAttribute("listaProduto", produtoDao.buscarTodosProdutos());
    }

    private void encaminharParaFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroProduto.jsp");
        dispatcher.forward(req, resp);
    }

    private Integer parseIntSafe(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDoubleSafe(String valor) {
        try {
            if (valor == null || valor.trim().isEmpty()) return 0.0;
            valor = valor.replace(",", ".");
            return Double.parseDouble(valor);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
