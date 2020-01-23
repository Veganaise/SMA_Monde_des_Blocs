import java.util.ArrayList;
import java.util.Collections;

class Systeme {
    private final ArrayList<Agent> agents;
    private final Environnement env;
    private final Interaction interaction;

    Systeme(int nbAgents) {
        env = new Environnement(nbAgents);
        agents = new ArrayList<>(nbAgents);
        interaction = new Interaction(nbAgents);

        // selection de bloc dessous but
        ArrayList<Integer> idOfPossibleBlocDessousBut = new ArrayList<>(nbAgents);
        for (int i = 0; i < nbAgents; ++i) {
            idOfPossibleBlocDessousBut.add(i);
        }
        Collections.shuffle(idOfPossibleBlocDessousBut);

        // instanciation des agents
        for (int i = 0; i < nbAgents; ++i) {
            Integer blocDessousButId = (i > 0 ? i - 1 : null);
            agents.add(new Agent(env, interaction, i, blocDessousButId));
        }
        Collections.shuffle(agents);
        env.initialLoadOfAgents(agents);
    }

    void startSystem() {
        for (Agent agent :
                agents) {
            Thread t = new Thread(agent);
            t.start();
        }
    }
}
