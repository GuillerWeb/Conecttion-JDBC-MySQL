package Model.Dao.Interfaces;

import Model.Entities.Pedido;
import java.util.List;

public interface IPedidoDao {

    void insert(Pedido pedido);
    Pedido findById(Integer id);
    void deleteById(Integer id);
    List<Pedido> findAll();
}
