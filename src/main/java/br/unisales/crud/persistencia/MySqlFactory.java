package br.unisales.crud.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlFactory {
    //endereço (url) do servidor mysql juntamente com a porta de conexão
    private static final String urlBD = "jdbc:mysql://localhost:1234/";
    //nome da base de dados 
    private static final String BD = "crud";
    //usuário do banco de dados
    private static final String userBD = "root";
    //senha do usuário do banco de dados
    private static final String passBD = "";
 
    private static MySqlFactory instancia = null;

    public static Connection getCon() {
        Connection conexao = null;
        try {
         /* as duas linhas abaixo são para a conexão com Mysql 5
          * Class.forName("com.mysql.jdbc.Driver");
          * conexao = DriverManager.getConnection(urlBD + BD, userBD, passBD);
          * as duas linhas acima são para a conexão com Mysql 5
          */
         
         //as duas linhas abaixo são para a conexão com Mysql 8
         Class.forName("com.mysql.cj.jdbc.Driver");
         conexao = DriverManager.getConnection(urlBD + "?useTimezone=true&serverTimezone=UTC", userBD, passBD);
   
         conexao.setAutoCommit(false);
         
         return conexao;
         
        } catch (Exception e) {
            
            throw new RuntimeException(e);
            
        }
    }
 
    public static MySqlFactory getInstancia() {

           if (instancia == null) {

               instancia = new MySqlFactory();

           }

           return instancia;
    }
}