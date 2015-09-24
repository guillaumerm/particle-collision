/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import modele.Collision;
import modele.Particule;
import modele.ParticuleException;
import modele.Terrain;
import modele.TerrainException;

/**
 *
 * @author 1141678
 */
public class Controleur extends Application implements Initializable {

    @FXML
    public BorderPane root;
    @FXML
    public Pane pane;
    @FXML
    public ColorPicker choixCouleur;
    @FXML
    public Slider rayon;
    @FXML
    public TextField nombres;
    @FXML
    public Slider vitesse;
    @FXML
    public Slider angle;

    private ArrayList<Particule> particules;
    private ArrayList<Shape> particulesVue;
    private int compteur;
    private final static long UPDATE_RATE = 120;
    private ExecutorService executorMovement;
    private Terrain terrain;
    private static final Object flag = new Object();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/tp2.fxml"));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add("ressources/css/style.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (((Button) event.getSource()).getText().equals("Quitter")) {
            Platform.exit();
        } else if (((Button) event.getSource()).getText().equals("Réinitialiser")) {
            reinitialiser();
        } else {
            ajouteBalle();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeGameComponents();
        initializeExecutor();

        //TODO mettre dans le FXML si possible
        pane.setOnMouseClicked((MouseEvent event) -> {
            ajouteBalle(event.getX(), event.getY());
        });
    }

    private void initializeGameComponents() {
        particules = new ArrayList<>();
        particulesVue = new ArrayList<>();

        try {
            terrain = new Terrain(0, 1000, 0, 500);
        } catch (TerrainException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeExecutor() {
        executorMovement = Executors.newFixedThreadPool(1, (Runnable r) -> {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            return thread;
        });

        TacheMouvement tacheMovement = new TacheMouvement();
        tacheMovement.setExecutor(executorMovement);

        tacheMovement.setOnSucceeded((t) -> {
            tacheMovement.reset();
            tacheMovement.restart();
        });

        tacheMovement.start();

    }

    @FXML
    private void ajouteBalle() {
        try {
            double positionX = random(terrain.getMinX() + rayon.getValue(), terrain.getMaxX() - rayon.getValue());
            double positionY = random(terrain.getMinY() + rayon.getValue(), terrain.getMaxY() - rayon.getValue());
            Particule particule = new Particule(vitesse.getValue(), angle.getValue(), rayon.getValue(), 100, 100, "#255");
            compteur++;
            nombres.setText(Integer.toString(compteur));

            particules.add(particule);

            Shape formeCourante = new Ellipse(rayon.getValue(), rayon.getValue());
            formeCourante.setFill(choixCouleur.getValue());
            formeCourante.setTranslateX(positionX);
            formeCourante.setTranslateY(positionY);

            particulesVue.add(formeCourante);
            
            Platform.runLater(() -> {
                pane.getChildren().add(formeCourante);
            });
        } catch (ParticuleException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ajouteBalle(double x, double y) {
        try {

            Particule particule = new Particule(vitesse.getValue(), angle.getValue(), rayon.getValue(), x, y, "#255");
            compteur++;
            nombres.setText(Integer.toString(compteur));

            Shape formeCourante = new Ellipse(rayon.getValue(), rayon.getValue());
            formeCourante.setFill(choixCouleur.getValue());
            formeCourante.setTranslateX(x);
            formeCourante.setTranslateY(y);

            synchronized (flag) {
                particulesVue.add(formeCourante);
                particules.add(particule);

                Platform.runLater(() -> {
                    pane.getChildren().add(formeCourante);
                });
            }
        } catch (ParticuleException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reinitialiser() {
        particules.clear();
        particulesVue.clear();
        pane.getChildren().clear();
    }

    public double random(double pMin, double pMax) {

        return (Math.floor(Math.random() * (pMax - pMin)) + pMin);
    }

    private void detectCollisionParticule(Particule particuleCourrante) {

        particules.stream().forEach((particule) -> {
            if (!particuleCourrante.equals(particule)) {
                double dx = particuleCourrante.getPosition().getX() - particule.getPosition().getX();
                double dy = particuleCourrante.getPosition().getY() - particule.getPosition().getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < particuleCourrante.getRayon() + particule.getRayon()) {

                    Collision.AppliquerCollisionParticule(particuleCourrante, particule);
                }
            }
        });

    }

    private class TacheMouvement extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    for (int i = 0; i < particules.size(); i++) {
                        final Particule particuleCourrante = particules.get(i);
                        final Shape particuleVueCourrante = particulesVue.get(i);

//                        detectGereCollisionTerrain(particuleCourrante);
                        synchronized (flag) {
                            detectCollisionParticule(particuleCourrante);
                            Collision.AppliquerCollisionConteneur(particuleCourrante, terrain);

                            particuleCourrante.getPosition().setX(particuleCourrante.getPosition().getX() + particuleCourrante.getVitesse().getX());
                            particuleCourrante.getPosition().setY(particuleCourrante.getPosition().getY() + particuleCourrante.getVitesse().getY());

                            Platform.runLater(() -> {
                                particuleVueCourrante.setTranslateX(particuleCourrante.getPosition().getX());
                                particuleVueCourrante.setTranslateY(particuleCourrante.getPosition().getY());
                            });
                        }
                    }

                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    return null;
                }
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
