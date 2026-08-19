package Model.Dao.Impl;

import Connection.BD;
import Model.Dao.Interfaces.IItemPedidoDao;
import Model.Entities.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDaoImpl implements IItemPedidoDao {

    private final Connection connection;

    public ItemPedidoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public ItemPedido getItemPedido(ResultSet resultSet) throws SQLException {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdPedido(resultSet.getInt("id_pedido"));
        itemPedido.setIdProduto(resultSet.getInt("id_produto"));
        itemPedido.setQuantidade(resultSet.getInt("quantidade"));
        itemPedido.setPreco_unitario(resultSet.getBigDecimal("preco_unitario"));
        itemPedido.setSubtotal(resultSet.getBigDecimal("subtotal"));
        itemPedido.setNomeProduto(resultSet.getString("nome_produto"));
        return itemPedido;
    }

    @Override
    public void insert(ItemPedido itemPedido) {
        PreparedStatement preparedStatement = null;

        try {
            String sqlInsert = "INSERT INTO item_pedido " +
                    "(id_pedido, id_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, itemPedido.getIdPedido());
            preparedStatement.setInt(2, itemPedido.getIdProduto());
            preparedStatement.setInt(3, itemPedido.getQuantidade());
            preparedStatement.setBigDecimal(4, itemPedido.getPreco_unitario());
            preparedStatement.setBigDecimal(5, itemPedido.getSubtotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<ItemPedido> listAll(int idPedido) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ItemPedido> itensPedido = new ArrayList<>();

        try {
            String sqlConsultAll = "SELECT ip.*, p.nome AS nome_produto " +
                    "FROM item_pedido ip " +
                    "INNER JOIN produto p ON p.id_produto = ip.id_produto " +
                    "WHERE ip.id_pedido = ?";
            preparedStatement = connection.prepareStatement(sqlConsultAll);
            preparedStatement.setInt(1, idPedido);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                itensPedido.add(getItemPedido(resultSet));
            }
            return itensPedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<ItemPedido> listAllByClient(int idCliente) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ItemPedido> itensPedido = new ArrayList<>();

        try {
            String sqlConsultByClient = "SELECT ip.*, p.nome AS nome_produto " +
                    "FROM item_pedido ip " +
                    "INNER JOIN pedido pe ON pe.id_pedido = ip.id_pedido " +
                    "INNER JOIN produto p ON p.id_produto = ip.id_produto " +
                    "WHERE pe.id_cliente = ?";
            preparedStatement = connection.prepareStatement(sqlConsultByClient);
            preparedStatement.setInt(1, idCliente);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                itensPedido.add(getItemPedido(resultSet));
            }
            return itensPedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(int idPedido, int idProduto) {
        PreparedStatement preparedStatement = null;

        try {
            String sqlDeleteById = "DELETE ip FROM item_pedido ip " +
                    "INNER JOIN pedido pe ON pe.id_pedido = ip.id_pedido " +
                    "WHERE ip.id_pedido = ? AND ip.id_produto = ? AND pe.status = 'PENDENTE'";
            preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, idProduto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BD.closeStatement(preparedStatement);
        }
    }
}
