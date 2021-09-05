public interface Graphe {
    void addNoeud(int noeud);
    void addRelation(int source, int destination);
    int nombreDIles();
    boolean contientUnCycle();
}