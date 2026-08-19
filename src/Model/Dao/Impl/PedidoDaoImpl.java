package Model.Dao.Impl;

import Model.Dao.Interfaces.IPedidoDao;
import Model.Entities.Pedido;
import Connection.BD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDaoImpl implements IPedidoDao {

    // Estabelecendo conexão com banco
    private final Connection connection;

    public PedidoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Mapeamento entre os objetos e a tabela do banco de dados
    public Pedido getPedido(ResultSet resultSet) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(resultSet.getInt("id_pedido"));
        pedido.setIdCliente(resultSet.getInt("id_cliente"));
        pedido.setData(resultSet.getDate("data").toLocalDate());
        pedido.setTotal(resultSet.getBigDecimal("total"));
        pedido.setStatus(resultSet.getString("status"));
        return pedido;
    }

    @Override
    public void insert(Pedido pedido) {
        PreparedStatement preparedStatement = null;

        try {
            String sqlInsert = "INSERT INTO pedido(id_cliente, data, total, status) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, pedido.getIdCliente());
            preparedStatement.setDate(2, Date.valueOf(pedido.getData()));
            preparedStatement.setBigDecimal(3, pedido.getTotal());
            preparedStatement.setString(4, pedido.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public Pedido findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlConsultById = "SELECT * FROM pedido WHERE id_pedido = ?";
            preparedStatement = connection.prepareStatement(sqlConsultById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getPedido(resultSet);
            }
            throw new SQLException("Erro ao buscar o pedido");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        findById(id);

        try {
            String sqlDeleteById = "DELETE FROM pedido WHERE id_pedido = ?";
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
    public List<Pedido> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            String sqlConsultAll = "SELECT * FROM pedido";
            preparedStatement = connection.prepareStatement(sqlConsultAll);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pedidos.add(getPedido(resultSet));
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }
}
