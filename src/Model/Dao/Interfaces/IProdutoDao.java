package Model.Dao.Interfaces;

import Model.Entities.Produto;
import java.util.List;

public interface IProdutoDao {

    void insert(Produto produto);
    void deleteById(Integer id);
    Produto findById(Integer id);
    Produto findByNome(String nome);
    void updateById(Produto produto);
    List<Produto> findAll();
}
