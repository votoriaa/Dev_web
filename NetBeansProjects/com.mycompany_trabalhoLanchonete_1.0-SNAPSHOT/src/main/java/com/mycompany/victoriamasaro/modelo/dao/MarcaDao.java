/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Marca;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author victo
 */
public class MarcaDao extends GenericoDAO<Marca> {




    public void salvar(Marca objCidade){
        String sql = "INSERT INTO Marca(NOME,OBSERVACOES) values (?,?)";
        save(sql,objCidade.getNome(), objCidade.getObservacoes());
    }
    
    public void alterar(Marca objCidade){
        String sql = "UPDATE Marca SET NOME=?, OBSERVACOES=? WHERE CODMARCA=?";
        save(sql, objCidade.getNome(), objCidade.getObservacoes(), objCidade.getCodMarca());
    }
    
    public void excluir(Marca objCidade){
        String sql = "DELETE FROM Marca WHERE CODMARCA=?";
        save(sql, objCidade.getCodMarca());
    }
    
    //implementa  o RowMapper específico para a entidade Cidade
    
    private static class CidadeRowMapper implements RowMapper<Marca>{

        @Override
        public Marca mapRow(ResultSet rs) throws SQLException {
            Marca objCidade = new Marca();
            objCidade.setCodMarca(rs.getInt("CODMARCA"));
            objCidade.setNome(rs.getString("NOME"));
            objCidade.setObservacoes(rs.getString("OBSERVACOES"));
            
            return objCidade;
        }
        
    }
    
    public List<Marca> buscarTodasCidades(){
      String sql = "SELECT * FROM Marca";
      return buscarTodos(sql, new CidadeRowMapper());
    }
    
    public Marca buscarCidadePorId(int idCidade){
        String sql = "SELECT * FROM Marca WHERE CODMARCA=?";
        return buscarPorId(sql, new CidadeRowMapper(), idCidade);
    }
}

    



