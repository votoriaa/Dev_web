package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Fornecedor;
import com.mycompany.victoriamasaro.modelo.dao.entidade.ItensVenda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDao extends GenericoDAO<Fornecedor> {

    public void salvar(Fornecedor objFornecedor) {
        String sql = "INSERT INTO FORNECEDOR(custo, itensVenda_codItemVenda) VALUES (?, ?)";
        save(sql,
            objFornecedor.getCusto(),
            objFornecedor.getObjItemVenda().getCodItemVenda()
        );
    }

    public void alterar(Fornecedor objFornecedor) {
        String sql = "UPDATE FORNECEDOR SET custo=?, itensVenda_codItemVenda=? WHERE codFornecedor=?";
        save(sql,
            objFornecedor.getCusto(),
            objFornecedor.getObjItemVenda().getCodItemVenda(),
            objFornecedor.getCodFornecedor()
        );
    }

    public void excluir(Fornecedor objFornecedor) {
        String sql = "DELETE FROM FORNECEDOR WHERE codFornecedor=?";
        save(sql, objFornecedor.getCodFornecedor());
    }

    private static class FornecedorRowMapper implements RowMapper<Fornecedor> {
        ItensVendaDao itensVendaDao = new ItensVendaDao();

        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor f = new Fornecedor();
            f.setCodFornecedor(rs.getInt("codFornecedor"));
            f.setCusto(rs.getDouble("custo"));
            ItensVenda itemVenda = itensVendaDao.buscarItemVendaPorId(rs.getInt("itensVenda_codItemVenda"));
            f.setObjItemVenda(itemVenda);
            return f;
        }
    }

    public List<Fornecedor> buscarTodosFornecedores() {
        String sql = "SELECT * FROM FORNECEDOR";
        return buscarTodos(sql, new FornecedorRowMapper());
    }

    public Fornecedor buscarFornecedorPorId(int idFornecedor) {
        String sql = "SELECT * FROM FORNECEDOR WHERE codFornecedor=?";
        return buscarPorId(sql, new FornecedorRowMapper(), idFornecedor);
    }
}
