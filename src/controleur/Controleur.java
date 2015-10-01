package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import modele.collision.Collision;
import modele.Particule;
import modele.Simulation;
import modele.exception.ParticuleException;
import modele.Terrain;
import modele.exception.TerrainException;

/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
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

    private ArrayList<Shape> particulesVue;
    private List<Particule> particules;
    private int compteur;
    private final static long UPDATE_RATE = 60;
    private ExecutorService executorSimulation;
    private Terrain terrain;
    private static final Object flag = new Object();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/tp2.fxml"));

        Scene scene = new Scene(root);
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

    @FXML
    private void handleMouseClick(MouseEvent event) {
        ajouteBalle(event.getX(), event.getY());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initialiseComposantesJeu();
        initialiseExecutor();
    }

    private void initialiseComposantesJeu() {
        particules = Collections.synchronizedList(new ArrayList<>());
        particulesVue = new ArrayList<>();

        try {
            terrain = new Terrain(0, 1000, 0, 500);
        } catch (TerrainException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialiseExecutor() {
        executorSimulation = Executors.newFixedThreadPool(1, (Runnable r) -> {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            return thread;
        });

        Simulation tacheSimulation = new Simulation(particules, terrain);
        tacheSimulation.setExecutor(executorSimulation);

        tacheSimulation.setOnSucceeded((t) -> {
            update();
            tacheSimulation.reset();
            tacheSimulation.restart();
        });

        tacheSimulation.start();
    }

    private void ajouteBalle() {
        Particule particule = creerParticule(random(terrain.getMinX(), terrain.getMaxX()), random(terrain.getMinY(), terrain.getMaxY()));
        incrementerNombreParticule();
        Shape formeCourante = creerCercle(particule);

        ajouterParticuleSimulation(formeCourante, particule);
    }

    private void ajouteBalle(double x, double y) {
        Particule particule = creerParticule(x, y);
        incrementerNombreParticule();
        Shape formeCourante = creerCercle(particule);

        ajouterParticuleSimulation(formeCourante, particule);
    }

    private Shape creerCercle(Particule particule) {
        Shape formeCourante = new Ellipse(rayon.getValue(), rayon.getValue());
        formeCourante.setFill(choixCouleur.getValue());
        formeCourante.setTranslateX(particule.getPosition().getX());
        formeCourante.setTranslateY(particule.getPosition().getY());

        return formeCourante;
    }

    private Particule creerParticule(double x, double y) {
        Particule particule = null;

        try {
            String couleur = "#" + Integer.toHexString(choixCouleur.getValue().hashCode()).substring(0, 6).toUpperCase();
            particule = new Particule(vitesse.getValue(), angle.getValue(), rayon.getValue(), x, y, couleur);
        } catch (ParticuleException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return particule;
    }

    private void incrementerNombreParticule() {
        compteur++;
        nombres.setText(Integer.toString(compteur));
    }

    private void ajouterParticuleSimulation(Shape particuleVue, Particule particule) {
        synchronized (flag) {
            particules.add(particule);
            particulesVue.add(particuleVue);
            particules.add(particule);
        }

        Platform.runLater(() -> {
            pane.getChildren().add(particuleVue);
        });
    }

    private void reinitialiser() {
        synchronized (flag) {
            particules.clear();
            particulesVue.clear();
        }
        compteur = 0;

        Platform.runLater(() -> {
            pane.getChildren().clear();
            nombres.setText(Integer.toString(compteur));
        });

    }

    private void gereCollisionParticule(Particule particuleCourrante) {
        particules.stream().forEach((particule) -> {
            if (!particuleCourrante.equals(particule)) {
                Collision.AppliquerCollisionParticule(particuleCourrante, particule);
            }
        });
    }

    private void gereCollisionTerrain(Particule particuleCourrante, Terrain terrain) {
        Collision.AppliquerCollisionConteneur(particuleCourrante, terrain);
    }

    private void update() {
        for (int i = 0; i < particules.size() - 1; i++) {
            Particule particule = particules.get(i);
            Shape particuleVue = particulesVue.get(i);

            Platform.runLater(() -> {
                particuleVue.setTranslateX(particule.getPosition().getX());
                particuleVue.setTranslateY(particule.getPosition().getY());
            });
        }
    }

    public static double random(double pMin, double pMax) {
        return (Math.floor(Math.random() * (pMax - pMin)) + pMin);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
