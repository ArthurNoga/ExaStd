import java.util.*;

public class GrapheMap implements Graphe {
    private Map<Integer, Set<Integer>> lstNoeuds;
    private boolean oriente;

    /* Constructeur & méthodes permettant de créer un Graphe */
    /* !! Vous n'avez pas besoin de modifier ces méthodes !! */

    public GrapheMap(boolean oriente) {
        this.oriente = oriente;
        lstNoeuds = new HashMap<>();
    }

    @Override
    public void addNoeud(int noeud) {
        if (!lstNoeuds.keySet().contains(noeud)) {
            lstNoeuds.put(noeud, null);
        }
    }

    @Override
    public void addRelation(int source, int destination) {
        addNoeud(source);
        addNoeud(destination);
        addArc(source, destination);
        if (!oriente) {
            addArc(destination, source);
        }
    }

    private void addArc(int source, int destination) {
        Set<Integer> relations = lstNoeuds.get(source);
        if (relations == null) {
            relations = new HashSet<>();
            relations.add(destination);
            lstNoeuds.put(source, relations);
        } else {
            relations.add(destination);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrapheMap grapheMap = (GrapheMap) o;
        return oriente == grapheMap.oriente && Objects.equals(lstNoeuds, grapheMap.lstNoeuds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lstNoeuds, oriente);
    }

    /**
     * Retourne le nombre "d'îles" de ce graphe
     */
    List<Integer> marked = new ArrayList<>();
    Set<Integer> noeudsTesté = new HashSet<>();
    int cpt = 0;

    /**
     * Retourne le nombre "d'îles" de ce graphe
     */
    @Override
    public int nombreDIles() throws NullPointerException {
        // TODO: parcourt en profondeur TOUT le graphe (pas seulement les noeuds reliés)
        //       et compte le nombre "d'îles" / "de clusters" distincts / non reliés
        cpt = 0;
        marked.equals(null);
        noeudsTesté.equals(null);
        for (Integer i : lstNoeuds.keySet()) {
            parcours(i);
        }
        return cpt;
    }

    private void parcours(Integer n) {
        if (marked.contains(n)) {
            return;
        }
        if (lstNoeuds.get(n) == null) {
            cpt++;
            return;
        }
        marked.add(n);
        lstNoeuds.get(n).forEach(x -> noeudsTesté.add(x));
        if (!marked.contains(n) | !noeudsTesté.contains(n)) {
            cpt++;
        }
        for (Integer i : lstNoeuds.get(n)) {
            parcours(i);
        }
    }

    /**
     * Indique si ce graphe contient un cycle (une boucle)
     */
    @Override
    public boolean contientUnCycle() throws NullPointerException {
        // TODO: Vérifie s'il y a un cycle / une boucle dans ce graphe
//  Algo possible:
//    - parcourt en profondeur le graphe depuis tous les noeuds
//    - et vérifie à chaque fois si on arrive retomber sur le noeud initial
//    - (si oui, c'est qu'il y a au moins un cycle / une boucle)
        for (Integer noeud : lstNoeuds.keySet()) {
            if (lstNoeuds.get(noeud) != null) {
                for (Integer noeudVal : lstNoeuds.get(noeud)) {
                    if (parcoursProfondeurv1(noeudVal).contains(noeud)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private Set<Integer> parcoursProfondeurv1(Integer n) {
        Set<Integer> monGroupe = new HashSet<>();
        Integer stack = 0;
        Stack<Integer> stacks = new Stack<>();
        stacks.add(n);
        while (stacks.size() != 0) {
            stack = stacks.pop();
            if (!monGroupe.contains(stack)) {
                monGroupe.add(stack);
                if (lstNoeuds.get(stack) != null) {
                    stacks.addAll(lstNoeuds.get(stack));
                }
            }
        }

        return monGroupe;
    }
}







































    /*public int nombreDIles() throws NullPointerException {
        // TODO: parcourt en profondeur TOUT le graphe (pas seulement les noeuds reliés)
        //       et compte le nombre "d'îles" / "de clusters" distincts / non reliés

        Set<Integer> visited = new LinkedHashSet<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        Set<Integer> visited2 = new LinkedHashSet<Integer>();

        for (Integer voisin : lstNoeuds.get(0)) { //Prend les voisins du st actuel
            if (!visited.contains(voisin)) { //éviter d'ajouter dans la pile des chemins inutiles
                stack.push(voisin);
            }
        }

        int cpt = 0;
        int nonRelie = 0;

        while (cpt != 2){
            cpt++;
            stack.push(0);
            while (!stack.isEmpty()) {
                Integer st = stack.pop();
                if (!visited2.contains(st)) {

                    //rajout dans la pile:
                    if(cpt == 1) {
                        visited.add(st);
                        for (Integer voisin : lstNoeuds.get(st)) { //Prend les voisins du st actuel
                            if (!visited.contains(voisin)) { //éviter d'ajouter dans la pile des chemins inutiles
                                stack.push(voisin);
                            }
                        }
                    }
                    if(cpt==2){
                        visited2.add(st);
                        for (Integer v: visited){
                            if(!lstNoeuds.get(st).contains(v)){
                                nonRelie++;
                            }
                        }
                        for (Integer voisin : lstNoeuds.get(st)) { //Prend les voisins du st actuel
                            if (!visited2.contains(voisin)) { //éviter d'ajouter dans la pile des chemins inutiles
                                stack.push(voisin);
                            }
                        }
                    }
                }
            }
        }
        return nonRelie;
    }*/
