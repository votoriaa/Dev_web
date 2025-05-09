    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.victoriamasaro.modelo.dao;

    import com.mycompany.victoriamasaro.modelo.dao.entidade.Categoria;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.List;

    public class CategoriaDao extends GenericoDAO<Categoria> {

        public void salvar(Categoria objCidade){
            String sql = "INSERT INTO CATEGORIA(nome) values (?)";
            save(sql,objCidade.getNome());
        }

        public void alterar(Categoria objCidade){
            String sql = "UPDATE CATEGORIA SET NOME=? WHERE CODCATEGORIA=?";
            save(sql, objCidade.getNome(), objCidade.getCodCategoria());
        }

        public void excluir(Categoria objCidade){
            String sql = "DELETE FROM CATEGORIA WHERE CODCATEGORIA=?";
            save(sql, objCidade.getCodCategoria());
        }

        //implementa  o RowMapper específico para a entidade Cidade

        private static class CidadeRowMapper implements RowMapper<Categoria>{

            @Override
            public Categoria mapRow(ResultSet rs) throws SQLException {
                Categoria objCidade = new Categoria();
                objCidade.setCodCategoria(rs.getInt("CODCATEGORIA"));
                objCidade.setNome(rs.getString("NOME"));

                return objCidade;
            }

        }

        public List<Categoria> buscarTodasCidades(){
          String sql = "SELECT * FROM CATEGORIA";
          return buscarTodos(sql, new CidadeRowMapper());
        }

        public Categoria buscarCidadePorId(int idCidade){
            String sql = "SELECT * FROM CATEGORIA WHERE CODCARGO=?";
            return buscarPorId(sql, new CidadeRowMapper(), idCidade);
        }
    }






