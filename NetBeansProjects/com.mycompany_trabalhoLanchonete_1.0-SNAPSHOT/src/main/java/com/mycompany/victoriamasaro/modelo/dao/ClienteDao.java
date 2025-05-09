/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao extends GenericoDAO<Cliente> {

    public void salvar(Cliente objCidade) {
        String sql = "INSERT INTO cliente (nome, cpf, email, dataNascimento, telefone, endereco, bairro, cidade, cep, uf)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        save(sql, objCidade.getNome());
        save(sql, objCidade.getCpf());
        save(sql, objCidade.getEmail());
        save(sql, objCidade.getDataNascimento());
        save(sql, objCidade.getTelefone());
        save(sql, objCidade.getEndereco());
        save(sql, objCidade.getBairro());
        save(sql, objCidade.getCidade());
        save(sql, objCidade.getCep());
        save(sql, objCidade.getUf());

    }

    public void alterar(Cliente objCidade) {
        String sql = "UPDATE cliente  SET cliente nome=?, cpf=?, email=?, dataNascimento=?, telefone=?, endereco=?, bairro=?, cidade=?, cep=?, uf=? WHERE CODCATEGORIA=?";
        save(sql, objCidade.getNome(), objCidade.getCpf(), objCidade.getEmail(), objCidade.getDataNascimento(), objCidade.getTelefone()
        ,objCidade.getEndereco(), objCidade.getBairro(), objCidade.getCidade(), objCidade.getCep(), objCidade.getUf(), objCidade.getCodCliente());
    }

    public void excluir(Cliente objCidade) {
        String sql = "DELETE FROM Cliente WHERE codCliente=?";
        save(sql, objCidade.getCodCliente());
    }

    //implementa  o RowMapper específico para a entidade Cidade
    private static class CidadeRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente objCidade = new Cliente();
            objCidade.setCodCliente(rs.getInt("CODCLIENTE"));
            objCidade.setNome(rs.getString("NOME"));
        objCidade.setCpf((rs.getString("CPF")));
         objCidade.setEmail((rs.getString("EMAIL")));
         objCidade.setDataNascimento((rs.getString("DATANASCIMENTO")));
         objCidade.setTelefone((rs.getString("TELEFONE")));
         objCidade.setEndereco((rs.getString("ENDERECO")));
         objCidade.setBairro((rs.getString("BAIRRO")));
         objCidade.setCidade((rs.getString("CIDADE")));
         objCidade.setCep((rs.getString("CEP")));
         objCidade.setUf((rs.getString("UF")));

            return objCidade;
        }

    }

    public List<Cliente> buscarTodasCidades() {
        String sql = "SELECT * FROM Cliente";
        return buscarTodos(sql, new CidadeRowMapper());
    }

    public Cliente buscarCidadePorId(int idCidade) {
        String sql = "SELECT * FROM Cliente WHERE codCliente=?";
        return buscarPorId(sql, new CidadeRowMapper(), idCidade);
    }
}
