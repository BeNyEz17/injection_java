package injection.bdd.dampierre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class AppTest {

    @Test
    void unNomUnique_trouverParNom_devraitRetournerLeLclient() {

        UtilisateurDAO utilisateur = new UtilisateurDAO();

        List<Utilisateur> listeObtenue = utilisateur.trouverParNom("pluchart");

        assertEquals(1, listeObtenue.size(), "Doit contenir un utilisateur contenant le nom pluchart");

    }

    @Test
    void doublonDeNom_trouverParNom_devraitRetournerLesDeuxClients() {

        UtilisateurDAO utilisateur = new UtilisateurDAO();

        List<Utilisateur> listeObtenue = utilisateur.trouverParNom("test");

        assertEquals(2, listeObtenue.size(), "Doit contenir deux utilisateurs contenant le nom test");
    }

    @Test
    void aucunNom_trouverParNom_devraitRetournerZeroClient() {

        UtilisateurDAO utilisateur = new UtilisateurDAO();

        List<Utilisateur> listeObtenue = utilisateur.trouverParNom("patrick");

        assertEquals(0, listeObtenue.size(), "Doit contenir aucun utilisateurs contenant le nom patrick");
    }

    @Test
    public void injection_trouverParNom_devraitRenvoyerzeroClient() {

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        List<Utilisateur> utilisateurs = utilisateurDAO.trouverParNom("pirate or '1' = '1'");

        assertEquals(0, utilisateurs.size(), "L'injection à réussi");
    }

}
