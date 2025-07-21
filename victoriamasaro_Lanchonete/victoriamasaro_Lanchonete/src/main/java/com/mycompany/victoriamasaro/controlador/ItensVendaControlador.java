package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.ItensVendaDao;
import com.mycompany.victoriamasaro.modelo.dao.ProdutoDao;
import com.mycompany.victoriamasaro.modelo.dao.VendaDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.ItensVenda;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Produto;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Venda;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/ItensVendaControlador")
public class ItensVendaControlador extends HttpServlet {

    private ItensVendaDao itensVendaDao;
    private ProdutoDao produtoDao;
    private VendaDao vendaDao;

    @Override
    public void init() throws ServletException {
        itensVendaDao = new ItensVendaDao();
        produtoDao = new ProdutoDao();
        vendaDao = new VendaDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarItensVenda(req, resp);
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

    private void listarItensVenda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItensVenda> listaItens = itensVendaDao.buscarTodosItensVenda();
        List<Produto> listaProduto = produtoDao.buscarTodosProdutos();
        List<Venda> listaVenda = vendaDao.buscarTodasVendas();

        req.setAttribute("listaItensVenda", listaItens);
        req.setAttribute("listaProduto", listaProduto);
        req.setAttribute("listaVenda", listaVenda);

        if (req.getAttribute("mensagem") == null) {
            limparCampos(req);
        }

        req.setAttribute("opcao", "cadastrar");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroItensVenda.jsp");
        dispatcher.forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItensVenda item = criarItemAPartirDoFormulario(req);
        itensVendaDao.salvar(item);
        req.setAttribute("mensagem", "Item de venda cadastrado com sucesso.");
        listarItensVenda(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarEditar");
        req.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItensVenda item = criarItemAPartirDoFormulario(req);
        item.setCodItemVenda(parseIntSafe(req.getParameter("codigoItemVenda")));
        itensVendaDao.alterar(item);
        req.setAttribute("mensagem", "Alteração realizada com sucesso.");
        listarItensVenda(req, resp);
    }

    private void prepararExclusao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarExcluir");
        req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarExcluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = parseIntSafe(req.getParameter("codigoItemVenda"));
        ItensVenda item = new ItensVenda();
        item.setCodItemVenda(codigo);
        itensVendaDao.excluir(item);
        req.setAttribute("mensagem", "Exclusão realizada com sucesso.");
        listarItensVenda(req, resp);
    }

    private void cancelar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarItensVenda(req, resp);
    }

    // --- MÉTODOS AUXILIARES ---

    private ItensVenda criarItemAPartirDoFormulario(HttpServletRequest req) {
        ItensVenda item = new ItensVenda();
        item.setQuantVenda(parseDoubleSafe(req.getParameter("quantVenda")));

        Produto p = new Produto();
        p.setCodProduto(parseIntSafe(req.getParameter("codProduto")));
        item.setObjProduto(p);

        Venda v = new Venda();
        v.setCodVenda(parseIntSafe(req.getParameter("codVenda")));
        item.setObjVenda(v);

        return item;
    }

    private void carregarCampos(HttpServletRequest req) {
        req.setAttribute("codigoItemVenda", req.getParameter("codigoItemVenda"));
        req.setAttribute("quantVenda", req.getParameter("quantVenda"));
        req.setAttribute("codProduto", req.getParameter("codProduto"));
        req.setAttribute("codVenda", req.getParameter("codVenda"));
    }

    private void limparCampos(HttpServletRequest req) {
        req.setAttribute("codigoItemVenda", "");
        req.setAttribute("quantVenda", "");
        req.setAttribute("codProduto", "");
        req.setAttribute("codVenda", "");
    }

    private void carregarListas(HttpServletRequest req) {
        req.setAttribute("listaProduto", produtoDao.buscarTodosProdutos());
        req.setAttribute("listaVenda", vendaDao.buscarTodasVendas());
        req.setAttribute("listaItensVenda", itensVendaDao.buscarTodosItensVenda());
    }

    private void encaminharParaFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroItensVenda.jsp");
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
