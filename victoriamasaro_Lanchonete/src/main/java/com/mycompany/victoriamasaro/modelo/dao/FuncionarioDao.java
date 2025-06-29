package com.mycompany.victoriamasaro.modelo.dao;

import com.mycompany.victoriamasaro.modelo.dao.entidade.Funcionario;
import com.mycompany.victoriamasaro.modelo.dao.entidade.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario objFuncionario) {
        String sql = "INSERT INTO FUNCIONARIO(nome, carTrab, cpf, email, salarioAtual, dataAdmissao, cargo_codCargo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        save(sql,
             objFuncionario.getNome(),
             objFuncionario.getCarTrab(),
             objFuncionario.getCpf(),
             objFuncionario.getEmail(),
             objFuncionario.getSalarioAtual(),
             objFuncionario.getDataAdmissao(),
             objFuncionario.getCodCargo().getCodCargo());
    }

    public void alterar(Funcionario objFuncionario) {
        String sql = "UPDATE FUNCIONARIO SET nome=?, carTrab=?, cpf=?, email=?, salarioAtual=?, dataAdmissao=?, cargo_codCargo=? WHERE codFuncionario=?";
        save(sql,
             objFuncionario.getNome(),
             objFuncionario.getCarTrab(),
             objFuncionario.getCpf(),
             objFuncionario.getEmail(),
             objFuncionario.getSalarioAtual(),
             objFuncionario.getDataAdmissao(),
             objFuncionario.getCodCargo().getCodCargo(),
             objFuncionario.getCodFuncionario());
    }

    public void excluir(Funcionario objFuncionario) {
        String sql = "DELETE FROM FUNCIONARIO WHERE codFuncionario=?";
        save(sql, objFuncionario.getCodFuncionario());
    }

    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {

        CargoDao cargoDao = new CargoDao();

        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario objFuncionario = new Funcionario();

            objFuncionario.setCodFuncionario(rs.getInt("codFuncionario"));
            objFuncionario.setNome(rs.getString("nome"));
            objFuncionario.setCarTrab(rs.getString("carTrab"));
            objFuncionario.setCpf(rs.getString("cpf"));
            objFuncionario.setEmail(rs.getString("email"));
            objFuncionario.setSalarioAtual(rs.getDouble("salarioAtual"));
            objFuncionario.setDataAdmissao(rs.getDate("dataAdmissao"));
            objFuncionario.setCodCargo(cargoDao.buscarCargoPorID(rs.getInt("cargo_codCargo")));

            return objFuncionario;
        }
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        String sql = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(sql, new FuncionarioRowMapper());
    }

    public Funcionario buscarFuncionarioPorId(int idFuncionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE codFuncionario=?";
        return buscarPorId(sql, new FuncionarioRowMapper(), idFuncionario);
    }
}
