package com.mycompany.victoriamasaro.controlador;

import com.mycompany.victoriamasaro.modelo.dao.FornecedorDao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Fornecedor;
import com.mycompany.victoriamasaro.servico.WebConstante;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/FornecedorControlador")
public class FornecedorControlador extends HttpServlet {

    private FornecedorDao dao;
    private Fornecedor fornecedor;

    @Override
    public void init() {
        dao = new FornecedorDao();
        fornecedor = new Fornecedor();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");
        if (opcao == null) opcao = "cadastrar";

        String codFornecedor = req.getParameter("codFornecedor");
        String nome = req.getParameter("nome");
        String cnpj = req.getParameter("cnpj");
        String telefone = req.getParameter("telefone");
        String endereco = req.getParameter("endereco");
        String custo = req.getParameter("custo");

        switch (opcao) {
            case "cadastrar":
                fornecedor.setNome(Integer.valueOf(nome));
                fornecedor.setCnpj(Integer.valueOf(cnpj));
                fornecedor.setTelefone(Integer.valueOf(telefone));
                fornecedor.setEndereco(Integer.valueOf(endereco));
                fornecedor.setCusto(Double.parseDouble(custo));
                dao.salvar(fornecedor);
                break;
            case "editar":
                req.setAttribute("codFornecedor", codFornecedor);
                req.setAttribute("nome", nome);
                req.setAttribute("cnpj", cnpj);
                req.setAttribute("telefone", telefone);
                req.setAttribute("endereco", endereco);
                req.setAttribute("custo", custo);
                req.setAttribute("opcao", "confirmarEditar");
                req.setAttribute("mensagem", "Edite os dados e clique em salvar");
                break;
            case "confirmarEditar":
                fornecedor.setCodFornecedor(Integer.valueOf(codFornecedor));
                fornecedor.setNome(Integer.valueOf(nome));
                fornecedor.setCnpj(Integer.valueOf(cnpj));
                fornecedor.setTelefone(Integer.valueOf(telefone));
                fornecedor.setEndereco(Integer.valueOf(endereco));
                fornecedor.setCusto(Double.parseDouble(custo));
                dao.alterar(fornecedor);
                break;
            case "excluir":
                req.setAttribute("codFornecedor", codFornecedor);
                req.setAttribute("nome", nome);
                req.setAttribute("cnpj", cnpj);
                req.setAttribute("telefone", telefone);
                req.setAttribute("endereco", endereco);
                req.setAttribute("custo", custo);
                req.setAttribute("opcao", "confirmarExcluir");
                req.setAttribute("mensagem", "Confirme a exclusão e clique em salvar");
                break;
            case "confirmarExcluir":
                fornecedor.setCodFornecedor(Integer.valueOf(codFornecedor));
                dao.excluir(fornecedor);
                break;
            case "cancelar":
                req.setAttribute("codFornecedor", "0");
                req.setAttribute("nome", "");
                req.setAttribute("cnpj", "");
                req.setAttribute("telefone", "");
                req.setAttribute("endereco", "");
                req.setAttribute("custo", "");
                req.setAttribute("opcao", "cadastrar");
                break;
        }

        req.setAttribute("listaFornecedor", dao.buscarTodos());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/CadastroFornecedor.jsp");
        dispatcher.forward(req, resp);
    }
}
