/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriacaixeta.controlador;

import com.mycompany.victoriacaixeta.modelo.dao.CidadeDAO;
import com.mycompany.victoriacaixeta.modelo.dao.entidade.Cidade;
import com.mycompany.victoriacaixeta.servico.WebConstante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author 12172700606
 */
@WebServlet(WebConstante.BASE_PATH+"/CidadeControlador")
public class CidadeControlador extends HttpServlet{

    private Cidade objCidade;
    private CidadeDAO objCidadeDao;
    String nomeCidade = "", ufCidade="", codigoCidade="";
    
    @Override
    public void init() throws ServletException {
        objCidadeDao = new CidadeDAO();
        objCidade = new Cidade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="cadastrar";
            }
            codigoCidade = request.getParameter("codigoCidade");
            nomeCidade = request.getParameter("nomeCidade");
            ufCidade = request.getParameter("ufCidade");
            switch(opcao){
                case "cadastrar":
                    cadastrar(request,response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: "+opcao);
            }
        }catch(NumberFormatException ex){ //lida com erros de conversão de tipo numérico
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos"+ex.getMessage());
        } catch(IllegalArgumentException ex){
            response.getWriter().println("Erro: "+ex.getMessage());
        }
        
        
    }
    
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        objCidade.setNomeCidade(nomeCidade);
        objCidade.setUfCidade(ufCidade);
        objCidadeDao.salvar(objCidade);
        
        
    }
}
