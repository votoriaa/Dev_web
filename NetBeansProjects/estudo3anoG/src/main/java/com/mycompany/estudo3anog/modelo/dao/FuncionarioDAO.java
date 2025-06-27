/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudo3anog.modelo.dao;


import com.mycompany.estudo3anog.modelo.entidade.Funcionario;
import com.mycompany.estudo3anog.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tulio
 */
public class FuncionarioDAO extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario objFuncionario) {
        String sql = "INSERT INTO FUNCIONARIO(NOME,SALARIO,NASCIMENTO,CIDADE) VALUES(?,?,?,?)";
        save(sql, objFuncionario.getNomeFuncionario(),
                  objFuncionario.getSalarioFuncionario(),
                  objFuncionario.getNascimentoFuncionario(),
                  objFuncionario.getObjCidade().getCodigoCidade());//RELACIONAMENTO COM CIDADE

    }

    public void alterar(Funcionario objFuncionario) {
        String sql = "UPDATE FUNCIONARIO SET NOME=?,SALARIO=?, NASCIMENTO=?,CIDADE=? WHERE CODIGO=?";
        save(sql, objFuncionario.getNomeFuncionario(),
                  objFuncionario.getSalarioFuncionario(),
                  objFuncionario.getNascimentoFuncionario(),
                  objFuncionario.getObjCidade().getCodigoCidade(),
                  objFuncionario.getCodigoFuncionario());
    }

    public void excluir(Funcionario objFuncionario) {
        String sql = "DELETE FROM FUNCIONARIO WHERE CODIGO=?";
        save(sql, objFuncionario.getCodigoFuncionario());
    }

    // implementa o RowMapper específico para a entidade Cidade
    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        ConverteData converte = new ConverteData();
        CidadeDAO objCidadeDao = new CidadeDAO();
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario objFuncionario = new Funcionario();
            objFuncionario.setCodigoFuncionario(rs.getInt("CODIGO"));
            objFuncionario.setNomeFuncionario(rs.getString("NOME"));
            objFuncionario.setSalarioFuncionario(rs.getDouble("SALARIO"));
            objFuncionario.setNascimentoFuncionario(converte.converteCalendario(rs.getDate("NASCIMENTO")));
            objFuncionario.setObjCidade(objCidadeDao.buscarCidadePorId(rs.getInt("CIDADE")));
            return objFuncionario;

        }

    }
    
    public List<Funcionario> buscarTodosFuncionarios(){
        String sql = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(sql, new FuncionarioRowMapper());
    }
    
    public Funcionario buscarFuncionarioPorId(int idFuncionario){
        String sql = "SELECT * FROM FUNCIONARIO WHERE CODIGO=?";
        return buscarPorId(sql, new FuncionarioRowMapper(), idFuncionario);
    }
}
