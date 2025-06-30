package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.ItensVenda;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Produto;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItensVendaDao extends GenericoDAO<ItensVenda> {

    public void salvar(ItensVenda objItensVenda) {
        String sql = "INSERT INTO ITENSVENDA(quantVenda, produto_codProduto, venda_codVenda) VALUES(?,?,?)";
        save(sql,
             objItensVenda.getQuantVenda(),
             objItensVenda.getObjProduto().getCodProduto(),
             objItensVenda.getObjVenda().getCodVenda());
    }

    public void alterar(ItensVenda objItensVenda) {
        String sql = "UPDATE ITENSVENDA SET quantVenda=?, produto_codProduto=?, venda_codVenda=? WHERE codItemVenda=?";
        save(sql,
             objItensVenda.getQuantVenda(),
             objItensVenda.getObjProduto().getCodProduto(),
             objItensVenda.getObjVenda().getCodVenda(),
             objItensVenda.getCodItemVenda());
    }

    public void excluir(ItensVenda objItensVenda) {
        String sql = "DELETE FROM ITENSVENDA WHERE codItemVenda=?";
        save(sql, objItensVenda.getCodItemVenda());
    }

    private static class ItensVendaRowMapper implements RowMapper<ItensVenda> {

        ProdutoDao produtoDao = new ProdutoDao();
        VendaDao vendaDao = new VendaDao();

        @Override
        public ItensVenda mapRow(ResultSet rs) throws SQLException {
            ItensVenda item = new ItensVenda();
            item.setCodItemVenda(rs.getInt("codItemVenda"));
            item.setQuantVenda(rs.getDouble("quantVenda"));
            item.setObjProduto(produtoDao.buscarProdutoPorId(rs.getInt("produto_codProduto")));
            item.setObjVenda(vendaDao.buscarVendaPorId(rs.getInt("venda_codVenda")));
            return item;
        }
    }

    public List<ItensVenda> buscarTodosItensVenda() {
        String sql = "SELECT * FROM ITENSVENDA";
        return buscarTodos(sql, new ItensVendaRowMapper());
    }

    public ItensVenda buscarItemVendaPorId(int id) {
        String sql = "SELECT * FROM ITENSVENDA WHERE codItemVenda=?";
        return buscarPorId(sql, new ItensVendaRowMapper(), id);
    }
}
