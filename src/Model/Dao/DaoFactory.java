package Model.Dao;

import Connection.BD;
import Model.Dao.Impl.ClienteDaoImpl;
import Model.Dao.Impl.PedidoDaoImpl;
import Model.Dao.Impl.ProdutoDaoImpl;
import Model.Dao.Interfaces.IClienteDao;
import Model.Dao.Interfaces.IPedidoDao;
import Model.Dao.Interfaces.IProdutoDao;

import java.io.IOException;
import java.sql.SQLException;

//Criação de inversor de dependência
public class DaoFactory {

    public static IClienteDao createClienteDao() throws SQLException, IOException {
        return new ClienteDaoImpl(BD.getConnection());
    }
    public static IProdutoDao createProdutoDao() throws SQLException, IOException{
            return new ProdutoDaoImpl(BD.getConnection());
    }
    public static IPedidoDao createPedidoDao() throws SQLException, IOException{
        return new PedidoDaoImpl(BD.getConnection());
    }
}
