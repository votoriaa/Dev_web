/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudo3anog.controlador;

import com.mycompany.estudo3anog.modelo.dao.CidadeDAO;
import com.mycompany.estudo3anog.modelo.entidade.Cidade;
import com.mycompany.estudo3anog.servico.WebConstante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstante.BASE_PATH + "/CidadeControlador")
public class CidadeControlador extends HttpServlet {

    private Cidade objCidade;
    private CidadeDAO objCidadeDao;
    String nomeCidade = "", ufCidade = "", codigoCidade = "", opcao;

    @Override
    public void init() throws ServletException {
        objCidadeDao = new CidadeDAO();
        objCidade = new Cidade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codigoCidade = request.getParameter("codigoCidade");
            nomeCidade = request.getParameter("nomeCidade");
            ufCidade = request.getParameter("ufCidade");
            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (NumberFormatException ex) { // lida com erros de conversão de tipo numérico
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.getWriter().println("Erro: " + ex.getMessage());

        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objCidade.setNomeCidade(nomeCidade);
        objCidade.setUfCidade(ufCidade);
        objCidadeDao.salvar(objCidade);
        encaminharParaPagina(request, response);

    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCidade", codigoCidade);
        request.setAttribute("nomeCidade", nomeCidade);
        request.setAttribute("ufCidade", ufCidade);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);

    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCidade", codigoCidade);
        request.setAttribute("nomeCidade", nomeCidade);
        request.setAttribute("ufCidade", ufCidade);
        request.setAttribute("mensagem", "Clique no botão salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);

    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objCidade.setCodigoCidade(Integer.valueOf(codigoCidade));
        objCidade.setNomeCidade(nomeCidade);
        objCidade.setUfCidade(ufCidade);
        objCidadeDao.alterar(objCidade);
        encaminharParaPagina(request, response);

    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objCidade.setCodigoCidade(Integer.valueOf(codigoCidade));
        objCidadeDao.excluir(objCidade);
        encaminharParaPagina(request, response);

    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cidade> listaCidade = objCidadeDao.buscarTodasCidades();
        request.setAttribute("listaCidade", listaCidade);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroCidade.jsp");
        encaminhar.forward(request, response);

    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codigoCidade", "0");
        request.setAttribute("nomeCidade", "");
        request.setAttribute("ufCidade", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);

    }

}
