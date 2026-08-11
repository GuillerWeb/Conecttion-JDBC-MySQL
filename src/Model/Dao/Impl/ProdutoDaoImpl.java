package Model.Dao.Impl;

import Model.Dao.Interfaces.IProdutoDao;
import Model.Entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.BD;

public class ProdutoDaoImpl implements IProdutoDao{

    // Prepara minha conexão com o banco
    private final Connection connection;
    public ProdutoDaoImpl(Connection connection) {this.connection = connection;}

    //Mapeamento entre os objetos e a tabela do banco sendo feitas
    private Produto getProduto(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto();
        produto.setIdProduto(resultSet.getInt("id_produto"));
        produto.setNome(resultSet.getString("nome"));
        produto.setDescricao(resultSet.getString("descricao"));
        produto.setPreco(resultSet.getDouble("preco"));
        produto.setEstoque(resultSet.getInt("estoque"));
        return produto;
    }


    @Override
    public void insert(Produto produto) {
        PreparedStatement preparedStatement = null;
        try {
            String sqlInsert = "INSERT INTO produto(nome, descricao, preco, estoque) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getEstoque());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        findById(id);
        try {
            String sqlDeleteById = "DELETE FROM produto WHERE id_produto = ?";
            preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }

    }

    @Override
    public Produto findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlConsultById = "SELECT * FROM produto WHERE id_produto = ?";
            preparedStatement = connection.prepareStatement(sqlConsultById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getProduto(resultSet);
            } else {
                throw new SQLException("Erro ao buscar o produto");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public Produto findByNome(String nome) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlConsultByNome = "SELECT * FROM produto WHERE nome = ?";
            preparedStatement = connection.prepareStatement(sqlConsultByNome);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getProduto(resultSet);
            } else {
                throw new SQLException("Erro ao buscar o produto");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public void updateById(Produto produto) {
        PreparedStatement preparedStatement = null;
        findById(produto.getIdProduto());
        try {
            String sqlUpdateById = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, estoque = ? WHERE id_produto = ?";
            preparedStatement = connection.prepareStatement(sqlUpdateById);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getEstoque());
            preparedStatement.setInt(5, produto.getIdProduto());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Produto> listProdutos = new ArrayList<>();

        try {
            String sqlConsultAll = "SELECT * FROM produto";
            preparedStatement = connection.prepareStatement(sqlConsultAll);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listProdutos.add(getProduto(resultSet));
            }

            return listProdutos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }
}


