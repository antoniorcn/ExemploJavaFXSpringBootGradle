package edu.curso.componentes;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class SelectionComponentPane extends ScrollPane {

    private final VBox box= new VBox();
    private final Map<Node, Boolean> components = new HashMap<>();

    public SelectionComponentPane() {
        box.setStyle("-fx-padding: 10px");
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        this.setPannable(true);
        this.setContent(box);
    }

    public void add(Node n) {
        box.getChildren().add(n);
    }

    public void remove(Node n) {
        box.getChildren().remove(n);
    }

    public void clear() {
        box.getChildren().clear();
    }

}
