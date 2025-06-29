package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.ClienteDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Cliente;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private Cliente objCliente;
    private ClienteDao objClienteDao;
    
    String codCliente = "", nome = "", cpf = "", email = "", dataNascimento = "", telefone = "", endereco = "", bairro = "", cidade = "", uf = "", opcao;

    @Override
    public void init() throws ServletException {
        objClienteDao = new ClienteDao();
        objCliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            opcao = req.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            codCliente = req.getParameter("codCliente");
            nome = req.getParameter("nome");
            cpf = req.getParameter("cpf");
            email = req.getParameter("email");
            dataNascimento = req.getParameter("dataNascimento");
            telefone = req.getParameter("telefone");
            endereco = req.getParameter("endereco");
            bairro = req.getParameter("bairro");
            cidade = req.getParameter("cidade");
            uf = req.getParameter("uf");

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
                    excluir(req, resp);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(req, resp);
                    break;
                case "cancelar":
                    cancelar(req, resp);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (IllegalArgumentException ex) {
            resp.getWriter().println("Erro: " + ex.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        objCliente.setNome(nome);
        objCliente.setCpf(cpf);
        objCliente.setEmail(email);
        objCliente.setDataNascimento(dataNascimento);
        objCliente.setTelefone(telefone);
        objCliente.setEndereco(endereco);
        objCliente.setBairro(bairro);
        objCliente.setCidade(cidade);
        objCliente.setUf(uf);
        objClienteDao.salvar(objCliente);
        encaminharParaPagina(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("codCliente", codCliente);
        req.setAttribute("nome", nome);
        req.setAttribute("cpf", cpf);
        req.setAttribute("email", email);
        req.setAttribute("dataNascimento", dataNascimento);
        req.setAttribute("telefone", telefone);
        req.setAttribute("endereco", endereco);
        req.setAttribute("bairro", bairro);
        req.setAttribute("cidade", cidade);
        req.setAttribute("uf", uf);
        req.setAttribute("mensagem", "Edite os dados e clique em salvar");
        req.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(req, resp);
    }

    private void confirmarEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        objCliente.setCodCliente(Integer.valueOf(codCliente));
        objCliente.setNome(nome);
        objCliente.setCpf(cpf);
        objCliente.setEmail(email);
        objCliente.setDataNascimento(dataNascimento);
        objCliente.setTelefone(telefone);
        objCliente.setEndereco(endereco);
        objCliente.setBairro(bairro);
        objCliente.setCidade(cidade);
        objCliente.setUf(uf);
        objClienteDao.alterar(objCliente);
        encaminharParaPagina(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("codCliente", codCliente);
        req.setAttribute("nome", nome);
        req.setAttribute("cpf", cpf);
        req.setAttribute("email", email);
        req.setAttribute("dataNascimento", dataNascimento);
        req.setAttribute("telefone", telefone);
        req.setAttribute("endereco", endereco);
        req.setAttribute("bairro", bairro);
        req.setAttribute("cidade", cidade);
        req.setAttribute("uf", uf);
        req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar");
        req.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(req, resp);
    }

    private void confirmarExcluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        objCliente.setCodCliente(Integer.valueOf(codCliente));
        objClienteDao.excluir(objCliente);
        encaminharParaPagina(req, resp);
    }

    private void encaminharParaPagina(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> listaCliente = objClienteDao.buscarTodosClientes();
        req.setAttribute("listaCliente", listaCliente);
        RequestDispatcher encaminhar = req.getRequestDispatcher("/CadastroCliente.jsp");
        encaminhar.forward(req, resp);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codCliente", "0");
        request.setAttribute("nome", "");
        request.setAttribute("cpf", "");
        request.setAttribute("email", "");
        request.setAttribute("dataNascimento", "");
        request.setAttribute("telefone", "");
        request.setAttribute("endereco", "");
        request.setAttribute("bairro", "");
        request.setAttribute("cidade", "");
        request.setAttribute("uf", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}
