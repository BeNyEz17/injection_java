package injection.bdd.dampierre;

public final class App {

    public static void main(String[] args) {
        Bdd bdd = new Bdd();
        bdd.connexion();
    }

}
