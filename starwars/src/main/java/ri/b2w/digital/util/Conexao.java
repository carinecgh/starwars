/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ri.b2w.digital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carine Henriques
 */
public class Conexao {

    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:postgresql://pgsql.carinehenriques.com.br:5432/carinehenriques1","carinehenriques1","DesafioB2W");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch(SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return con;
    }
}
