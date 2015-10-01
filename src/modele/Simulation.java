/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author Gus
 */
public class Simulation extends Service<Void> {

    private final List<Particule> particules;
    private final Terrain terrain;
    private static final double TEMP_EPSILON = 0.005;
    private static final int UPDATE_RATE = 120;

    public Simulation(List<Particule> particules, Terrain terrain) {
        this.particules = particules;
        this.terrain = terrain;
    }

    private void simulationUpdate() {
        double tempsRestant = 1.0;

        do {
            particules.stream().forEach((particules) -> {
                double tempsPlutotCollision = tempsRestant;

                particules.intersectTerrain(terrain, tempsRestant);
                particules.avancePas(tempsPlutotCollision);

            });
        } while (tempsRestant > TEMP_EPSILON);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                long debutTempMillis, tempPrisMillis, tempRestantMillis;
                debutTempMillis = System.currentTimeMillis();

                double tempsRestant = 1.0;

                try {
                    Thread.sleep(1000 / (long) UPDATE_RATE * (long) tempsPlutotCollision);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }

//                        synchronized (flag) {
//                            gereCollisionParticule(particuleCourrante);
//                            gereCollisionTerrain(particuleCourrante, terrain);
//                        }
//
//                        particuleCourrante.getPosition().setX(particuleCourrante.getPosition().getX() + particuleCourrante.getVitesse().getX());
//                        particuleCourrante.getPosition().setY(particuleCourrante.getPosition().getY() + particuleCourrante.getVitesse().getY());
//
                return null;
            }
        };
    }

}
