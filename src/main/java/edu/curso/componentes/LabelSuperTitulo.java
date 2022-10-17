package edu.curso.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class LabelSuperTitulo extends Label {

    public LabelSuperTitulo(String texto) {
        this.setText(texto);
        Font labelFont = new Font(48);
        this.setPrefWidth(Double.MAX_VALUE);
        this.setAlignment(Pos.CENTER);
        this.setFont(labelFont);
        this.setStyle(  "-fx-text-fill: #faebd7; -fx-background-color: #a0522d; " +
                        "-fx-border-color: #faebd7;-fx-border-width: 5px; " +
                        "-fx-background-radius: 5em; -fx-border-radius: 5em; ");
    }
}
