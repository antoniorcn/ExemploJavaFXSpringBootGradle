package edu.curso.componentes;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelPreco extends Label {
    public LabelPreco(double valor) {
        String texto = String.format("R$ %7.2f", valor);
        this.setText(texto);
        Font labelFont = new Font(24);
        this.setFont(labelFont);
    }
}
