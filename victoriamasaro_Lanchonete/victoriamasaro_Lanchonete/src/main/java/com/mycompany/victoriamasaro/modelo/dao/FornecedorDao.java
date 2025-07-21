package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDao extends GenericoDAO<Fornecedor> {

    public void salvar(Fornecedor objFornecedor) {
        String sql = "INSERT INTO FORNECEDOR (NOME, CNPJ, TELEFONE, ENDERECO, CUSTO) VALUES (?, ?, ?, ?, ?)";
        save(sql, objFornecedor.getNome(), objFornecedor.getCnpj(), objFornecedor.getTelefone(), objFornecedor.getEndereco(), objFornecedor.getCusto());
    }

    public void alterar(Fornecedor objFornecedor) {
        String sql = "UPDATE FORNECEDOR SET NOME=?, CNPJ=?, TELEFONE=?, ENDERECO=?, CUSTO=? WHERE CODFORNECEDOR=?";
        save(sql, objFornecedor.getNome(), objFornecedor.getCnpj(), objFornecedor.getTelefone(), objFornecedor.getEndereco(), objFornecedor.getCusto(), objFornecedor.getCodFornecedor());
    }

    public void excluir(Fornecedor objFornecedor) {
        String sql = "DELETE FROM FORNECEDOR WHERE CODFORNECEDOR=?";
        save(sql, objFornecedor.getCodFornecedor());
    }

    private static class FornecedorRowMapper implements RowMapper<Fornecedor> {
        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor f = new Fornecedor();
            f.setCodFornecedor(rs.getInt("CODFORNECEDOR"));
            f.setNome(rs.getInt("NOME"));
            f.setCnpj(rs.getInt("CNPJ"));
            f.setTelefone(rs.getInt("TELEFONE"));
            f.setEndereco(rs.getInt("ENDERECO"));
            f.setCusto(rs.getDouble("CUSTO"));
            return f;
        }
    }

    public List<Fornecedor> buscarTodos() {
        String sql = "SELECT * FROM FORNECEDOR";
        return buscarTodos(sql, new FornecedorRowMapper());
    }

    public Fornecedor buscarPorID(int id) {
        String sql = "SELECT * FROM FORNECEDOR WHERE CODFORNECEDOR=?";
        return buscarPorId(sql, new FornecedorRowMapper(), id);
    }
}
