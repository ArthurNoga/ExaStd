public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("==================================================================================");
            System.out.println("===== 1) Test de la classe Arbre : listeSansEnfant() & sontCousins() =============");
            System.out.println("==================================================================================");

            Arbre a = getArbre();
            System.out.println("Personnes sans enfant: " + a.listeSansEnfant());

            System.out.println("Caro & Celia " + (a.sontCousins("Caro","Celia") ? "sont":"ne sont pas") + " cousins");
            System.out.println("Doly & Daryl " + (a.sontCousins("Doly","Daryl") ? "sont":"ne sont pas") + " cousins");
            System.out.println("Cody & Cora "  + (a.sontCousins("Cody","Cora")  ? "sont":"ne sont pas") + " cousins");
            System.out.println("Celia & Daria "+ (a.sontCousins("Celia","Daria")? "sont":"ne sont pas") + " cousins");

            System.out.println("==================================================================================");
            System.out.println("===== 2) Test de la classe Graphe : nombreDIles() & contientUnCycle() ============");
            System.out.println("==================================================================================");

            System.out.println("Nombre d'iles du Graphe1: " + getGraphe(1,false).nombreDIles());
            System.out.println("Nombre d'iles du Graphe2: " + getGraphe(2,false).nombreDIles());
            System.out.println("Nombre d'iles du Graphe3: " + getGraphe(3,false).nombreDIles());

            System.out.println("Graphe1 contient un cycle: " + getGraphe(1,true).contientUnCycle());
            System.out.println("Graphe2 contient un cycle: " + getGraphe(2,true).contientUnCycle());
            System.out.println("Graphe3 contient un cycle: " + getGraphe(3,true).contientUnCycle());
        } catch (NullPointerException e) {
                System.out.println("Caught the NullPointerException");
        }
    }

    private static Arbre getArbre() {
        Arbre a = new ArbreListe();
        a.creer("Adam",a.creer("Barry",a.creer("Caren"),a.creer("Carla",a.creer("Daria"),a.creer("Daryl")),a.creer("Caro")),a.creer("Ben",a.creer("Celia")),a.creer("Bill"),a.creer("Bob",a.creer("Cody",a.creer("Doly")),a.creer("Colin"),a.creer("Cora")));
        return a;
    }

    private static Graphe getGraphe(int no, boolean oriente) {
        Graphe gr = new GrapheMap(oriente);
        switch (no) {
            case 1: gr.addRelation(0,1); gr.addRelation(2,1); gr.addRelation(4,0); gr.addRelation(4,2); gr.addRelation(2,3); gr.addRelation(3,4); break;
            case 2: gr.addRelation(0,1); gr.addRelation(2,1); gr.addRelation(3,1); gr.addRelation(2,3); gr.addRelation(0,3); gr.addRelation(6,5); gr.addRelation(5,4); gr.addRelation(4,6); break;
            case 3: gr.addRelation(0,1); gr.addRelation(2,1); gr.addRelation(3,1); gr.addRelation(2,3); gr.addRelation(0,3); gr.addRelation(6,5); gr.addNoeud(4); break;
        }
        return gr;
    }
}