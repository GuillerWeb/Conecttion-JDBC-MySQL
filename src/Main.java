import Model.Dao.*;
import Model.Dao.Interfaces.IClienteDao;
import Model.Dao.Interfaces.IItemPedidoDao;
import Model.Dao.Interfaces.IPedidoDao;
import Model.Dao.Interfaces.IProdutoDao;
import Model.Entities.Cliente;
import Model.Entities.ItemPedido;
import Model.Entities.Pedido;
import Model.Entities.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        IClienteDao clienteDao = DaoFactory.createClienteDao();
        IProdutoDao produtoDao = DaoFactory.createProdutoDao();
        IPedidoDao pedidoDao = DaoFactory.createPedidoDao();
        IItemPedidoDao itemPedidoDao = DaoFactory.createItemPedidoDao();




    }
}
