package Model.Dao.Impl;

import Model.Dao.IClienteDao;
import Model.Entities.Cliente;
import Connection.BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {

    private final Connection connection;  // Atributo que fará as conexões com o banco nessa classe.

    public ClienteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para pegar os dados do banco e transformar em um objeto Cliente
    // Fazendo o mapeamento entre objetos e a tabela do banco
    private Cliente getCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(resultSet.getInt("id_cliente"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setTelefone(resultSet.getString("telefone"));
        cliente.setEmail(resultSet.getString("email"));
        cliente.setCidade(resultSet.getString("cidade"));
        return cliente;
    }

    @Override
    public void insert(Cliente cliente){

        PreparedStatement preparedStatement = null; // Preparar uma requisição para o banco

        try{
            String sqlInsert = "INSERT INTO cliente(nome,telefone,email,cidade) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, cliente.getNome()); // Coloca o id que eu quero busca no lugar do '?' e confirma seu tipo inteiro
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getCidade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public int updateById(Cliente cliente) {
        PreparedStatement preparedStatement = null; // Preparar um requesição para o banco
        findById(cliente.getIdCliente()); // Busca o cliente pelo id para verificar se existe

        try{
            String sqlUpdateById = "UPDATE cliente SET nome = ?, telefone = ?, email = ?, cidade = ? WHERE id_cliente = ?";
            preparedStatement = connection.prepareStatement(sqlUpdateById);
            preparedStatement.setString(1,cliente.getNome());
            preparedStatement.setString(2,cliente.getTelefone());
            preparedStatement.setString(3,cliente.getEmail());
            preparedStatement.setString(4,cliente.getCidade());
            preparedStatement.setInt(5,cliente.getIdCliente());
            return preparedStatement.executeUpdate();// No terminal retorna um inteiro que será quantidade de linhas afetadas
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            BD.closeStatement(preparedStatement);
        }
    }

    @Override
    public int deleteById(Integer id) {
        PreparedStatement preparedStatement = null; // Preparar um requesição para o banco
        findById(id); // Busca o cliente pelo id para verificar se existe

        try{
            String sqlDeleteById = "DELETE FROM cliente WHERE id_cliente = ?";
            preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();// No terminal retorna um inteiro que será quantidade de linhas afetadas
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            BD.closeStatement(preparedStatement);
        }
    }


    @Override
    public Cliente findById(Integer id) {
        PreparedStatement preparedStatement = null; // Preparar um requesição para o banco
        ResultSet resultSet = null; // Retorno que recebo do meu banco de dados

        try{
            String sqlConsultById = "SELECT * FROM cliente WHERE id_cliente = ?";
            preparedStatement = connection.prepareStatement(sqlConsultById);
            preparedStatement.setInt(1,id); // Coloca o id que eu quero busca no lugar do '?' e confirma seu tipo inteiro
            resultSet = preparedStatement.executeQuery(); // Traz o resultado da consulta

            if(resultSet.next()){
                return getCliente(resultSet);

            }else{
                throw new SQLException("Erro ao buscar o cliente");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }

    }


    @Override
    public List<Cliente> findAll(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Cliente> listClientes = new ArrayList<>();

        try{
            String sqlConsultAll = "SELECT * FROM cliente ";
            preparedStatement = connection.prepareStatement(sqlConsultAll);
            resultSet = preparedStatement.executeQuery(); // Traz o resultado da consulta

            while (resultSet.next()){
                listClientes.add(getCliente(resultSet));
            }
            return listClientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            BD.closeResultSet(resultSet);
            BD.closeStatement(preparedStatement);
        }

    }
}
