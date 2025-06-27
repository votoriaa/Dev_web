/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudo3anog.modelo.dao;

import com.mycompany.estudo3anog.modelo.entidade.Cidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tulio
 */
public class CidadeDAO extends GenericoDAO<Cidade> {

    public void salvar(Cidade objCidade) {
        String sql = "INSERT INTO CIDADE(NOME,UF) VALUES(?,?)";
        save(sql, objCidade.getNomeCidade(), objCidade.getUfCidade());

    }

    public void alterar(Cidade objCidade) {
        String sql = "UPDATE CIDADE SET NOME=?,UF=? WHERE CODIGO=?";
        save(sql, objCidade.getNomeCidade(), objCidade.getUfCidade(), objCidade.getCodigoCidade());
    }

    public void excluir(Cidade objCidade) {
        String sql = "DELETE FROM CIDADE WHERE CODIGO=?";
        save(sql, objCidade.getCodigoCidade());
    }

    // implementa o RowMapper específico para a entidade Cidade
    private static class CidadeRowMapper implements RowMapper<Cidade> {

        @Override
        public Cidade mapRow(ResultSet rs) throws SQLException {
            Cidade objCidade = new Cidade();
            objCidade.setCodigoCidade(rs.getInt("CODIGO"));
            objCidade.setNomeCidade(rs.getString("NOME"));
            objCidade.setUfCidade(rs.getString("UF"));
            return objCidade;

        }

    }
    
    public List<Cidade> buscarTodasCidades(){
        String sql = "SELECT * FROM CIDADE";
        return buscarTodos(sql, new CidadeRowMapper());
    }
    
    public Cidade buscarCidadePorId(int idCidade){
        String sql = "SELECT * FROM CIDADE WHERE CODIGO=?";
        return buscarPorId(sql, new CidadeRowMapper(), idCidade);
    }
}
