package edu.curso.componentes;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelTitulo extends Label {

    public LabelTitulo(String texto) {
        this.setText(texto);
        Font labelFont = new Font(32);
        this.setFont(labelFont);
    }
}
