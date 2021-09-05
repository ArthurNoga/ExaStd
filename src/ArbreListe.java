import java.util.*;

public class ArbreListe implements Arbre {
    private Noeud racine;

    public ArbreListe() {
        racine = null;
    }

    static class Noeud {
        private String nom;
        private List<Noeud> fils;

        public Noeud(String nom, List<Noeud> fils) {
            this.nom = nom;
            this.fils = fils;
        }

        public String toString() {
            return nom;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Noeud noeud = (Noeud) o;
            return Objects.equals(nom, noeud.nom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nom);
        }

        public String getNom() {
            return nom;
        }

        public List<Noeud> getFils() {
            return fils;
        }
    }


    @Override
    public Noeud creer(String nom, Noeud... fils) {
        return racine = new Noeud(nom, fils.length == 0 ? null : new ArrayList<>(Arrays.asList(fils)));
    }

    /**
     * Retourne la liste des noms des personnes n'ayant pas d'enfant ("les feuilles de l'arbre")
     */
    @Override
    public List<String> listeSansEnfant() throws NullPointerException {
        // TODO: parcourt tout l'arbre, et retourne le contenu (le nom) des feuilles
        sansEnfant.clear();
        listeSansEnfant(racine);
        return sansEnfant;
    }

    private List<String> sansEnfant = new ArrayList<>();

    private void listeSansEnfant(Noeud nMutation) {
        if (nMutation.getFils() == null) {
            sansEnfant.add(nMutation.nom);
            return;
        }
        for (Noeud n : nMutation.getFils()) {
            listeSansEnfant(n);
        }
    }


    /**
     * Indique si nom1 & nom2 sont "au même niveau" et n'ont pas le même père
     */
    @Override
    public boolean sontCousins(String nom1, String nom2) throws NullPointerException {
        // TODO: recherche la hauteur et le père de nom1 et nom2
        //  Algo possible:
        //    - recherche nom1 et conserve toute sa filiation (père/grand-père/arrière-grand-père/...),
        //    - idem pour nom2
        //    - compare la taille des 2 filiations/généalogies, et compare les 2 pères
        Noeud noeud1 = new Noeud(nom1, null);
        Noeud noeud2 = new Noeud(nom2, null);
        List<Noeud> geneN1 = Genealogie(noeud1);
        List<Noeud> geneN2 = Genealogie(noeud2);
        if (geneN1.size() == geneN2.size()) {
            Noeud pere1 = geneN1.remove(0);
            Noeud pere2 = geneN2.remove(0);
;
            if (geneN1.equals(geneN2) & !pere1.equals(pere2)) {
                return true;
            }
            return false;
        }

        return false;
    }

    public List<Noeud> Genealogie(Noeud nRechercher) {
        List<Noeud> genealogie = new ArrayList<>();
        Genealogie(nRechercher, racine, genealogie);
        genealogie.remove(0);
        ;

        return genealogie;
    }

    public List<Noeud> Genealogie(Noeud nRechercher, Noeud nMutation, List<Noeud> genealogie) {

        if (nMutation.getFils() != null) {
            for (Noeud fils : nMutation.getFils()) {
                if (Genealogie(nRechercher, fils, genealogie) != null) {
                    genealogie.add(nMutation);
                    return genealogie;
                }
            }
        }
        if (nRechercher.equals(nMutation)) {
            genealogie.add(nRechercher);
            return genealogie;
        }
        return null;
    }
}