package Model.Dao;

import Model.Entities.Cliente;
import java.util.List;

public interface IClienteDao {

    void insert(Cliente cliente);    // Insere no banco
    int updateById(Cliente cliente); // Atualiza no banco
    int deleteById(Integer id);      // Deleta no banco por id
    Cliente findById(Integer id);    // Busca no banco por id
    List<Cliente> findAll();         // Busca todos os clientes e listar

}
