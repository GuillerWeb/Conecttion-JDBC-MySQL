package Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BD{

    // Método para carregar meu db.properties contendo dados sensíveis do Banco de Dados
    private static Properties loadProperties() throws IOException {
        try(FileInputStream fileInputstream = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputstream);
            return properties;
        } catch (IOException e) {
            throw new IOException("Erro ao carregar o arquivo db.properties");
        }
    }


    public static Connection connection = null;
    //Classe onde a conexão com o Banco vai ser efetuada por meio do arquivo properties e da url
    public static Connection getConnection() throws IOException, SQLException {

        try{
            if (connection == null) {
                Properties properties = loadProperties();
                String url = properties.getProperty("db.url");
                connection = DriverManager.getConnection(url, properties);

            }
        }catch(SQLException sqlException){
            throw new SQLException("Erro ao conectar ao banco de dados");
        }
        return connection;
    }

    //Métodos para fechar a conexão com o Statement e ResultSet
    public static void closeStatement(Statement st){
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
