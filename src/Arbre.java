import java.util.List;

public interface Arbre {
    ArbreListe.Noeud creer(String nom, ArbreListe.Noeud... fils);
    List<String> listeSansEnfant();
    boolean sontCousins(String nom1, String nom2);
}