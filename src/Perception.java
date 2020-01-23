class Perception {
    final Agent blocDessousCourant;
    final Agent blocDessus; // on stocke l'agent plutot qu'on simple boolean pour transmettre interaction(pousse)
    final int place1; // 1,2,3
    final int place2;

    boolean estPousse;

    public Perception(Agent blocDessousCourant, Agent blocDessus, int place1, int place2) {
        this.blocDessousCourant = blocDessousCourant;
        this.blocDessus = blocDessus;
        this.place1 = place1;
        this.place2 = place2;
    }

    public void setEstPousse(boolean estPousse) {
        this.estPousse = estPousse;
    }
}
