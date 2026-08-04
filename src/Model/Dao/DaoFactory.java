package Model.Dao;

import Connection.BD;
import Model.Dao.Impl.ClienteDaoImpl;
import java.io.IOException;
import java.sql.SQLException;

//Criação de inversor de dependência
public class DaoFactory {

    public static IClienteDao createClienteDao() throws SQLException, IOException {
        return new ClienteDaoImpl(BD.getConnection());
    }
}
