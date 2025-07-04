package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.ClienteDao;
import com.mycompany.victoriamasaro.modelo.dao.EntregasDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Cliente;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Entregas;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/EntregasControlador")
public class EntregasControlador extends HttpServlet {

    private EntregasDao entregasDao;
    private ClienteDao clienteDao;

    @Override
    public void init() throws ServletException {
        entregasDao = new EntregasDao();
        clienteDao = new ClienteDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarEntregas(req, resp);
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

    private void listarEntregas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Entregas> listaEntregas = entregasDao.buscarTodasEntregas();
        List<Cliente> listaClientes = clienteDao.buscarTodosClientes();

        req.setAttribute("listaEntregas", listaEntregas);
        req.setAttribute("listaClientes", listaClientes);

        if (req.getAttribute("mensagem") == null) {
            limparCampos(req);
        }

        req.setAttribute("opcao", "cadastrar");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroEntregas.jsp");
        dispatcher.forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Entregas e = criarEntregaDoFormulario(req);
        entregasDao.salvar(e);
        req.setAttribute("mensagem", "Cadastro realizado com sucesso.");
        listarEntregas(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarEditar");
        req.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Entregas e = criarEntregaDoFormulario(req);
        e.setCodEntrega(parseIntSafe(req.getParameter("codEntrega")));
        entregasDao.alterar(e);
        req.setAttribute("mensagem", "Alteração realizada com sucesso.");
        listarEntregas(req, resp);
    }

    private void prepararExclusao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarCampos(req);
        req.setAttribute("opcao", "confirmarExcluir");
        req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar.");
        carregarListas(req);
        encaminharParaFormulario(req, resp);
    }

    private void confirmarExcluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = parseIntSafe(req.getParameter("codEntrega"));
        Entregas e = new Entregas();
        e.setCodEntrega(codigo);
        entregasDao.excluir(e);
        req.setAttribute("mensagem", "Exclusão realizada com sucesso.");
        listarEntregas(req, resp);
    }

    private void cancelar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listarEntregas(req, resp);
    }

    // Auxiliares

    private Entregas criarEntregaDoFormulario(HttpServletRequest req) {
        Entregas e = new Entregas();
        e.setEndereco(req.getParameter("endereco"));
        e.setNumeroCasa(req.getParameter("numeroCasa"));

        Cliente cliente = new Cliente();
        cliente.setCodCliente(parseIntSafe(req.getParameter("codCliente")));
        e.setObjCliente(cliente);

        return e;
    }

    private void carregarCampos(HttpServletRequest req) {
        req.setAttribute("codEntrega", req.getParameter("codEntrega"));
        req.setAttribute("endereco", req.getParameter("endereco"));
        req.setAttribute("numeroCasa", req.getParameter("numeroCasa"));
        req.setAttribute("codCliente", req.getParameter("codCliente"));
    }

    private void limparCampos(HttpServletRequest req) {
        req.setAttribute("codEntrega", "");
        req.setAttribute("endereco", "");
        req.setAttribute("numeroCasa", "");
        req.setAttribute("codCliente", "");
    }

    private void carregarListas(HttpServletRequest req) {
        req.setAttribute("listaClientes", clienteDao.buscarTodosClientes());
        req.setAttribute("listaEntregas", entregasDao.buscarTodasEntregas());
    }

    private void encaminharParaFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroEntregas.jsp");
        dispatcher.forward(req, resp);
    }

    private Integer parseIntSafe(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception e) {
            return 0;
        }
    }
}
