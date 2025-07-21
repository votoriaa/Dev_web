package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Entregas;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntregasDao extends GenericoDAO<Entregas> {

    public void salvar(Entregas objEntrega) {
        String sql = "INSERT INTO ENTREGAS(endereco, numeroCasa, cliente_codCliente) VALUES (?, ?, ?)";
        save(sql,
            objEntrega.getEndereco(),
            objEntrega.getNumeroCasa(),
            objEntrega.getObjCliente().getCodCliente()
        );
    }

    public void alterar(Entregas objEntrega) {
        String sql = "UPDATE ENTREGAS SET endereco=?, numeroCasa=?, cliente_codCliente=? WHERE codEntrega=?";
        save(sql,
            objEntrega.getEndereco(),
            objEntrega.getNumeroCasa(),
            objEntrega.getObjCliente().getCodCliente(),
            objEntrega.getCodEntrega()
        );
    }

    public void excluir(Entregas objEntrega) {
        String sql = "DELETE FROM ENTREGAS WHERE codEntrega=?";
        save(sql, objEntrega.getCodEntrega());
    }

    private static class EntregasRowMapper implements RowMapper<Entregas> {
        ClienteDao clienteDao = new ClienteDao();

        @Override
        public Entregas mapRow(ResultSet rs) throws SQLException {
            Entregas e = new Entregas();
            e.setCodEntrega(rs.getInt("codEntrega"));
            e.setEndereco(rs.getString("endereco"));
            e.setNumeroCasa(rs.getString("numeroCasa"));
            Cliente cliente = clienteDao.buscarClientePorID(rs.getInt("cliente_codCliente"));
            e.setObjCliente(cliente);
            return e;
        }
    }

    public List<Entregas> buscarTodasEntregas() {
        String sql = "SELECT * FROM ENTREGAS";
        return buscarTodos(sql, new EntregasRowMapper());
    }

    public Entregas buscarEntregaPorId(int idEntrega) {
        String sql = "SELECT * FROM ENTREGAS WHERE codEntrega=?";
        return buscarPorId(sql, new EntregasRowMapper(), idEntrega);
    }
}
