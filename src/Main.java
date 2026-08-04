import Model.Dao.*;
import Model.Entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        IClienteDao clienteDao = DaoFactory.createClienteDao();

        //Testando o método de buscar todos os clientes
        //List<Cliente> Listaclientes = clienteDao.findAll();
        //Listaclientes.forEach(System.out::println);

        //Testando o método de inserir no banco
        //Cliente cliente = new Cliente();
        //cliente.setNome("Jonas Emanuel");
        //cliente.setCidade("Belo Horizonte");
        //cliente.setEmail("jonas@hotmail.com");
        //cliente.setTelefone("119895542326");
        //clienteDao.insert(cliente);

        //Testando o delete, que retorna a quantidade de linhas afetadas na tabela do banco
        //int linhasAfetadas = clienteDao.deleteById(3);
        //System.out.println("Linhas afetadas: " + linhasAfetadas);

        //Testando o update
        //Cliente cliente = new Cliente();
        //cliente.setIdCliente(2);
        //cliente.setNome("Luiza Mel Alvarenga");
        //cliente.setEmail("luiza@email.com");
        //cliente.setTelefone("119895542326");
        //cliente.setCidade("Maringá");
        //clienteDao.updateById(cliente);





    }
}
