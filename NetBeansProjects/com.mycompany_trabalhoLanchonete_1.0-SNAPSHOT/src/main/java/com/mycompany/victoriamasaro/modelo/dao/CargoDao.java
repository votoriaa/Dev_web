/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CargoDao extends GenericoDAO<Cargo>{


    public void salvar(Cargo objCidade){
        String sql = "INSERT INTO CARGO(NOME,SALARIOINICIAL) values (?,?)";
        save(sql,objCidade.getNome(), objCidade.getSalarioInicial());
    }
    
    public void alterar(Cargo objCidade){
        String sql = "UPDATE CARGO SET NOME=?, SALARIOINICIAL=? WHERE CODCARGO=?";
        save(sql, objCidade.getNome(), objCidade.getSalarioInicial(), objCidade.getCodCargo());
    }
    
    public void excluir(Cargo objCidade){
        String sql = "DELETE FROM CARGO WHERE CODCARGO=?";
        save(sql, objCidade.getCodCargo());
    }
    
    //implementa  o RowMapper específico para a entidade Cidade
    
    private static class CidadeRowMapper implements RowMapper<Cargo>{

        @Override
        public Cargo mapRow(ResultSet rs) throws SQLException {
            Cargo objCidade = new Cargo();
            objCidade.setCodCargo(rs.getInt("CODCARGO"));
            objCidade.setNome(rs.getString("NOME"));
            objCidade.setSalarioInicial(rs.getDouble("SALARIOINICIAL"));
            
            return objCidade;
        }
        
    }
    
    public List<Cargo> buscarTodasCidades(){
      String sql = "SELECT * FROM Cargo";
      return buscarTodos(sql, new CidadeRowMapper());
    }
    
    public Cargo buscarCidadePorId(int idCidade){
        String sql = "SELECT * FROM CIDADE WHERE CODCARGO=?";
        return buscarPorId(sql, new CidadeRowMapper(), idCidade);
    }
}

    

