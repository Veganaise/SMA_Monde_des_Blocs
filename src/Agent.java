import static java.lang.Thread.sleep;

class Agent implements Runnable {
    final int id;
    private final Environnement env;
    private final Interaction interaction;
    private final Integer blocDessousButId; //id of agent or null if none

    private Perception percep;


    Agent(Environnement env, Interaction interaction, int id, Integer blocDessousButId) {
        this.env = env;
        this.interaction = interaction;
        this.id = id;
        this.blocDessousButId = blocDessousButId;
    }

    private void perception() {
        Perception perception = env.percevoir(this);
        perception.setEstPousse(interaction.suisJePousses(this));
        this.percep = perception;
    }

    private void action() {
        // le bloc qui est censé ètre en dessous de tous les autres est toujours consiferé bien placé
        // les autres blocs sont bien placés si le bloc perçu en dessous d'eux est blocDessousBut
        boolean estBienPlace = blocDessousButId == null ||
                (percep.blocDessousCourant != null && (percep.blocDessousCourant.id == blocDessousButId));

        if (percep.estPousse || !estBienPlace) { //si il est poussé ou mal placé
            if (percep.blocDessus == null) {//au dessus de la pile (donc il peut bouger )
                // déplacement aléatoire
                boolean choisitPlace1 = Math.random() < 0.5;
                env.changerEmplacement(this, choisitPlace1 ? percep.place1 : percep.place2);
            } else {
                //propage poussée au bloc du dessus
                interaction.pousse(percep.blocDessus);
            }
        }
    }


    @Override
    public void run() {
        int i = 0;
        while (i < 500) {
//            System.out.println("yo "+id);
            perception();
            action();
            i++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
