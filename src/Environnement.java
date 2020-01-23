import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


class Environnement {
    private final Integer nbAgents;
    private final ArrayList<Stack<Agent>> colonnes;
    private final HashMap<Agent, Integer> emplacementDesAgents;


    Environnement(Integer nbAgents) {
        this.nbAgents = nbAgents;
        this.colonnes = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            this.colonnes.add(new Stack<>());
        }
        this.emplacementDesAgents = new HashMap<>(nbAgents);
    }

    void initialLoadOfAgents(List<Agent> agents) {
        this.colonnes.get(0).addAll(agents);
        for (Agent agent : agents) {
            emplacementDesAgents.put(agent, 0);
        }
    }


    Perception percevoir(Agent agent) {
        int emplacement = emplacementDesAgents.get(agent);
        Stack<Agent> colonneCourante = colonnes.get(emplacement);
        Integer positionDansColonne = colonneCourante.search(agent);
        Agent blocDessousCourant = null;
        if (positionDansColonne > 0 && positionDansColonne < colonneCourante.size() && positionDansColonne < nbAgents) {
            blocDessousCourant = colonneCourante.elementAt(positionDansColonne - 1);
        }
        boolean estEnDessousDunBloc = positionDansColonne > 1;
        Agent blocDessus = null;
        if (estEnDessousDunBloc) {
            blocDessus = colonneCourante.get(positionDansColonne - 1);
        }
        int place1 = emplacement + 1 == 1 ? 2 : 1;
        int place2 = emplacement + 1 == 3 ? 2 : 3;


        return new Perception(blocDessousCourant, blocDessus, place1, place2);
    }

    void changerEmplacement(Agent agent, int colonneDestinationId) {
        int emplacement = emplacementDesAgents.get(agent);
        Stack<Agent> colonneCourante = colonnes.get(emplacement);
        Agent agentDessusDePile = colonneCourante.pop();
        try {
            if (agentDessusDePile != agent) {
                throw new Exception("deplacement impossible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stack<Agent> colonneDestination = colonnes.get(colonneDestinationId - 1);
        colonneDestination.add(agent);

        emplacementDesAgents.put(agent, colonneDestinationId - 1);

        printEnvironnement();
    }

    private void printEnvironnement() {
        System.out.println("*********************");
        for (int i = 0; i < colonnes.size(); ++i) {
            Stack<Agent> colonne = colonnes.get(i);
            System.out.print("colonne " + (i + 1) + ": {");
            for (Agent agentAtJ : colonne) {
                System.out.print(agentAtJ.id + " ");
            }
            System.out.print("}\n");
        }
        System.out.println();
        System.out.println();
    }

}
