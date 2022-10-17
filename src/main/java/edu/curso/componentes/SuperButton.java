package edu.curso.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

public class SuperButton extends Button {
    public enum BUTTON_TYPE {OK, CANCEL}
    private DropShadow shadow = new DropShadow();
    private BUTTON_TYPE type;

    public SuperButton(String texto, BUTTON_TYPE type) {
        this.type = type;
        try {
            String imageName = type == BUTTON_TYPE.OK ? "check.png" : "cancel.png";
            ImageView image = new ImageView(new Image(
                    new ClassPathResource("images/" + imageName).getInputStream()
            ));
            image.setFitWidth(64.0);
            image.setFitHeight(64.0);
            this.setContentDisplay(ContentDisplay.RIGHT);
            this.setStyle("-fx-font: 22 arial; -fx-base: #faebd7; -fx-border-color: #8b4513; " +
                    "-fx-border-width: 3px; -fx-border-radius: 5em; -fx-background-radius: 5em;");
            this.setAlignment(Pos.CENTER);
            this.setGraphic(image);
            this.setText(texto);
            Font labelFont = new Font(32);
            this.setFont(labelFont);
            this.setEffect(shadow);
            this.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> this.setEffect(null));
            this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> this.setEffect(shadow));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
