package Model.Dao.Interfaces;

import Model.Entities.ItemPedido;
import java.util.List;

public interface IItemPedidoDao {

    void insert(ItemPedido itemPedido);
    List<ItemPedido> listAll(int idPedido);
    List<ItemPedido> listAllByClient(int idCliente);
    void deleteById(int idPedido, int idProduto); // Caso pedido estiver como pendente podera ser cancelad

}
