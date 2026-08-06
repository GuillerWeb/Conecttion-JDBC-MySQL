package Model.Dao.Interfaces;

import Model.Entities.ItemPedido;
import java.util.List;

public interface IItemPedidoDao {

    void inserir(ItemPedido itemPedido);
    List<ItemPedido> listarPorPedido(int idPedido);
    void atualizarQuantidade(int idPedido, int idProduto, int novaQuantidade);
    void deletar(int idPedido, int idProduto);

}
