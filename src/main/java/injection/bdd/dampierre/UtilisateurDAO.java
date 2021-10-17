package injection.bdd.dampierre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    public List<Utilisateur> trouverParNom(String nom) {

        // initialisation de la base donnée
        Bdd bdd = new Bdd();
        // connexion a la BDD
        Connection conn = bdd.connexion();

        // commande sql a éviter pour avoir une injection sql car une personne qui nous
        // hack peut mettre ce qu'il veut
        // le ('%s'",nom) cela va etre une concaténation qui va causé l'injection sql
        String sql = String.format("select ID_USER, Prenom, Nom, Email from user where Nom = '%s'", nom);
        // commande pour éviter une injection
        // String sql = String.format ("select ID_USER, Prenom, Nom, Email from user
        // where Nom = ?");
        // A chaque fois que l'on veut faire une entre utilisateur on utilisera plutot
        // un "?" et pas une concaténation
        List<Utilisateur> utilisateurs = new ArrayList<>();

        // essay de la création et de l'excusion d'un statement
        try {
            // ceci est pas sécurisé
            Statement stmt = conn.createStatement();
            // il faut mieux faire une requête prépare pour une meilleur sécurité et enlever
            // les caractères étranger apres le nom
            // il faudrai mieux utilisé ceci pour éviter tout injection preparedStatement
            // stmt = conn.preparedStatement(sql)
            // on va y ajouter cette commande
            // stmt.setString(1,nom);
            // donc cette commande va prendre le point ? qui va etre le 1 et on va lui
            // ajouter le nom qu'on va demander a l'utilisateur
            ResultSet res = stmt.executeQuery(sql);
            // et pour plus de sécurité on va plus prendre en parametre sql et faire un
            // ResultSet res = stmt.executeQuery();

            while (res.next()) {
                utilisateurs.add(contruireClientDepuis(res));
            }
            // si cela marche pas erreur
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return utilisateurs;
    }

    private Utilisateur contruireClientDepuis(ResultSet res) throws SQLException {

        Long id = res.getLong("ID_User");
        String prenom = res.getString("Prenom");
        String nom = res.getString("Nom");
        String email = res.getString("Email");

        // appele de la méthode utilisateur avec des parametre (id,prenom,nom,email) et
        // la méthode et dans la classe utilisateur
        Utilisateur utilisateur = new Utilisateur(id, prenom, nom, email);

        return utilisateur;
    }

}
