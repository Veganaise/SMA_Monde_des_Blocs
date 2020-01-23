class Interaction {
    private final boolean[] agentsPousses;

    Interaction(int nbAgent) {
        agentsPousses = new boolean[nbAgent];
    }

    /**
     * ordonne à agent de se pousser
     *
     * @param agent
     */
    void pousse(Agent agent) {
        agentsPousses[agent.id] = true;
    }

    /**
     * @param agent
     * @return vrai si agent poussé
     */
    boolean suisJePousses(Agent agent) {
        boolean estPousse = agentsPousses[agent.id];
        agentsPousses[agent.id] = false;
        return estPousse;
    }
}
