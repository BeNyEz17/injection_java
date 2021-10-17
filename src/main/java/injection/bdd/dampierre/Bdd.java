package injection.bdd.dampierre;

import java.sql.*;

public class Bdd {

    // connexion a la basse de donnée
    String url = "jdbc:mysql://localhost:3306/bddinjection?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";
    String user = "root";
    String passwd = "POKEMON17";

    public Connection connexion() {
        Connection con = null;
        // essayer de la connexion a la bdd
        try {
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion réussi");
            return con;
        }
        // si cela ne marche pas error
        catch (SQLException e) {
            System.out.println("Connexion échoué");
            return con;
        }
    }
}
