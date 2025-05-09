/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.controlador;


import com.mycompany.victoriamasaro.modelo.dao.entidade.Cargo;
import com.mycompany.victoriamasaro.modelo.dao.CargoDao;
import com.mycompany.victoriamasaro.servico.WebConstante;
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
 * @author 12172700606
 */
@WebServlet(WebConstante.BASE_PATH+"/CidadeControlador")
public class CargoControlador extends HttpServlet{

    private Cargo objCargo;
    private CargoDao objCargodao;
    String nome = "", salarioInicial="", codCargo="";
    
    
    
    @Override
    public void init() throws ServletException {
        objCargodao = new CargoDao();
        objCargo = new Cargo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="cadastrar";
            }
            codCargo = request.getParameter("codCargo");
            nome = request.getParameter("nome");
            salarioInicial = request.getParameter("salarioInicial");
            switch(opcao){
                case "cadastrar":
                    cadastrar(request,response);
                    break;
                    case "editar":
                    editar(request,response);
                    break;
                    case "confirmarEditar":
                    confirmarEditar(request,response);
                    break;
                    case "excluir":
                    excluir(request,response);
                    break;
                    case "confirmarExcluir":
                    confirmarExcluir(request,response);
                    break;
                    case "cancelar":
                    cancelar(request,response);
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
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        objCargo.setNome(nome);
        objCargo.setSalarioInicial(salarioInicial);
        objCargodao.salvar(objCargo);
        encaminharParaPagina(request, response);
       
    }
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setAttribute("codCargo", codCargo);
        request.setAttribute("nome", nome);
        request.setAttribute("salarioInicial", salarioInicial);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        objCargo.setCodCargo(Integer.valueOf(codCargo));
        objCargo.setNome(nome);
        objCargo.setSalarioInicial(salarioInicial);
        objCargodao.alterar(objCargo);
        encaminharParaPagina(request, response);
       
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setAttribute("codCargo", codCargo);
        request.setAttribute("nome", nome);
        request.setAttribute("salarioInicial", salarioInicial);
        request.setAttribute("mensagem", "Exclua os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        objCargo.setCodCargo(Integer.valueOf(codCargo));
        objCargo.setNome(nome);
        objCargo.setSalarioInicial(salarioInicial);
        objCargodao.excluir(objCargo);
        encaminharParaPagina(request, response);
    }
    
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Cargo> listaCidade = objCargodao.buscarTodasCidades();
        request.setAttribute("listaCidade",listaCidade);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroCargo.jsp");
        encaminhar.forward(request, response);
        
    }
    
    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codCargo", "0");
        request.setAttribute("nome", "");
        request.setAttribute("salarioInicial", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
        
    }
}
