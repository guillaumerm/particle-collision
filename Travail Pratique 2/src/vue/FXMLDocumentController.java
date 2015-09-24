/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 *
 * @author antoi_000
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    public Pane pane;
    @FXML
    public ColorPicker choixCouleur;
    @FXML
    public Slider rayon;
    @FXML
    public TextField nombres;

    private Shape formeCourante;
    private int compteur;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (((Button) event.getSource()).getText().equals("Quitter")) {
            Platform.exit();
        }
        else if (((Button) event.getSource()).getText().equals("Réinitialiser")) {
            Platform.exit();
        }
        else {
            ajouteBalle();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 @FXML
    private void ajouteBalle() {
        compteur++;
        nombres.setText(Integer.toString(compteur));
        formeCourante = new Ellipse(rayon.getValue(), rayon.getValue());
        formeCourante.fillProperty().bind(choixCouleur.valueProperty());
        formeCourante.setTranslateX(100);
        formeCourante.setTranslateY(100);
        pane.getChildren().add(formeCourante);
        
    }
    
}
