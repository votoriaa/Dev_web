package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.FornecedorDao;
import com.mycompany.victoriamasaro.modelo.dao.ItensVendaDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Fornecedor;
import com.mycompany.victoriamasaro.modelo.dao.entidade.ItensVenda;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/FornecedorControlador")
public class FornecedorControlador extends HttpServlet {

    private FornecedorDao fornecedorDao;
    private ItensVendaDao itensVendaDao;

    @Override
    public void init() throws ServletException {
        fornecedorDao = new FornecedorDao();
        itensVendaDao = new ItensVendaDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarFornecedores(req, resp);
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

    private void listarFornecedores(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Fornecedor> listaFornecedor = fornecedorDao.buscarTodosFornecedores();
        List<ItensVenda> listaItensVenda = itensVendaDao.buscarTodosItensVenda();

        req.setAttribute("listaFornecedor", listaFornecedor);
        req.setAttribute("listaItensVenda", listaItensVenda);

        if (req.getAttribute("mensagem") == null) {
            limparCampos(req);
        }

        req.setAttribute("opcao", "cadastrar");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroFornecedor.jsp");
        dispatcher.forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fornecedor f = criarFornecedorDoFormulario(req);
        fornecedorDao.salvar(f);
        req.setAttribute("mensagem", "Cadastro realizado com sucesso.");
        listarFornecedores(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarEditar");
        req.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fornecedor f = criarFornecedorDoFormulario(req);
        f.setCodFornecedor(parseIntSafe(req.getParameter("codigoFornecedor")));
        fornecedorDao.alterar(f);
        req.setAttribute("mensagem", "Alteração realizada com sucesso.");
        listarFornecedores(req, resp);
    }

    private void prepararExclusao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarExcluir");
        req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarExcluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = parseIntSafe(req.getParameter("codigoFornecedor"));
        Fornecedor f = new Fornecedor();
        f.setCodFornecedor(codigo);
        fornecedorDao.excluir(f);
        req.setAttribute("mensagem", "Exclusão realizada com sucesso.");
        listarFornecedores(req, resp);
    }

    private void cancelar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarFornecedores(req, resp);
    }

    // Auxiliares

    private Fornecedor criarFornecedorDoFormulario(HttpServletRequest req) {
        Fornecedor f = new Fornecedor();
        f.setCusto(parseDoubleSafe(req.getParameter("custoFornecedor")));

        ItensVenda itemVenda = new ItensVenda();
        itemVenda.setCodItemVenda(parseIntSafe(req.getParameter("codItemVenda")));
        f.setObjItemVenda(itemVenda);

        return f;
    }

    private void carregarCampos(HttpServletRequest req) {
        req.setAttribute("codigoFornecedor", req.getParameter("codigoFornecedor"));
        req.setAttribute("custoFornecedor", req.getParameter("custoFornecedor"));
        req.setAttribute("codItemVenda", req.getParameter("codItemVenda"));
    }

    private void limparCampos(HttpServletRequest req) {
        req.setAttribute("codigoFornecedor", "");
        req.setAttribute("custoFornecedor", "");
        req.setAttribute("codItemVenda", "");
    }

    private void carregarListas(HttpServletRequest req) {
        req.setAttribute("listaItensVenda", itensVendaDao.buscarTodosItensVenda());
        req.setAttribute("listaFornecedor", fornecedorDao.buscarTodosFornecedores());
    }

    private void encaminharParaFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroFornecedor.jsp");
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
